package pl.pej.malpompaaligxilo.form.field.calculateField

import pl.pej.malpompaaligxilo.form.FormExpr

import scala.util.Try

case object FormProgressField extends ProgressField {
  override def max: FormExpr[Int] = { form =>
    form.fields.count { f =>
      f.visible(form) && f.store && !f.isCalculate
    }
  }

  override def value: FormExpr[Int] = {implicit form =>
    form.fields.count{f =>
      Try {
          f.visible(form) && f.store && !f.isCalculate && f.value(form).nonEmpty
      }.getOrElse(false)
    }
  }

}
