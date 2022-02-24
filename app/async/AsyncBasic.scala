package com.particeep.test.async

import scala.concurrent.Future

import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.Failure
import scala.util.Success

/**
 * You have 2 webservices, we want to compute the sum of the 2 webservice call.
 *
 * You need to write only the compute function.
 * For instance : compute(1) should return 1099 (1098 + 1)
 *
 * It's part of the exercise to handle error cases
 */
object AsyncBasic {

  def compute(id: String) = {
    val wbsrvc1 = Webservice1.call(id)
    val wbsrvc2 = Webservice2.call(id)
    val result : Future[(Option[Int],Either[String,Int])] = for{
      res1 <- wbsrvc1
      res2 <- wbsrvc2
    }yield (res1,res2)
    result.onComplete{
      case Success(value) => {
        //We assume that only the computation of the value is important error are ignored
        val res =value._1.getOrElse(0) + value._2.getOrElse(0)
        println("The computed sum is %d".format(res))
      }
      case Failure(exception) => exception.printStackTrace()
    }
  }

}

object Webservice1 {
  private[this] val result = Map(
    "1"  -> 1,
    "2"  -> 21,
    "5"  -> 4,
    "10" -> 1987
  )

  def call(id: String): Future[Option[Int]] = Future(result.get(id))
}

object Webservice2 {
  private[this] val result = Map(
    "1"  -> 1098,
    "3"  -> 218777,
    "9"  -> 434,
    "10" -> Int.MaxValue
  )

  def call(id: String): Future[Either[String, Int]] = Future {
    result.get(id) match {
      case Some(x) => Right(x)
      case None    => Left("No value")
    }
  }
}
