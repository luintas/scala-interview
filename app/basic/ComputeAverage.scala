package com.particeep.test.basic

/**
 * Compute the average of the list
 *
 * ex : [1, 10, 16] -> 9
 */
object ComputeAverage {

  def average(l: List[Double]) = {
    l.sum / l.length
  }

}
