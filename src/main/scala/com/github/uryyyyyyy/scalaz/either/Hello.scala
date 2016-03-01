package com.github.uryyyyyyy.scalaz.either

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scalaz.\/
import scalaz.\/._

object Hello {
	def main(args: Array[String]): Unit = {
		println("Hello, world!")
		val ss = Future("hello".hashCode).map {
			case i: Int => right(i)
			case _ => left(s"future error")
		}.mapTo[\/[String, Int]]
		ss.map(v => v.map(u => println(u)))
		ss.map(v => v.leftMap(u => println(u)))

		val f = Future("hello".hashCode)
		f.map(v => println("aa" + v))
		Thread.sleep(2000)
	}
}
