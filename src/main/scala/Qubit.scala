import scala.math.{abs, pow, sqrt}
import breeze.math.Complex.*

import scala.language.postfixOps
import ImaginaryExtentions.*
import breeze.math._
case class Qubit(alpha: Complex, beta: Complex) {
  def norm: Double = sqrt(pow(alpha.abs,2) + pow(beta.abs,2))

  def isNormalized: Boolean = {
    val tolerance = 1e-9
    abs(norm - 1) < tolerance
  }
  def normalize: Qubit = {
    val norm = this.norm
    Qubit(
      alpha/norm,
      beta/norm
    )
  }
  def **(other: Qubit): Ket = {
    Ket(alpha*other.alpha,alpha*other.beta,beta*other.alpha,beta*other.beta)
  }

  override def toString: String = {
    def formatDouble(d: Double): String = {
      val formatted = f"$d%.3f".replace(",", ".")  // Standardize decimal separator
      if (formatted.endsWith(".000")) formatted.dropRight(4) else formatted
    }
    def formatComplex(c: Complex): String = {
      (c.real, c.imag) match {
        case (0, 0) => "0"
        case (r, 0) => s"${formatDouble(c.real)}"// Standardize decimal separator
        case (0, i) =>
          val imagPart = if (math.abs(c.imag) == 1) "" else formatDouble(math.abs(c.imag))
          s"${if (c.imag < 0) "-" else ""}${imagPart}i"
        case (r, i) =>
          val realPart = formatDouble(c.real)
          val imagPart =
            if (math.abs(c.imag) == 1) s"${if (c.imag < 0) "-" else "+"}i"
            else s"${if (c.imag < 0) "-" else "+"}${formatDouble(math.abs(c.imag))}i"
          s"$realPart$imagPart"
    }
  }

    def formatTerm(coefficient: Complex, basis: String): String = {
      coefficient match {
        case Complex(0, 0) => ""
        case Complex(1, 0) => s"|$basis⟩"
        case Complex(-1, 0) => s"-|$basis⟩"
        case Complex(0, 1) => s"i|$basis⟩"
        case Complex(0, -1) => s"-i|$basis⟩"
        case _ => s"${formatComplex(coefficient)}|$basis⟩"
      }
    }

    val alphaTerm = formatTerm(alpha, "0")
    val betaTerm = formatTerm(beta, "1")

    (alphaTerm, betaTerm) match {
      case ("", "") => "0"  // Zero state
      case (a, "") => s"$a"
      case ("", b) => s"$b"
      case (a, b) =>
        val separator = if (beta.real < 0 || (beta.real == 0 && beta.imag < 0)) " - " else " + "
        val absBetaTerm = if (separator == " - ") formatTerm(beta * -1, "1") else b
        s"$a$separator$absBetaTerm"
    }
}

}

object Qubit {
  private val sqr2 = 1/sqrt(2)
  val Zero: Qubit = new Qubit(1.0,0.0)

  val One: Qubit = new Qubit(0.0,1.0)

  val Plus: Qubit = new Qubit(sqr2,sqr2)

  val Minus: Qubit = new Qubit(sqr2,-sqr2)

  val YZero: Qubit = new Qubit(sqr2, sqr2)

  val YOne: Qubit = new Qubit(sqr2, sqr2.i)
}
