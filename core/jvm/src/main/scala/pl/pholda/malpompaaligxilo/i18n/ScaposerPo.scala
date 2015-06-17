package pl.pholda.malpompaaligxilo.i18n

case class ScaposerPo(po: scaposer.Po) extends Po {

  override def t(singular: String): String = po.t(singular)

  override def t(ctx: String, singular: String): String = po.t(ctx, singular)

  override def t(singular: String, plural: String, n: Long): String = po.t(singular, plural, n)

  override def t(ctx: String, singular: String, plural: String, n: Long): String = po.t(ctx, singular, plural, n)
}
