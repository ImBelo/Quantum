case class Real(value: Double) {
  def +(other: Imaginary): Complex = Complex(value,other.value)
  def +(other: Complex): Complex = Complex(value,other.im)
  def -(other: Imaginary): Complex = Complex(value,other.value)
  def -(other: Complex): Complex = Complex(value,-other.im)
  def *(other: Imaginary): Imaginary = Imaginary(value*other.value)
  def *(other: Complex): Complex = Complex(other.re*value,other.im*value)
  def /(other: Imaginary): Imaginary = Imaginary(value/other.value)
  def /(other: Complex): Complex = Complex(value/other.re,value/other.im)
}
