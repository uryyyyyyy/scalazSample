package com.github.uryyyyyyy.scalaz.either

import scalaz.Scalaz._
import scalaz._

case class User(id: Int,
  firstName: Option[String],
  lastName: Option[String]
)

object MyTransformer {

//  implicit def toOptionT[A](either: \/[String, A]): OptionT[EitherString, A] = {
//    either.liftKleisli[OptionT]
//  }
//
//  implicit def toOptionT[A](op: Option[A]): OptionT[EitherString, A] = {
//    val s: EitherString[Option[A]] = op.point[EitherString]
//    OptionT.optionT(s)
//  }
}

object TransformerMain {

  type EitherString[A] = \/[String, A]

  import MyTransformer._

  val multiple = (a: Int, b: Int) => a * b

  val add = (a: Int, b: Int) => a * b

  val cons: List[Int] = List(1, 2, 3)
  val nil: List[Int] = Nil
  val some: Option[Int] = Some(2)
  val none: Option[Int] = None
  val left: \/[String, Int] = -\/("error")
  val right: \/[String, Int] = \/-(3)

  def main(args: Array[String]) {
    val result1: List[Option[Int]] = OptionTransformer.listOption(cons, some, multiple).run
    println(result1)

    val result2: List[Option[Int]] = OptionTransformer.listOption(cons, none, multiple).run
    println(result2)

    val result3: List[Option[Int]] = OptionTransformer.listOption(nil, some, multiple).run
    println(result3)



    val result4: Option[\/[String, Int]] = EitherTransformer.eitherOption(left, some, multiple).run
    println(result4.right.getOrElse(100))
    println(result4.left)
    println(result4.getOrElse(0))

    val result5: Option[\/[String, Int]] = EitherTransformer.eitherOption(right, some, multiple).run
    println(result5.right)
    println(result5.left)
    println(result5.getOrElse(0))

  }

}

object OptionTransformer {

  implicit def toOptionT[A](op: Option[A]): OptionT[List, A] = {
    val s: List[Option[A]] = op.point[List]
    OptionT.optionT(s)
  }

  implicit def toOptionT[A](list: List[A]): OptionT[List, A] = {
    list.liftM[OptionT]
  }


  def listOption(list:List[Int], option: Option[Int], f: (Int, Int) => Int): OptionT[List, Int] = for {
    a <- list
    s <- option
  }yield{
    a * s
  }
}

object EitherTransformer {

  implicit def toEitherT[L, R](either: \/[L, R]): EitherT[Option, L, R] = {
    val s: Option[\/[L, R]] = either.point[Option]
    EitherT.eitherT(s)
  }

  implicit def toEitherT[L, R](op: Option[R]): EitherT[Option, L, R] = {
    EitherT.right[Option, L, R](op)
  }

  def eitherOption(either: \/[String, Int], option: Option[Int], f: (Int, Int) => Int): EitherT[Option, String, Int] = for {
    s <- toEitherT(option)
    a <- toEitherT(either)
  }yield{
    a * s
  }
}