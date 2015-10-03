package rmq.actors

import akka.actor.Actor
import akka.actor.Actor.Receive
import rmq.actors.RabitAkkaInit._

/**
 * Created by fridlyos on 10/3/15.
 */
class RabbitActor extends Actor {
  override def receive: Receive = {
    case RabitMessage(msg) => {
      withRabbit {
        println("publishing....")
        publish(msg)
      }
    }
  }
}


case class RabitMessage(msg : String)