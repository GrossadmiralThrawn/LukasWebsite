package com.lukasdiettrichwebsite.frontend




import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping




@Controller
class UserFrontEnd {
    @GetMapping("/user/login")
    fun login(): String {
        return "Login"
    }




    @GetMapping("/user/dashboard")
    fun dashboard(): String {
        return "Dashboard"
    }




    @GetMapping("/user/statistics")
    fun getStatistics(model: Model): String {
        // Hier können Sie das Model mit Daten füllen, falls nötig.
        return "statistics"
    }




    @PostMapping("/user/statistics")
    fun postStatistics(): String {
        return "statistics"
    }
}
