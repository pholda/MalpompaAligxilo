package pl.pej.malpompaligxilo.jes2015

import pl.pej.malpompaligxilo.jes2015.Jes2015Kotizo.Euroj

import scala.collection.immutable.ListMap

case class Kotizo(kotizoj: ListMap[String, Euroj], sumo: Euroj) {
  override def toString: String = str

  def str: String = (kotizoj map {
    case (priskribo, prezo) =>
      s"$priskribo: $prezo"
  }).mkString("</br>") + s"</br><strong>Sume:</strong> %.2f".format(sumo)
}

object Kotizo {
  def apply(kotizoj: ListMap[String, Euroj]): Kotizo = Kotizo(kotizoj, kotizoj.values.sum)
}
