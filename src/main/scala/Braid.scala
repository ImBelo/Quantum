import breeze.linalg._
import breeze.math.Complex
import ImaginaryExtentions.*
import Math.*

trait Braid {
  def matrix: DenseMatrix[Complex]
  private val phi = (1 + sqrt(5.0)) / 2.0
  val tau: Complex = 1.0 / phi
  val sqrttau: Complex = 1.0/sqrt(phi)
}

// Elementary braiding operations
case class Sigma1() extends Braid {
  val matrix = DenseMatrix(
    (-tau, sqrttau),
    (sqrttau, tau)
  )
}

case class Sigma2() extends Braid {
  val matrix: DenseMatrix[Complex] = DenseMatrix(
    (tau, sqrttau), 
    (sqrttau, -tau)
  )
}