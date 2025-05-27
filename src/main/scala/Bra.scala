import breeze.linalg.DenseVector

// Bra vector ⟨ψ|
case class Bra(amplitudes: DenseVector[Complex]) extends QuantumState {
   def dagger: Ket = Ket(amplitudes.map(_.conjugate))

}