// Bra vector ⟨ψ|
case class Bra(amplitudes: Vector[Complex]) extends QuantumState {
  // Bra-vector specific operations
  def *(ket: Ket): Complex = {
    require(this.dimension == ket.dimension, "Dimension mismatch in inner product")
    (this.amplitudes * ket.amplitudes).apply(0, 0)
  }
  
  // Outer product |φ⟩⟨ψ|
  def outerProduct(ket: Ket): Matrix[Complex] = {
    ket.amplitudes * this.amplitudes.t
  }
}