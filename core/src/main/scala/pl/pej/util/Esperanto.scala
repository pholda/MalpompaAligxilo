package pl.pej.util

import scala.annotation.tailrec

object Esperanto {
  implicit lazy val ordering: Ordering[String] = new Ordering.StringOrdering {
    //TODO optimize, on the other hand it won't work correctly

    private val xsystem = Map(
      "ĉ" -> "cx",
      "ĥ" -> "hx",
      "ĝ" -> "gx",
      "ĵ" -> "jx",
      "ŝ" -> "sx",
      "ŭ" -> "ux",
      "Ĉ" -> "Cx",
      "Ĥ" -> "Hx",
      "Ĝ" -> "Gx",
      "Ĵ" -> "Jx",
      "Ŝ" -> "Sx",
      "Ŭ" -> "Ux"

    )

    private def convert2X(str: String): String = {
      @tailrec
      def _conv(str: String, chars: List[(String, String)]): String = {
        chars match {
          case (o, n) :: t =>
            _conv(str.replace(o, n), t)
          case Nil =>
            str
        }
      }
      _conv(str, xsystem.toList)
    }

    override def compare(x: String, y: String): Int = {
      convert2X(x).compareTo(convert2X(y))
    }
  }
}
