package com.lukaswebsite.frontend




import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping




@Controller
class MainSite {
    @GetMapping(path = ["/welcome"])
    fun welcome(): String {
        return "index"
    }
}