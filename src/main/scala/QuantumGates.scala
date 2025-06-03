import breeze.linalg.{DenseMatrix, DenseVector}
import breeze.math.Complex
case class QuantumGates(matrix : DenseMatrix[Complex]){
  def **(b: QuantumGates): QuantumGates = {
    val result = DenseMatrix.zeros[Complex](this.matrix.rows * b.matrix.rows, this.matrix.cols * b.matrix.cols)
    for {
      i1 <- 0 until this.matrix.rows
      j1 <- 0 until this.matrix.cols
      i2 <- 0 until b.matrix.rows
      j2 <- 0 until b.matrix.cols
    } {
      result(i1 * b.matrix.rows + i2, j1 * b.matrix.cols + j2) = this.matrix(i1, j1) * b.matrix(i2, j2)
    }
    new QuantumGates(result)
  }
}
object QuantumGates {
  val i: Complex = Complex.i
  private val zero = Complex.zero
  private val one = Complex.one
  private val sqrt2: Complex = Complex(math.sqrt(2),0.0)
  // Single-qubit gates
  val I = new QuantumGates(DenseMatrix((one, zero), (zero, one))) // Identity
  val X = new QuantumGates(DenseMatrix((zero, one), (one, zero))) // Pauli-X (NOT)
  val Y = new QuantumGates(DenseMatrix((zero, -i), (i, zero)))    // Pauli-Y
  val Z = new QuantumGates(DenseMatrix((one, zero), (zero, -one))) // Pauli-Z
  val H = new QuantumGates(DenseMatrix((one/sqrt2, one/sqrt2), (one/sqrt2, -one/sqrt2)) )// Hadamard
  val S = new QuantumGates(DenseMatrix((one, zero), (zero, i)))    // Phase (S gate)
  val T = new QuantumGates(DenseMatrix((one, zero), (zero, (one+i)/sqrt2))) // T gate (π/8)
  // Two-qubit gates
  def CNOT: DenseMatrix[Complex] = DenseMatrix(
    (one, zero, zero, zero),
    (zero, one, zero, zero),
    (zero, zero, zero, one),
    (zero, zero, one, zero)
  )

  def SWAP: DenseMatrix[Complex] = DenseMatrix(
    (one, zero, zero, zero),
    (zero, zero, one, zero),
    (zero, one, zero, zero),
    (zero, zero, zero, one)
  )

  // Three-qubit gate
  def Toffoli: DenseMatrix[Complex] = DenseMatrix(
    (one, zero, zero, zero, zero, zero, zero, zero),
    (zero, one, zero, zero, zero, zero, zero, zero),
    (zero, zero, one, zero, zero, zero, zero, zero),
    (zero, zero, zero, one, zero, zero, zero, zero),
    (zero, zero, zero, zero, one, zero, zero, zero),
    (zero, zero, zero, zero, zero, one, zero, zero),
    (zero, zero, zero, zero, zero, zero, zero, one),
    (zero, zero, zero, zero, zero, zero, one, zero)
  )

  // Controlled gates
  def controlled(gate: DenseMatrix[Complex]): DenseMatrix[Complex] = {
    val n = gate.rows
    DenseMatrix.tabulate(n * 2, n * 2) { (i, j) =>
      if (i < n && j < n && i == j) one
      else if (i >= n && j >= n) gate(i - n, j - n)
      else zero
    }
  }

}
