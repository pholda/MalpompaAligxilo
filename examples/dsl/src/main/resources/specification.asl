fields = {
< name = "name", type = string, caption = @"Name" >
< name = "sex", type = checkbox default true, caption = @"Sex" >
< name = "Info", type = calculable {(@"Your name: " + $ "name")} , caption = @"Info" >
}