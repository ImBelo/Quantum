import scala.math.sqrt
/*
class DensityMatrix(val matrix: Matrix) {
  require(isHermitian, "Density matrix must be Hermitian")
  require(hasUnitTrace, "Density matrix must have trace 1")
  require(isPositiveSemidefinite, "Density matrix must be positive semi-definite")

  // Check if ρ = ρ† (Hermitian)
  def isHermitian: Boolean = {
    matrix.zip(matrix.transpose).forall { case (row, col) => row == col.map(_.conjugate) }
  }

  // Check if Tr(ρ) = 1
  def hasUnitTrace: Boolean = {
    val trace = matrix.zipWithIndex.map { case (row, i) => row(i) }.sum
    trace.real = 1.0 && trace.imaginary = 0.0
  }

  // Check if all eigenvalues ≥ 0 (simplified)
  def isPositiveSemidefinite: Boolean = {
    // (In practice, use a linear algebra library for eigenvalue computation)
    true // Placeholder for actual implementation
  }

  // Purity: Tr(ρ²)
  def purity: Double = {
    val ρ² = multiply(this, this)
    ρ².trace.real
  }

  // Trace of the matrix
  def trace: Complex = matrix.zipWithIndex.map { case (row, i) => row(i) }.sum

  // Matrix multiplication helper
  def multiply(a: DensityMatrix, b: DensityMatrix): DensityMatrix = {
    // Implement matrix multiplication
    ???
  }
}
object DensityMatrix {
  // From a pure state |ψ⟩
  def fromPureState(ket: Ket): DensityMatrix = {
    val bra = ket.toBra
    val matrix = ket.amplitudes.flatMap { a =>
      bra.amplitudes.map { b => a * b }
    }.grouped(bra.amplitudes.size).toVector
    new DensityMatrix(matrix)
  }

  // From a mixed state: Σ p_i |ψ_i⟩⟨ψ_i|
  def fromMixedState(states: Map[Ket, Double]): DensityMatrix = {
    val weightedMatrices = states.map { case (ket, prob) =>
      val ρ_i = fromPureState(ket)
      ρ_i.matrix.map(row => row.map(_ * prob))
    }
    val summedMatrix = weightedMatrices.reduce { (m1, m2) =>
      m1.zip(m2).map { case (r1, r2) => r1.zip(r2).map { case (a, b) => a + b } }
    }
    new DensityMatrix(summedMatrix)
  }
}*/