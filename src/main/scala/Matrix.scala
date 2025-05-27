class Matrix(val rows: Int, val cols: Int)(val data: Array[Array[Double]] = Array.ofDim(rows, cols)) {
  if (data.length != rows) throw new Exception("Passed array has incorrect row dimensions")
  else data.foreach(row => if (row.length != cols) throw new Exception("Passed array has incorrect column dimensions"))

  def this(copy: Matrix) = this(copy.rows, copy.cols)(copy.data)

  def transpose: Matrix = {
    val newData: Array[Array[Double]] = Array.ofDim(cols, rows)

    for (row <- 0 until rows)
      for (col <- 0 until cols)
        newData(col)(row) = data(row)(col)

    new Matrix(cols, rows)(newData)
  }

  def traverse(visit: Double => Unit): Unit = data.flatten.foreach(f => visit(f))

  def transform(visit: Double => Double): Matrix = new Matrix(rows, cols)(data.map(_.map(f => visit(f))))

  def transformRow(rowPassed: Int, visit: Double => Double): Matrix = {
    val newData: Array[Array[Double]] = data.zipWithIndex.map(ell => if (ell._2 == rowPassed) ell._1.map(f => visit(f)) else ell._1)
    new Matrix(rows, cols)(newData)
  }

  def RowSwap(rowA: Int, rowB: Int): Matrix = {
    if (rowA >= rows || rowB >= rows || rowA < 0 || rowB < 0) throw new Exception("Specified row out of bounds")

    val newData: Array[Array[Double]] = data.clone()
    newData(rowA) = data(rowB)
    newData(rowB) = data(rowA)

    new Matrix(rows, cols)(newData)
  }

  def reduceSum(): Double = data.flatten.sum

  def printMat(): Unit = for (list <- data) {
    for (item <- list) print("[" + item + "]"); println()
  }

  def index(row: Int, col: Int): Double = {
    if (row >= rows || row < 0 || col >= cols || col < 0) throw new Exception("Index out of bounds")
    data(row)(col)
  }

  def getSize(): Array[Int] = Array(rows, cols)
}


object Matrix {
  def +(lvalue: Matrix, rvalue: Matrix): Matrix = {
    if (lvalue.rows != rvalue.rows || lvalue.cols != rvalue.cols)
      throw new Exception("Cannot subtract matrices of differing degrees")

    new Matrix(lvalue.rows, lvalue.cols)((lvalue.data zip rvalue.data).map(row => row._1 zip row._2).map(_.map(tup => tup._1 + tup._2)))
  }

  def -(lvalue: Matrix, rvalue: Matrix): Matrix = {
    
      throw new Exception("Cannot subtract matrices of differing degrees")

    new Matrix(lvalue.rows, lvalue.cols)((lvalue.data zip rvalue.data).map(row => row._1 zip row._2).map(_.map(tup => tup._1 - tup._2)))
  }

  def *(lvalue: Matrix, rvalue: Matrix): Matrix = {
    if (lvalue.cols != rvalue.rows) throw new Exception("cross product lvalue columns must equal rvalue rows")

    val newData: Array[Array[Double]] = Array.ofDim(lvalue.rows, rvalue.cols)

    for (lrow <- 0 until lvalue.rows)
      for (rcol <- 0 until rvalue.cols)
        for (lcol <- 0 until lvalue.cols)
          newData(lrow)(rcol) += lvalue.data(lrow)(lcol) * rvalue.data(lcol)(rcol)

    new Matrix(lvalue.rows, rvalue.cols)(newData)
  }

  def ==(lvalue: Matrix, rvalue: Matrix): Boolean = {
    if (lvalue.rows != rvalue.rows || lvalue.cols != rvalue.cols) false
    else lvalue.data.flatten sameElements rvalue.data.flatten
  }

  def identity(dim: Int): Matrix = {
    val newData: Array[Array[Double]] = Array.ofDim(dim, dim)

    for (i <- 0 until dim) newData(i)(i) = 1

    new Matrix(dim, dim)(newData)
  }
}