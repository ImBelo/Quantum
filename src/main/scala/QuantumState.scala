import breeze.linalg.{DenseMatrix, DenseVector, sum}
import breeze.math.Complex
import breeze.numerics.abs

abstract class QuantumState {
  def amplitudes: DenseVector[Complex]
  def dimension: Int = amplitudes.length
  def norm: Double = math.sqrt(sum(amplitudes.map(a =>(Math.pow(abs(a.real),2)+Math.pow(abs(a.imag),2)))))
  def applyGate(gate: QuantumGates): QuantumState = {
    require(gate.matrix.cols == dimension, "Gate dimension must match state dimension")
    Ket(gate.matrix * amplitudes)
  }
}
