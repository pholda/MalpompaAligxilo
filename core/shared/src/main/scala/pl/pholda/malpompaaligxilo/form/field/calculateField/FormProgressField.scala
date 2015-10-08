package pl.pholda.malpompaaligxilo.form.field.calculateField

import pl.pholda.malpompaaligxilo.form.FormExpr

import scala.util.Try

case object FormProgressField extends ProgressField {
  override def max: FormExpr[Int] = FormExpr{ form =>
    form.fields.count { f =>
      f.visible(form) && f.store && !f.isComputed
    }
  }

  override def value: FormExpr[Int] = FormExpr{implicit form =>
    form.fields.count{f =>
      Try {
          f.visible(form) && f.store && !f.isComputed && f.value(form).nonEmpty
      }.getOrElse(false)
    }
  }

  override def separatedValues(value: Option[Int]): List[(String, String)] = ???
}
