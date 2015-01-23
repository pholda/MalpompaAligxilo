package pl.pej.malpompaaligxilo.jes2015

import pl.pej.malpompaaligxilo.jes2015.Jes2015Kotizo.Euroj

import scala.collection.immutable.ListMap

case class Kotizo(kotizoj: ListMap[String, Euroj], sumo: Euroj) {
  override def toString: String = str

  def str: String = (kotizoj.filterNot{case (priskribo, prezo) => prezo == 0} map {
    case (priskribo, prezo) =>
      s"${priskribo}:&nbsp;%.2f€".format(prezo)
  }).mkString(", ") + s"</br><strong>Sume:</strong>&nbsp;%.2f€".format(sumo)
}

object Kotizo {
  def apply(kotizoj: ListMap[String, Euroj]): Kotizo = Kotizo(kotizoj, kotizoj.values.sum)
}
