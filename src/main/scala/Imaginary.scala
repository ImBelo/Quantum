// Purely imaginary number (0 + bi)
case class Imaginary(value: Double) {
  def real: Double = 0.0
  def imag: Double = value
  def toComplex: Complex = Complex(0.0, value)

  def *(real: Double): Imaginary = Imaginary(real * value)
  def *(im: Imaginary): Imaginary = Imaginary(im.value * (-value))
  def *(complex: Complex): Complex = Complex(complex.re,-value*complex.im)
  def +(complex : Complex): Complex = Complex(complex.re, complex.im+value)
  def +(real: Double): Complex = Complex(real,value)
  def +(im: Imaginary): Imaginary = Imaginary(im.value+value)
  def -(complex : Complex): Complex = Complex(complex.re, complex.im+value)
  def -(real: Double): Complex = Complex(-real,value)
  def -(im: Imaginary): Imaginary = Imaginary(value-im.value)
}
def -(imaginary: Imaginary): Imaginary = Imaginary(-imaginary.value)
