import scala.math.sqrt
import ImaginaryExtention._
object Main {
   def main(args: Array[String]): Unit = {
     val a = Qubit.Zero
     val b = Qubit.One
     val c = Qubit.Plus
     val d = Qubit.Minus
     val e = Qubit(3.0.i,2.0).normalize
     val av = (Ket(1+2.i,0+2.i)**Ket(0+1.i,0+2.i)**Ket(1.i,2.i)**Ket(2,1))
     println(av)
     println(a)
     println(b)
     println(c)
     println(d)
     println(e)
   }


}
