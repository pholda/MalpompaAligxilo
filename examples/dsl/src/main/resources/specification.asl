fields = {
< name = "name", type = string, caption = @"Name" >
< name = "sex", type = checkbox default ( true ), caption = @"Sex" >
< name = "birth", type = date, caption = @"Date of birth" >
< name = "birth2", type = date, caption = @"Date2" >
< name = "info", type = calculable {(@"Your name: " + $ "name")} , caption = @"Info" >
< name = "info2", type = calculable
    {
        mapValue ( compareDates ( $"birth" < $"birth2" ) ) {
            true => "birth < birth2",
            false => "birth >= birth2"
        }
    } , caption = @"Info2"
>
< name = "country", type = select
    {
        < "pl" @"Poland" >
        < "de" @"Germany" >
        < "uk" @"United Kingdom" >
    } notSelected < "kurwa" @"Please choose country.." > orderBy caption, caption = @"Country"
>
< name = "countryInfo", type = calculable
    {
        mapValue (enumValue($"country")) {
            "uk" => "UK",
            "de" => "DE",
            "pl" => "PL"
        }
    } , caption = @"CountryInfo"
>
< name = "meals", type = checkboxTable
    rows {
        < "b" @"Breakfast" >
        < "d" @"Dinner" >
        < "s" @"Supper" >
    }
    cols {
        < "1" @"Day 1">
        < "2" @"Day 2">
        < "3" @"Day 3">
    }
    disabled {
        < "b" "1" > <"s" "3">
    }
    default ( true ), caption = @"Meals"
>
}