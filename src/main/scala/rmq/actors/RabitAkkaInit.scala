package rmq.actors

import akka.actor.ActorSystem.Settings
import akka.actor._
import akka.dispatch.{Dispatchers, Mailboxes}
import akka.event.{LoggingAdapter, EventStream}
import akka.routing.RoundRobinPool
import com.rabbitmq.client.Channel
import rmq.rabbit.{RabbitSupport, RabbitConnector}

import scala.concurrent.duration.Duration
import scala.concurrent.{Future, ExecutionContextExecutor}

/**
 * Created by fridlyos on 10/3/15.
 */
object RabitAkkaInit extends App with RabbitSupport {


  // Initialize Akka System
  val akka = ActorSystem("rabbit")
  val actor = akka.actorOf(Props(classOf[RabbitActor]))

  createActors()
  sendMessage("this is  a first message")

  def createActors() : Unit = {
    val router = akka.actorOf(RoundRobinPool(10).props(Props(classOf[RabbitActor])), "rabbit-actor")
    println(router.path)
  }

  def sendMessage(msg : String) : Unit = {
    val actor = akka.actorSelection("/user/rabbit-actor*")
    actor ! RabitMessage(msg)
  }

}
