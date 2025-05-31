import breeze.linalg._
import breeze.math.Complex


trait Braid {
  def matrix: DenseMatrix[Complex]
}

// Elementary braiding operations
case class Sigma1() extends Braid {
 val sigma1 = DenseMatrix[Complex](
  Array(-0.80901699-0.5877852522.i, Complex.zero),
  Array(Complex.zero, -0.309016994+0.95105651629.i)
)
 val F = DenseMatrix[Complex](
  Array(Complex(1 / phi), Complex(1 / sqrt(phi))),
  Array(Complex(1 / sqrt(phi)), Complex(-1 / phi))
)

val sigma2 = F * sigma1 * F.inv
}

case class Sigma2() extends Braid {
  val matrix = DenseMatrix(
    (Complex(0.8090169943749475, -0.5877852522924731), Complex.zero),
    (Complex.zero, Complex(-0.3090169943749474, -0.9510565162951535))
  )
}