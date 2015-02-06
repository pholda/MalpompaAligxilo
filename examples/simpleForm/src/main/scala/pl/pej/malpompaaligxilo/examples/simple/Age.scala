package pl.pej.malpompaaligxilo.examples.simple

case class Age(age: Int) extends AnyVal {
  override def toString: String = s"$age years"
}
