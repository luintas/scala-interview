package com.particeep.test.akka

import akka.actor.Actor

import scala.concurrent.Future
import scala.util.{ Failure, Success }
import scala.concurrent.ExecutionContext.Implicits.global

/**
 * Do you see anything that could lead to potential problems ?
 * What would you do to fix it ?
 * Do not mind about the not implemented code
 */

/* This doesn't respect the Actor model we shouldn't have an internal State put rather reason through modification of behaviours 
  currently the state can be mutated during it's execution of queryAsyncServer which introduce undefined behaviour */
class WhatsWrong3 extends Actor {

  var internalState = "internal state"

  def receive: Receive = {
    case "a query" => {
      val requestF: Future[String] = queryAsyncServer()
      requestF.onComplete {
        case Success(r) => handleResponse(r)
        case Failure(e) => e.printStackTrace()
      }
    }
  }

  def handleResponse(r: String) = ??? // mutate internal state

  def queryAsyncServer(): Future[String] = ???
}
