import breeze.linalg.{DenseMatrix, DenseVector, kron}

import java.util.Optional
import scala.language.postfixOps
import breeze.math.{Complex, Field}

class Ket(val amplitudes: DenseVector[Complex]) extends QuantumState{
  def apply(amplitude: Complex*): Ket = new Ket(DenseVector(amplitude.toArray))
  def apply(amplitude: DenseVector[Complex]): Ket = new Ket(amplitude)
  // Construct from varargs
  def this(amplitudes: Complex*) = this(DenseVector(amplitudes.toArray))

  // Construct from varargs
  // Convert to Bra ⟨ψ|
  def toBra: Bra = Bra(amplitudes.map(_.conjugate))
  def *(other:Complex): Ket = {
    Ket(amplitudes.map(_*other))
  }
  // Tensor product
  def **(other: Ket): Ket = {
    val n = amplitudes.length
    val m = other.amplitudes.length
    val data = new Array[Complex](n * m)

    var idx = 0
    val a1 = amplitudes.data  // Direct access to underlying array
    val a2 = other.amplitudes.data

    for (i <- 0 until n) {
      val x = a1(i)
      for (j <- 0 until m) {
        data(idx) = x * a2(j)
        idx += 1
      }
    }

    new Ket(DenseVector(data))
  }
  def **(other: Qubit): Ket = {
    val otherKet = Ket(other.alpha,other.beta)
    this**otherKet
  }
  def **(other: Bra):DenseMatrix[Complex] = {
    val rows = this.dimension
    val cols = other.dimension
    val result = DenseMatrix.zeros[Complex](rows, cols)

    for (i <- 0 until rows; j <- 0 until cols) {
      result(i, j) = this.amplitudes(i) * other.amplitudes(j).conjugate // ⟨ϕ| is the conjugate of |ϕ⟩
    }

    result
  }
  // Normalize the state
  def normalized: Ket = {
    val n = this.norm
    if (n == 0.0) throw new IllegalArgumentException("Cannot normalize zero vector")
    Ket(amplitudes.map(_ / n))
  }
  override def toString: String = {
    val size = (Math.log(amplitudes.length)/Math.log(2)).toInt;
    def formatNumber(number: Int):String = String.format(s"%${size}s", Integer.toBinaryString(number)).replace(' ', '0')
    val basisStates = (0 to amplitudes.length).map(i => s"|${formatNumber(i)}⟩")
    val terms = amplitudes.toArray.zip(basisStates).collect {
      case (amp, state) =>
        val coef = amp match {
          case Complex(0.0, 0.0) => ""
          case Complex(1.0, 0.0) => ""
          case Complex(-1.0, 0.0) => "-"
          case _ => s"($amp)"
        }
        val end = amp match{
          case Complex(0.0, 0.0) => ""
          case _ => state
        }
        s"$coef$end"
    }
    if (terms.isEmpty) "|0⟩"
    else terms.filter(_.nonEmpty).mkString(" + ")
  }

}