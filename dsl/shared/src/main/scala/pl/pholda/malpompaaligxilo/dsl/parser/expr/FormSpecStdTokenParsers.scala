package pl.pholda.malpompaaligxilo.dsl.parser.expr

import pl.pholda.malpompaaligxilo.dsl.parser.FormSpecificationLexical

import scala.util.parsing.combinator.PackratParsers
import scala.util.parsing.combinator.syntactical.StandardTokenParsers

trait FormSpecStdTokenParsers extends StandardTokenParsers with PackratParsers {

  override val lexical: FormSpecificationLexical = new FormSpecificationLexical()
}
