package rmq.rabbit

import com.rabbitmq.client.Channel
import com.rabbitmq.client.ConnectionFactory

/**
 * Created by fridlyos on 10/3/15.
 */
object RabbitConnector {

  lazy val conn = getRabbit()
  lazy val channel = conn.createChannel()
  lazy val queue = channel.queueBind("test1", "amq.topic", "")

  def getRabbit() = {
    val factory = new ConnectionFactory();
    factory.setUsername("admin");
    factory.setPassword("admin");
//    factory.setVirtualHost(virtualHost);
    factory.setHost("fridlyos-DELL");
    factory.setPort(5672);
    val conn = factory.newConnection();
    conn
  }

}

//channel.basicPublish("amq.topic", "", null, s"this is at test${i}".getBytes

trait RabbitSupport {



  def withRabbit[T]( f : Channel => T) : T = {
    val ch = RabbitConnector.channel
    f(ch)
  }

  def publish(msg : String)(c : Channel) : Unit = {
    c.basicPublish("amq.topic", "", null, msg.getBytes)
  }
}