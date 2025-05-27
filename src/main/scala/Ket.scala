import ImaginaryExtention.*

import java.util.Optional
import scala.language.postfixOps
class Ket(val amplitudes: Vector[Complex]) extends QuantumState,Iterable[Complex] {
  def apply(amplitude: Complex*): Ket = new Ket(amplitude.toVector)
  def apply(amplitude: Vector[Complex]): Ket = new Ket(amplitude)
  override def iterator: Iterator[Complex] = amplitudes.iterator
  // Construct from varargs
  def this(amplitudes: Complex*) = this(amplitudes.toVector)


  // Convert to Bra ⟨ψ|
  def toBra: Bra = Bra(amplitudes.map(_.conjugate))
  def *(other:Complex): Ket = {
    Ket(amplitudes.map(_*other))
  }
  // Tensor product
  def **(other: Ket): Ket = {
    val newAmplitudes = for {
      a <- this.amplitudes
      b <- other.amplitudes
    } yield a * b
    new Ket(newAmplitudes)
  }
  def **(other: Bra):Matrix = ???
  // Normalize the state
  def normalized: Ket = {
    val n = this.norm
    if (n == 0.0) throw new IllegalArgumentException("Cannot normalize zero vector")
    Ket(amplitudes.map(_ / n))
  }

  override def toString: String = {
    val size = (Math.log(amplitudes.length)/Math.log(2)).toInt;
    def formatNumber(number: Int):String = String.format(s"%${size}s", Integer.toBinaryString(number)).replace(' ', '0')
    val basisStates = amplitudes.indices.map(i => s"|${formatNumber(i)}⟩")
    val terms = amplitudes.zip(basisStates).collect {
      case (amp, state) =>
        val coef = amp match {
          case Complex(1.0, 0.0) => ""
          case Complex(-1.0, 0.0) => "-"
          case _ => s"($amp)"
        }
        s"$coef$state"
    }
    if (terms.isEmpty) "|0⟩"
    else terms.mkString(" + ")
  }
}