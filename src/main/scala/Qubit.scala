import scala.math.{abs, pow, sqrt}
case class Qubit(alpha : Complex, beta: Complex) {

  def norm: Double = sqrt(alpha.magnitudeSquared + beta.magnitudeSquared)

  def isNormalized: Boolean = abs(sqrt(alpha.magnitudeSquared + beta.magnitudeSquared - 1)) < 1e-9

  def normalize: Qubit = {
    val norm = this.norm
    Qubit(
      alpha/norm,
      beta/norm
    )
  }
  override def toString: String = {
  val alphaStr = alpha match {
    case Complex(0, 0) => "0"
    case Complex(r, 0) => s"${r}"
    case Complex(0, i) => s"${i}i"
    case Complex(r, i) => s"${r} ${if (i >= 0) "+" else "-"} ${math.abs(i)}i"
  }

  val betaStr = beta match {
    case Complex(r, i) if r < 0 => s"${-r} ${if (i >= 0) "+" else "-"} ${math.abs(i)}i" // Flip sign of real
    case Complex(r, i) => s"${r} ${if (i >= 0) "+" else "-"} ${math.abs(i)}i"
  }

  val separator = if (beta.re < 0) " - " else " + " // Use minus if real part is negative

  s"ψ = $alphaStr|0⟩$separator$betaStr|1⟩"
}

  object Qubit{
    def apply(alpha: Complex, beta: Complex): Qubit = new Qubit(alpha, beta)
    val Zero: Qubit = new Qubit(Complex(1.0), Complex(0.0))

    val One: Qubit = new Qubit(Complex(0.0), Complex(1.0))

    val XZero: Qubit = new Qubit(Complex(1.0 / sqrt(2)), Complex(1.0 / sqrt(2)))

    val XOne: Qubit = new Qubit(Complex(1.0 / sqrt(2)), Complex(-1.0 / sqrt(2)))

    val YZero: Qubit = new Qubit(Complex(1.0 / sqrt(2)), Complex(1.0 / sqrt(2)))

    val YOne: Qubit = new Qubit(Complex(1.0 / sqrt(2)), Complex(0.0, 1.0 / sqrt(2)))
  }

}
