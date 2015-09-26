package samples

import scalaz._
import Scalaz._

case class Foo(s: String, n: Int)

object ApplicativeSample {

  def app1(): Unit = {

    val res = ( getName("test") |@| getAge("test") )(Foo(_,_))
    println(res)


  }


  def getName(arg : String) : List[String] = {
    List(s"Name=$arg")
  }

  def getAge(arg : String) : List[Int] = List.empty


}



