import breeze.linalg.*
import ImaginaryExtentions.*
import breeze.math.Complex
import breeze.numerics.sqrt
object Main {
   def main(args: Array[String]): Unit = {
     val oneover = Complex(1/Math.sqrt(2),0.0)
     val hadamard = new DenseMatrix[Complex](2, 2, Array(
        oneover, oneover,
        oneover, -oneover
     ))
     val wa = new BraidOptimizer(hadamard,1e-4)
     println(wa.optimize())
   }


}
