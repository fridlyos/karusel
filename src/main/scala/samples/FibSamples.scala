package samples

/**
 * Created by fridlyos on 9/26/15.
 */
object FibSamples extends App {


  def calcFib(f : Int, f1 : Int) : Stream[Int] = {
    Stream.cons(f1, calcFib(f1, f + f1))
  }

  val zz = calcFib(0,1).take(5)
  zz.foreach(println)

}
