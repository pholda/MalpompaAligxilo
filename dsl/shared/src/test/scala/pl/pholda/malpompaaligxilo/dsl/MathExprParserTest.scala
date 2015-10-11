package pl.pholda.malpompaaligxilo.dsl

import pl.pholda.malpompaaligxilo.dsl.parser.expr.FormExprParser
import pl.pholda.malpompaaligxilo.form.FormInstance
import pl.pholda.malpompaaligxilo.i18n.TranslationProvider
import pl.pholda.malpompaaligxilo.util.DateCompanion
import utest._

trait MathExprParserTest extends TestSuite with ParserTestHelper[FormExprParser] {
   def testForm: TestForm

   override val parsers = new FormExprParser {
     override implicit val translationProvider: TranslationProvider = testForm.context.translationProvider
     override implicit val dateCompanion: DateCompanion = testForm.context.date
   }


   val tests = TestSuite {

     def form: FormInstance[_] = testForm.form
     import parsers._

     'add{
       val expr = """ 2+2 """
       assertMatch(quickParse(mathExpr, expr)(testForm.form)){
         case 4 =>
       }
     }
     'subtract{
       val expr = """ 5 - 10 """
       assertMatch(quickParse(mathExpr, expr)(testForm.form)){
         case -5 =>
       }
     }
     'multiply{
       val expr = """5*5"""

       assertMatch(quickParse(mathExpr, expr)(testForm.form)){
         case 25 =>
       }
     }
     'div{
       val expr = """ 10 / 2 """
       assertMatch(quickParse(mathExpr, expr)(testForm.form)){
         case 5 =>
       }
     }
   }

 }
