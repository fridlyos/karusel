package spray_sample

import akka.actor.Actor
import spray.routing._
import spray.http._
import MediaTypes._
import akka.actor.{ActorSystem, Props}
import akka.io.IO
import spray.can.Http
import akka.pattern.ask
import akka.util.Timeout
import scala.concurrent.duration._
import scalaz._
import Scalaz._



class ConfigServiceActor extends Actor with ConfigService {

  def actorRefFactory = context
  def receive = runRoute(keyValPairRoute)

}



/**
 * simple service ton convert uri parms into key-value response
 * invoked
 * localhost:8080/conf?app=test&ver=1.0&lang=English&lcl=Eng
 *                      localhost:8080/conf?app=test&ver=1.0&lang=English&lcl=Eng
 */
trait ConfigService extends HttpService with CasePrinter {

  val keyValPairRoute =
    path("conf") {
      get {

        parameters('app, 'ver, 'lang, 'lcl) {
          (appName, version, lang, locale) => {
            ConfPrinter(appName, version, lang, locale) |> {  cp =>
              respondWithMediaType(`text/html`) {
                complete {
                  <html><body>{print(cp).map(kv => s"${kv._1}=${kv._2}").mkString("; ")}</body></html>
                }
              }
            }
          }
        }
      }
    }
}


case class ConfPrinter(appName : String, version : String, language : String, locale : String)

trait CasePrinter {
  def print(o: Any): Map[String, Any] = {
    val fieldsAsPairs = for (field <- o.getClass.getDeclaredFields) yield {
      field.setAccessible(true)
      (field.getName, field.get(o))
    }
    Map(fieldsAsPairs :_*)

  }
}



/**
 * simple boot - no config provided - all defaults
 */
object Boot extends App {

  implicit val system = ActorSystem("configSrv")
  implicit val timeout = Timeout(5.seconds)

  val service = system.actorOf(Props[ConfigServiceActor], "config-srv")

  IO(Http) ? Http.Bind(service, interface = "localhost", port = 8080)

}
