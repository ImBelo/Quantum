import breeze.linalg.DenseVector
import breeze.math.Complex

// Bra vector ⟨ψ|
class Bra(val amplitudes: DenseVector[Complex]) extends QuantumState {
   def apply(amplitude: Complex*): Bra = new Bra(DenseVector(amplitude.toArray))
   def apply(amplitude: DenseVector[Complex]): Bra = new Bra(amplitude)
   // Construct from varargs
   def this(amplitudes: Complex*) = this(DenseVector(amplitudes.toArray))
   def dagger: Ket = Ket(amplitudes.map(_.conjugate))

}