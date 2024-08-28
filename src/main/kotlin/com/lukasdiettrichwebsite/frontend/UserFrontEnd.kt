package com.lukasdiettrichwebsite.frontend




import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping




@Controller ("user")
class UserFrontEnd {
    @GetMapping("/login")
    fun login(): String {
        return "Login"
    }




    @GetMapping("/dashboard")
    fun dashboard(): String {
        return "Dashboard"
    }





    @GetMapping(path = ["statistics"])
    fun statistics(model: Model): String {
        return "statistics"
    }




    @PostMapping(path = ["statistics"])
    fun statistics(): String {
        return "statistics"
    }





}