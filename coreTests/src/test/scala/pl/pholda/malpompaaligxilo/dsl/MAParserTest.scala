//package pl.pej.malpompaaligxilo.dsl
//
//import minitest._
//import pl.pej.malpompaaligxilo.form.Field
//import pl.pej.malpompaaligxilo.form.field.StringField
//import pl.pej.malpompaaligxilo.util.NoI18nString
//
//object MAParserTest extends SimpleTestSuite {
//  test("form") {
//    val input =
//      """
//        |form {
//        | fields = {
//        |   <
//        |     name = "blabla",
//        |     caption = "iahahha"
//        |   >
//        | }
//        |}
//      """.stripMargin
//
//    val f = Parser(input)
//
//    val pattern = DslForm(List(
//      Field(
//        name = "blabla",
//        caption = NoI18nString("iahahha"),
//        `type` = StringField()
//      )
//    ))
//
//    println(f.fields)
//    println(pattern.fields)
//    assert(f == pattern)
//
//    println(f)
//  }
//}
