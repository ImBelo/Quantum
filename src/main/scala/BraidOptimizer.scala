import breeze.linalg.{DenseMatrix, norm}
import breeze.math.Complex

import scala.util.Random

class BraidOptimizer(target: DenseMatrix[Complex], tolerance: Double) {
  private val populationSize = 100
  private val maxGenerations = 500
  private val maxBraidLength = 30
  private val eliteCount = 10
  private val mutationRate = 0.1

  case class Candidate(sequence: Seq[Braid], error: Double)

  def optimize(): Seq[Braid] = {
    var population = initializePopulation()

    for (gen <- 1 to maxGenerations) {
      population = evolvePopulation(population)
      if (population.head.error <= tolerance) return population.head.sequence
    }

    population.minBy(_.error).sequence
  }

  private def initializePopulation(): Vector[Candidate] = {
    Vector.fill(populationSize) {
      val length = Random.nextInt(maxBraidLength/2) + 1
      val seq = Vector.fill(length)(if (Random.nextBoolean()) Sigma1() else Sigma2())
      Candidate(seq, computeError(seq))
    }.sortBy(_.error)
  }

  private def evolvePopulation(pop: Vector[Candidate]): Vector[Candidate] = {
    val elites = pop.take(eliteCount)
    val children = Vector.fill(populationSize - eliteCount) {
      val parent1 = selectParent(pop)
      val parent2 = selectParent(pop)
      crossover(parent1, parent2)
    }
    (elites ++ children).sortBy(_.error)
  }

  private def selectParent(pop: Vector[Candidate]): Candidate = {
    // Tournament selection
    val tournamentSize = 5
    val candidates = Vector.fill(tournamentSize)(pop(Random.nextInt(pop.size)))
    candidates.minBy(_.error)
  }

  private def crossover(p1: Candidate, p2: Candidate): Candidate = {
    val crossoverPoint = Random.nextInt(math.min(p1.sequence.length, p2.sequence.length))
    val newSeq = (p1.sequence.take(crossoverPoint) ++
                 p2.sequence.drop(crossoverPoint)).take(maxBraidLength)
    mutate(Candidate(newSeq, computeError(newSeq)))
  }

  private def mutate(candidate: Candidate): Candidate = {
    if (Random.nextDouble() < mutationRate) {
      val idx = Random.nextInt(candidate.sequence.length)
      val newBraid = if (Random.nextBoolean()) Sigma1() else Sigma2()
      val newSeq = candidate.sequence.updated(idx, newBraid)
      Candidate(newSeq, computeError(newSeq))
    } else candidate
  }
  private def computeError(seq: Seq[Braid]): Double = {
    val product = seq.foldLeft(DenseMatrix.eye[Complex](target.rows))(_ * _.matrix)
    norm(target.toDenseVector - product.toDenseVector)
  }
}
