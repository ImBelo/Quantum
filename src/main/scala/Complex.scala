import com.sun.org.apache.xalan.internal.lib.ExsltMath.{atan2, cos, sin}

import scala.annotation.tailrec
import scala.math.Numeric
import scala.math.Numeric.FloatIsFractional.div
case class Complex(re: Double, im: Double = 0.0) {
  //Sum of 2 complex number
  def +(other: Complex): Complex =
    Complex(re+ other.re,im+other.im)
  //Difference of 2 complex number
  def -(other: Complex): Complex =
    Complex((re- other.re), (im- other.im))
  //multiplication fo 2 complex number
  def *(other: Complex): Complex = Complex(
    (re*other.re)-(im*other.im),
    (re*other.im)+(im*other.re)
  )
  def *(other: Double): Complex = Complex(
    re*other,
    im*other
  )
  def /(other: Complex): Complex = {
    val denominator = other.re* other.re+ other.im* other.im
    if (denominator == 0.0) {
      throw new ArithmeticException("Complex division by zero")
    }
    Complex(
      (re*other.re+im*other.im) / denominator,
      (im*other.re-re*other.im) / denominator
    )
  }
  def /(other: Double): Complex = {
    if (other == 0.0) {
      throw new ArithmeticException("Complex division by zero")
    }
    Complex(
      re/other,
      im/other
    )
  }

  // Square root of complex number
  def sqrt: (Complex, Complex) = {
    val Pi = Math.PI

    val magnitude = Math.sqrt(re*re + im*im)
    val angle = atan2(im, re)

    val root1 = Complex(
      Math.sqrt(magnitude) * cos(angle/2),
      Math.sqrt(magnitude) * sin(angle/2)
    )

    val root2 = Complex(
      Math.sqrt(magnitude) * cos((angle + 2*Pi)/2),
      Math.sqrt(magnitude) * sin((angle + 2*Pi)/2)
    )

    (root1, root2)
  }
  def norm: Double = Math.sqrt(this.magnitudeSquared)

  def toDouble: Complex = Complex(re.toDouble, im.toDouble)
  def toFloat: Complex = Complex(re.toFloat, im.toFloat)

  // Conjugate
  def conjugate: Complex = Complex(re, -im)

  // Magnitude squared (avoiding sqrt for generic types)
  def magnitudeSquared: Double = (re*re)+(im*im)
  def prettyPrint: String = {
    (re, im) match {
      case (0, 0) => "0"                
      case (0, 1) => "i"             
      case (0, -1) => "-i"             
      case (0, i) => f"${i}%.3fi"             
      case (r, 1) => f"${r}%.3f+i"             
      case (r, -1) => f"${r}%.3f-i"             
      case (r, 0) => f"${r}%.3f"               
      case (r, i) if i < 0 => f"$r%.3f-${-i}%.3fi"  
      case (r, i) => f"${r}%.3f+${i}%.3fi"       
    }
  }
  override def toString: String = {
    (re, im) match {
      case (0, 0) => "0"                  // 0 + 0i → "0"
      case (0, i) => s"${i}i"             // 0 + 2i → "2i"
      case (r, 0) => s"$r"                // 3 + 0i → "3"
      case (r, i) if i < 0 => s"$r - ${-i}i"  // 1 - 2i
      case (r, i) => s"$r + ${i}i"        // 1 + 2i
    }
  }

}
