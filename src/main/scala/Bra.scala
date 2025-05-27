// Bra vector ⟨ψ|
case class Bra(amplitudes: Vector[Complex]) extends QuantumState {
   def dagger: Ket = Ket(amplitudes.map(_.conjugate).toVector)

}