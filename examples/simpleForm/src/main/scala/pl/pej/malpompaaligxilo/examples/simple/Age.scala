package pl.pholda.malpompaaligxilo.examples.simple

case class Age(age: Int) extends AnyVal {
  override def toString: String = s"$age years"
}
