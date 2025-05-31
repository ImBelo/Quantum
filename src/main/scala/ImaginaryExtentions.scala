import breeze.math.Complex

import scala.language.implicitConversions

object ImaginaryExtentions {
  // Convert Double to Real
  implicit def doubleToReal(d: Double): Real = Real(d)
  implicit def doubleToComplex(d: Double): Complex = Complex(d,0.0)
  // Convert Imaginary to ComplexNumber
  implicit def imaginaryToComplex(i: Imaginary): Complex = 
    Complex(0.0, i.value)

  // Enable 2.0.i syntax
  implicit class DoubleOps(d: Double) {
    def i: Imaginary = Imaginary(d)
  }
}