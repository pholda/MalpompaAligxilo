package pl.pej.malpompaligxilo.jes2015

import pl.pej.malpompaligxilo.jes2015.Jes2015Kotizo.Euroj

case class Kotizo(kotizoj: Map[String, Euroj], sumo: Euroj) {
  override def toString: String = str

  def str: String = (kotizoj map {
    case (priskribo, prezo) =>
      s"$priskribo: $prezo"
  }).mkString(", ")
}

object Kotizo {
  def apply(kotizoj: Map[String, Euroj]): Kotizo = Kotizo(kotizoj, kotizoj.values.sum)
}
