import breeze.linalg.{DenseVector, sum}

abstract class QuantumState {
  def amplitudes: DenseVector[Complex]
  def dimension: Int = amplitudes.length
  def norm: Double = math.sqrt(sum(amplitudes.map(a =>(a.magnitudeSquared))))
}
