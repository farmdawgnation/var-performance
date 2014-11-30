package me.frmr.lift

import net.liftweb.common._
import net.liftweb.util._
import net.liftweb.http._
import net.liftweb.mockweb._

object PerformanceTester extends Factory {
  val accesses = 1000000

  object requestVarTest extends RequestVar[String]("abc123")
  object transientRequestVarTest extends TransientRequestVar[String]("abc123")
  object sessionVarTest extends SessionVar[String]("abc123")

  def average(rawNumbers: => List[Long]): Long = {
    rawNumbers.sum / rawNumbers.length
  }

  def main(args: Array[String]) {
    println("Testing performance of var access vurses factory maker access.")

    MockWeb.testS("/", Empty, "") {
      val rvDuration = average {
        (0 to 10).map { _=>
          val rvStart = System.currentTimeMillis
          (0 to accesses).foreach { _ => requestVarTest.is }
          val rvEnd = System.currentTimeMillis
          rvEnd - rvStart
        } toList
      }

      val trvDuration = average {
        (0 to 10).map { _ =>
          val trvStart = System.currentTimeMillis
          (0 to accesses).foreach { _ => transientRequestVarTest.is }
          val trvEnd = System.currentTimeMillis
          trvEnd - trvStart
        } toList
      }

      val svDuration = average {
        (0 to 10).map { _ =>
          val svStart = System.currentTimeMillis
          (0 to accesses).foreach { _ => sessionVarTest.is }
          val svEnd = System.currentTimeMillis
          svEnd - svStart
        } toList
      }

      println("\nResults:")
      println(s"Request var test: $rvDuration")
      println(s"Transient request var test: $trvDuration")
      println(s"Session var test: $svDuration")
    }
  }
}
