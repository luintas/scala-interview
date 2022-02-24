package com.particeep.test.basic

/**
 * What is the complexity of the function ?
 *
 * Refactor it to be a better function
 */
object Refactoring {

  case class File(
    name:     String,
    category: String
  )

  def originalgetCategories(files: List[File]): List[String] = {
    val categories: List[String] = List()

    if(files != null) {
      for(file <- files) {
        if(file.category != null && !categories.contains(file.category)) {
          categories :+ file.category
        }
      }
    }

    return categories
  }
  def easyRefactorGetCategories(files: List[File]): List[String] = {
    var categories: Map[String, Char] = Map[String, Char]()
    if(files != null) {
      for(file <- files) {
        if(file.category != null) {
          categories += (file.category -> '0')
        }
      }
    }
    return categories.keys.toList
  }
  def otherRefactorgetCategories(files: List[File]): List[String] = {
    val categories: List[String] = List()
    return files.filter(_.category == null).map(_.category).distinct
  }

}
