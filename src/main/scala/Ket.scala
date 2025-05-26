case class Ket(amplitudes: Vector[Complex]) extends QuantumState {
  require(norm == 1.0, "Ket vectors must be normalized")

  // Construct from varargs
  def this(amplitudes: Complex*) = this(amplitudes.toVector)

  // Convert to Bra ⟨ψ|
  def toBra: Bra = Bra(amplitudes.map(_.conjugate))

  // Tensor product
  def **(other: Ket): Ket = {
    val newAmplitudes = for {
      a <- this.amplitudes
      b <- other.amplitudes
    } yield a * b
    Ket(newAmplitudes)
  }

  // Inner product ⟨φ|ψ⟩
  def innerProduct(other: Ket): Complex = ??? 

  // Normalize the state
  def normalized: Ket = {
    val n = this.norm
    Ket(amplitudes.map(_ / n))
  }
}