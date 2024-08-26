package com.lukasdiettrichwebsite.frontend




import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping




@Controller("/juristicEndpoints/")
class JuristicEndpoints {
    @GetMapping("impress")
    fun impress(): String {
        return "impress"
    }
}