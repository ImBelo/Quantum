class QuantumState {
  def amplitudes: Vector[Complex]
  def dimension: Int = amplitudes.length
  def norm: Double = math.sqrt(amplitudes.map(a =>(a.magnitudeSquared)).sum)
}
