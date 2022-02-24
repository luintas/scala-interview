package com.particeep.test.basic

import com.particeep.test.async.AsyncBasic

/**
 * This is basic language questions so don't use external library or build in function
 */
object BasicScala {

  /**
   * Encode parameter in url format
   *
   * Example:
   *
   * input  : Map("sort_by" -> "name", "order_by" -> "asc", "user_id" -> "12")
   * output : "?sort_by=name&order_by=asc&user_id=12"
   *
   * input  : Map()
   * output : ""
   */
  def encodeParamsInUrl(params: Map[String, String]): String = {
    "?" + recEncodeParamsInUrl(params).substring(1)
  }
  def recEncodeParamsInUrl(params: Map[String, String]): String = {
    if(params.isEmpty) {
      return ""
    }
    var head   = params.head
    val retVal = "&" + head._1 + "=" + head._2 + recEncodeParamsInUrl(params.removed(head._1))
    retVal
  }

  /**
   * Test if a String is an email
   */
  def isEmail(maybeEmail: String): Boolean = {
    // Pattern conditions are slightly more complex it need to add that first dns domain can't be all numerical
    val pattern =
      "[\\!-\\'\\--9\\^-\\~A-Z\\?\\=\\*\\+]{1,64}@([\\w0-9][\\!-\\'\\--9\\^-\\~A-Z\\?\\=\\*\\+]{0,61}[\\w0-9]{0,1}\\.){1,3}[\\w0-9][\\!-\\'\\--9\\^-\\~A-Z\\?\\=\\*\\+]{0,61}[\\w0-9]{0,1}".r
    pattern.matches(maybeEmail)
  }

  /**
   * Compute i ^ n
   *
   * Example:
   *
   * input : (i = 2, n = 3) we compute 2^3 = 2x2x2
   * output : 8
   *
   * input : (i = 99, n = 38997)
   * output : 1723793299
   */
  def power(i: Int, n: Int): Int = {
    if(n <= 0) {
      return 1
    }
    println("n = %d".format(n))
    i * power(i, n - 1)
  }

}

object main extends App {

  val encodeTest = Map("sort_by" -> "name", "order_by" -> "asc", "user_id" -> "12")
  assert(BasicScala.encodeParamsInUrl(encodeTest) == "?sort_by=name&order_by=asc&user_id=12")
  println("Encode Passe")

  assert(BasicScala.isEmail("nell.flaharty@gmail.com"))
  println("Passe")
  assert(!BasicScala.isEmail("nell.flaharty@.com"))
  println("Passe1")
  assert(!BasicScala.isEmail("@gmail.com"))
  println("Passe2")
  assert(BasicScala.isEmail("nell.flaharty3@gmail.com"))
  println("Passe3")
  assert(!BasicScala.isEmail("nell.flaharty@gmail"))

  assert(BasicScala.power(2, 3) == 8)
  println("Power passed")
  assert(ComputeAverage.average(List[Double](1, 10, 16)) == 9)
  println("Average passed")
  AsyncBasic.compute("1")
  AsyncBasic.compute("2")
  AsyncBasic.compute("5")
  AsyncBasic.compute("3")
  AsyncBasic.compute("9")
  AsyncBasic.compute(
    "10"
  ) // Undefined behaviour, will return MIN_DOUBLE but there's nothing to tell how we're supposed to handle that
}
