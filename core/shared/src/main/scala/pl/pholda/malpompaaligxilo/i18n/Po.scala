package pl.pholda.malpompaaligxilo.i18n

abstract class Po {
  def t(singular: String): String

  def t(ctx: String, singular: String): String

  def t(singular: String, plural: String, n: Long): String

  def t(ctx: String, singular: String, plural: String, n: Long): String

  def t(singular: String, ctx: Option[String], plural: Option[String], n: Option[Long]): String = {
    ctx match {
      case Some(ctx) =>
        (plural, n) match {
          case (Some(plural), Some(n)) =>
            t(ctx, singular, plural, n)
          case _ =>
            t(ctx, singular)
        }
      case None =>
        (plural, n) match {
          case (Some(plural), Some(n)) =>
            t(singular, plural, n)
          case _ =>
            t(singular)
        }
    }
  }
}
