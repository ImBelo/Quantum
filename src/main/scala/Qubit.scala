import scala.math.{abs, pow, sqrt}
case class Qubit (alpha: Complex,beta: Complex) {
  def norm: Double = sqrt(alpha.magnitudeSquared + beta.magnitudeSquared)

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
  override def toString: String = {
  def formatComplex(c: Complex): String = {
    (c.re, c.im) match {
      case (0, 0) => "0"
      case (r, 0) => f"${c.re}%.3f".replace(",", ".") // Standardize decimal separator
      case (0, i) =>
        val imagPart = if (math.abs(c.im) == 1) "" else f"${math.abs(c.im)}%.3f".replace(",", ".")
        s"${if (c.im < 0) "-" else ""}${imagPart}i"
      case (r, i) =>
        val realPart = f"${c.re}%.3f".replace(",", ".")
        val imagPart =
          if (math.abs(c.im) == 1) s"${if (c.im < 0) "-" else "+"}i"
          else f"${if (c.im < 0) "-" else "+"}${math.abs(c.im)}%.3f".replace(",", ".") + "i"
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
      val separator = if (beta.re < 0 || (beta.re == 0 && beta.im < 0)) " - " else " + "
      val absBetaTerm = if (separator == " - ") formatTerm(beta * -1, "1") else b
      s"$a$separator$absBetaTerm"
  }
}

}

object Qubit {
  val Zero: Qubit = new Qubit(Complex(1.0), Complex(0.0))

  val One: Qubit = new Qubit(Complex(0.0), Complex(1.0))

  val Plus: Qubit = new Qubit(Complex(1.0 / sqrt(2)), Complex(1.0 / sqrt(2)))

  val Minus: Qubit = new Qubit(Complex(1.0 / sqrt(2)), Complex(-1.0 / sqrt(2)))

  val YZero: Qubit = new Qubit(Complex(1.0 / sqrt(2)), Complex(1.0 / sqrt(2)))

  val YOne: Qubit = new Qubit(Complex(1.0 / sqrt(2)), Complex(0.0, 1.0 / sqrt(2)))
}
