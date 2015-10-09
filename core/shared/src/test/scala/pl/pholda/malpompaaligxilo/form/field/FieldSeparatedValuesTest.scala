package pl.pholda.malpompaaligxilo.form.field

import pl.pholda.malpompaaligxilo.Context
import pl.pholda.malpompaaligxilo.form.field.calculateField.cost.CostDef.MultipleCostDef
import pl.pholda.malpompaaligxilo.form.field.calculateField.cost.CostValue.MultipleCostValue
import pl.pholda.malpompaaligxilo.form.field.calculateField.cost.CostsField
import pl.pholda.malpompaaligxilo.form.{Field, FormExpr}
import pl.pholda.malpompaaligxilo.i18n.NoI18nString
import utest._

trait FieldSeparatedValuesTest extends TestSuite {
  implicit def context: Context

  val tests = TestSuite{
    'string{
      val field = StringField()
      assert(field.separatedValues(Some("abc")) == "" -> "abc" :: Nil)
    }
    'int{
      val field = IntField()
      assert(field.separatedValues(Some(123)) == "" -> "123" :: Nil)
    }
    'email{
      val field = EmailField
      assert(field.separatedValues(Some("abc@abc")) == "" -> "abc@abc" :: Nil)
    }
    'checkbox{
      val field = CheckboxField()
      assert(field.separatedValues(Some(true)) == "" -> "x" :: Nil)
    }
    'dateField{
      val field = DateField()
      assert(field.separatedValues(Some(context.date.fromString("1991-05-06"))) ==
        "" -> "1991-05-06" :: Nil
      )
    }
    'checkboxTableField{
      'multipleRowsMultipleCols{
        val field = CheckboxTableField(
          rows = CheckboxTableRow("a", "a") :: CheckboxTableRow("b", "b") :: Nil,
          cols = CheckboxTableCol("1", "1") :: CheckboxTableCol("2", "2") :: Nil
        )
        val result = CheckboxTableField.Result(Set(
          CheckboxTableRow("a", "a") -> CheckboxTableCol("2", "2"),
          CheckboxTableRow("a", "a") -> CheckboxTableCol("1", "1")
        ))
        assert(field.separatedValues(Some(result)) ==
          "a-2" -> "x" :: "a-1" -> "x" :: Nil )
      }
      'oneCol{
        val field = CheckboxTableField(
          rows = CheckboxTableRow("a", "a") :: CheckboxTableRow("b", "b") :: Nil,
          cols = CheckboxTableCol("1", "1") :: Nil
        )
        val result = CheckboxTableField.Result(Set(
          CheckboxTableRow("a", "a") -> CheckboxTableCol("1", "1"),
          CheckboxTableRow("b", "b") -> CheckboxTableCol("1", "1")
        ))
        assert(field.separatedValues(Some(result)) ==
          "a" -> "x" :: "b" -> "x" :: Nil )
      }
      'oneRow{
        val field = CheckboxTableField(
          rows = CheckboxTableRow("a", "a") :: Nil,
          cols = CheckboxTableCol("1", "1") :: CheckboxTableCol("2", "2") :: Nil
        )
        val result = CheckboxTableField.Result(Set(
          CheckboxTableRow("a", "a") -> CheckboxTableCol("1", "1"),
          CheckboxTableRow("a", "a") -> CheckboxTableCol("2", "2")
        ))
        assert(field.separatedValues(Some(result)) ==
          "1" -> "x" :: "2" -> "x" :: Nil )
      }
    }
    'selectField{
      val field = SelectField(
        options = EnumOption("1", "first") :: EnumOption("2", "second") :: Nil
      )
      assert(field.separatedValues(Some(EnumOption("1", "first"))) ==
        "" -> "1" :: Nil
      )
    }
    'computedField{
      'string{
        val field = new ComputeField[String] {
          override protected def formula: FormExpr[Option[String]] = FormExpr(form => Some("abc"))
        }
        assert(field.separatedValues(Some("abc")) == "" -> "abc" :: Nil)
      }
    }
    'costsField{
      val field = CostsField(
        definition = MultipleCostDef("b", NoI18nString("b"), 10, FormExpr{5}),
        {costValue => NoI18nString("")}
      )
      assert(field.separatedValues(Some(MultipleCostValue("b", NoI18nString("b"), 10, 5))) ==
        "" -> "50.0" :: Nil
      )
    }
  }
}
