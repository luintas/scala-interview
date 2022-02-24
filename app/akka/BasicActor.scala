package com.particeep.test.akka

import akka.actor.{ Actor, ActorSystem, Props }

trait BasicMessage
case class Hello() extends BasicMessage
case class Other() extends BasicMessage

/**
 * Question about Akka framework http://akka.io
 *
 * When receiving a message that says "Hello", BasicActor must print "Hello there."
 * It must print "What?" when receiving any other message
 */
class BasicActor extends Actor {

  def receive: Receive = {
    case Hello() => println("Hello there")
    case Other() => println("What ?")
  }
}

object FireActor extends App {

  /**
   * Create an instance of BasicActor
   *
   * Make it print "Hello there." and "What?"
   */
  def fireActor(): Unit = {
    val system      = ActorSystem("Actor_System")
    val newValue    = Props[BasicActor]()
    val basic_actor = system.actorOf(newValue, "basic_actor")
    basic_actor ! Hello()
    basic_actor ! Other()
  }
  fireActor()
}
