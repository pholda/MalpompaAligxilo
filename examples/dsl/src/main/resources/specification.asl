fields = {
< name = "name", type = string default ("blabla"), caption = @"Name", required = true>
< name = "sex", type = checkbox default (true) , caption = @"Sex">
< name = "email", type = email, caption = @"Email">
< name = "birth", type = date, caption = @"Date of birth" >
< name = "birth2", type = date, caption = @"Date2">
< name = "info2", type = computed { selectedInTotal($"partoprentempo") * {5 + 2} } , caption = @"Info2" >
< name = "country", type = select
    {
        < "pl" @"Poland" >
        < "de" @"Germany" >
        < "uk" @"United Kingdom" >
    } notSelected < "" @"Please choose country.." > orderBy caption, caption = @"Country"
>
< name = "countryInfo", type = computed
    {
        mapValue (enumValue($"country")) {
            "uk" => "UK",
            "de" => "DE",
            "pl" => "PL"
        }
    } , caption = @"CountryInfo"
>
<name = "partoprentempo", caption=@"Tempo de partopreno", type=checkboxTable rows {<"tago" @"Tago">} cols {<"12" "12.11"><"13" "13.11"><"14" "14.11"><"15" "15.11">} default (true)>

}