package com.particeep.test.akka

import akka.actor.{ Actor, ActorSystem, Props }
import akka.actor.typed.Behavior
import akka.actor.typed.scaladsl.Behaviors
import akka.actor.AbstractActor
import akka.actor.typed.javadsl
import akka.actor.ActorContext
import com.particeep.test.basic

/**
 * Question about Akka framework http://akka.io
 *
 * When receiving a message that says "Hello", BasicActor must print "Hello there."
 * It must print "What?" when receiving any other message
 */
object  BasicActor {


  trait  BasicMessage
  case class Hello() extends BasicMessage
  case class Other() extends BasicMessage
  def apply(): Behavior[BasicMessage] = Behaviors.receive{
    (context,message) =>{
      message match {
        case Hello() => context.log.info("Hello there")
        case Other() => context.log.info("What ?")
      }
      Behaviors.same
    }
  }
}

object FireActor extends App {

  /**
   * Create an instance of BasicActor
   *
   * Make it print "Hello there." and "What?"
   */
  // def fireActor(): Unit = {
      //  val system = ActorSystem("Actor System")
      //  val basic_actor = system.actorOf(Props[BasicActor.BasicMessage](), name = "basic actor")
  // }
  val basicActor : ActorSystem[BasicActor.BasicMessage] = ActorSystem(BasicActor(), "basic")

  basicActor ! Hello()
  basicActor ! BasicActor.Other()
}
