package pl.pholda.malpompaaligxilo.dsl.parser.expr

import pl.pholda.malpompaaligxilo.dsl.DslFormExpr
import pl.pholda.malpompaaligxilo.dsl.expr.checkboxtable.SelectedInTotal
import pl.pholda.malpompaaligxilo.dsl.parser.UtilParsers

import scala.util.parsing.combinator.syntactical.StandardTokenParsers

trait CheckboxTableExprParser extends StandardTokenParsers with UtilParsers {

  lexical.reserved +=("selectedInTotal")

  lexical.delimiters +=("(", ")")

  def checkboxTableExpr: Parser[DslFormExpr[_]] = selectedInTotal

  protected[dsl] def selectedInTotal: Parser[SelectedInTotal] = "selectedInTotal" ~> "(" ~> expr <~ ")" ^^ SelectedInTotal
}
