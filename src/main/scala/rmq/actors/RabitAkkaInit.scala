package rmq.actors

import akka.actor.ActorSystem.Settings
import akka.actor._
import akka.dispatch.{Dispatchers, Mailboxes}
import akka.event.{LoggingAdapter, EventStream}
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

  val conn = RabbitConnector.getRabbit()


  withRabbit {
    publish("sldkjfsldkjf")
  }



}
