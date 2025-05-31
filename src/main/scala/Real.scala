import breeze.math.Complex

case class Real(value: Double) {
  def +(other: Imaginary): Complex = Complex(value,other.value)
  def +(other: Complex): Complex = Complex(value,other.imag)
  def -(other: Imaginary): Complex = Complex(value,other.value)
  def -(other: Complex): Complex = Complex(value,-other.imag)
  def *(other: Imaginary): Imaginary = Imaginary(value*other.value)
  def *(other: Complex): Complex = Complex(other.real*value,other.imag*value)
  def /(other: Imaginary): Imaginary = Imaginary(value/other.value)
  def /(other: Complex): Complex = Complex(value/other.real,value/other.imag)
}