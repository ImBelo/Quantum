import scala.math.sqrt

object Main {
   def main(args: Array[String]): Unit = {
     val a = Qubit(Complex(1/sqrt(2)),Complex(-1/sqrt(2)))
     val b = Qubit(Complex(0.0),Complex(-1/sqrt(2)))
     val c = Qubit(Complex(-1/sqrt(2),1),Complex(-1/sqrt(2)))
     println(a.toString)
     println(b.toString)
     println(c.toString)
   }


}
