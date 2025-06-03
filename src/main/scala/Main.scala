import breeze.linalg.*
import ImaginaryExtentions.*
import breeze.math.Complex
import breeze.numerics.sqrt
object Main {
   def main(args: Array[String]): Unit = {
     val oneover = Complex(1/Math.sqrt(2),0.0)
     val hadamard = new DenseMatrix[Complex](2,2,Array(
       oneover, oneover,
       oneover, -oneover)
     )
     val register = Qubit.Zero**Qubit.One
     val register2 = Qubit.YZero**Qubit.One
     val gate = QuantumGates.H**QuantumGates.I**QuantumGates.Z
     println(register** register2.toBra)
   }


}
