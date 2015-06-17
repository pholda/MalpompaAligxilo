package pl.pholda.malpompaaligxilo.form

import pl.pholda.malpompaaligxilo.ContextJS
import pl.pholda.malpompaaligxilo.angular.FormScope
import pl.pholda.malpompaaligxilo.form.field.CheckboxField

class FormInstanceJS(
  specification: FormSpecification,
  scope: FormScope
                      )(
  implicit val context: ContextJS
  ) extends FormInstance(specification) {

  override protected def getRawFieldValue(field: Field[_]): Seq[String] = {
    field.`type`.arrayValue match {
        //TODO check this part
      case true =>
//        val checked = new ListBuffer[String]
//        jQuery(s"[name='$field[]']:checked").each{(a: js.Any, e: Element) =>
//          checked.append(jQuery(e).`val`().asInstanceOf[String])
//          a
//        }
//        checked.toSeq
        Seq()
      case false =>
        field.`type` match {
          case CheckboxField(default) =>
            scope.field.get(field.name) match {
              case Some(true) =>
                Seq("true")
              case _ =>
                Seq.empty
            }
          case _ =>
            scope.field.get(field.name).map(x => Seq(x.toString)).getOrElse(Seq.empty)
        }
    }

  }
}
