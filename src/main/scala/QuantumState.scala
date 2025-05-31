import breeze.linalg.{DenseVector, sum}
import breeze.math.Complex
import breeze.numerics.abs

abstract class QuantumState {
  def amplitudes: DenseVector[Complex]
  def dimension: Int = amplitudes.length
  def norm: Double = math.sqrt(sum(amplitudes.map(a =>(Math.pow(abs(a.real),2)+Math.pow(abs(a.imag),2)))))
}
