import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import scala.math.Fractional.Implicits._

class ComplexTest extends AnyFlatSpec with Matchers {

  // Test helper function
  def assertComplexApproxEquals[T: Fractional](actual: Complex[T], expected: Complex[T], epsilon: Double = 1e-6): Unit = {
    val frac = implicitly[Fractional[T]]
    import frac._

    def toDouble(x: T): Double = x match {
      case d: Double => d
      case f: Float => f.toDouble
      case bd: BigDecimal => bd.toDouble
      case _ => throw new IllegalArgumentException("Unsupported type")
    }

    val actualRe = toDouble(actual.re)
    val actualIm = toDouble(actual.im)
    val expectedRe = toDouble(expected.re)
    val expectedIm = toDouble(expected.im)

    actualRe should be (expectedRe +- epsilon)
    actualIm should be (expectedIm +- epsilon)
  }

  "Complex numbers" should "correctly handle all operations for Double" in {
    val a = Complex(1.5, 2.5)
    val b = Complex(3.0, 4.0)

    // Addition
    (a + b) shouldEqual Complex(4.5, 6.5)

    // Subtraction
    (a - b) shouldEqual Complex(-1.5, -1.5)

    // Multiplication
    (a * b) shouldEqual Complex(1.5*3.0 - 2.5*4.0, 1.5*4.0 + 2.5*3.0)

    // Division
    assertComplexApproxEquals(a / b, Complex(0.58, 0.06))
  }

  it should "correctly handle all operations for Float" in {
    val a = Complex(1.5f, 2.5f)
    val b = Complex(3.0f, 4.0f)

    // Addition
    (a + b) shouldEqual Complex(4.5f, 6.5f)

    // Subtraction
    (a - b) shouldEqual Complex(-1.5f, -1.5f)

    // Multiplication
    assertComplexApproxEquals(a * b, Complex(-5.5f, 13.5f), 1e-6f)

    // Division
    assertComplexApproxEquals(a / b, Complex(0.58f, 0.06f), 1e-6f)
  }


  it should "throw ArithmeticException when dividing by zero" in {
    val a = Complex(1.0, 2.0)
    val zero = Complex(0.0, 0.0)

    an[ArithmeticException] should be thrownBy {
      a / zero
    }
  }

  it should "handle edge cases correctly" in {
    // Test with zeros
    val zero = Complex(0.0, 0.0)
    val one = Complex(1.0, 0.0)
    val i = Complex(0.0, 1.0)

    (zero + one) shouldEqual one
    (one - one) shouldEqual zero
    (i * i) shouldEqual Complex(-1.0, 0.0)
    (one / i) shouldEqual Complex(0.0, -1.0)
  }

  it should "work with mixed operations" in {
    val a = Complex(1.0, 2.0)
    val b = Complex(3.0, 4.0)
    val c = Complex(5.0, 6.0)

    val result = (a + b) * c - (b / a)
    val expectedReal = -18.2
    val expectedImag = 54.4

    assertComplexApproxEquals(result, Complex(expectedReal, expectedImag))
  }
}