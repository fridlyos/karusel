import samples.{ApplicativeSample, ListSample}

/**
 * Created by fridlyos on 9/23/15.
 */
object InitController {

  def main(args : Array[String]): Unit = {

    println("this is a test")
    ListSample.list1()
    ApplicativeSample.app1()



    val v42 = 42
    Some(3) match {
      case Some(v42) => println(v42); println("42")
      case _ => println("Not 42")
    }



    val tt = TT("sldkfjslf", "sldfjlsdkjf")

    tt.b

  }

}

case class TT(private val a : String, var b : String)
