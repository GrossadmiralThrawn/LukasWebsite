package com.lukasdiettrichwebsite.frontend

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping




@Controller
class GeneralUsageEndpoints {

    @GetMapping
    fun aboutMe(model: Model): String {
        return "index"
    }




    @GetMapping(path = ["/statistics"])
    fun statistics(model: Model): String {
        return "statistics"
    }




    @PostMapping(path = ["/statistics"])
    fun statistics(): String {
        return "statistics"
    }




    @GetMapping(path = ["/projects"])
    fun projects(model: Model): String {
        return "projects"
    }
}