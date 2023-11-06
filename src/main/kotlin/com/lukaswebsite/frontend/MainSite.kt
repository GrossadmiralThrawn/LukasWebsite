package com.lukaswebsite.frontend




import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping




@Controller
class MainSite {
    @GetMapping(path = ["/welcome"])
    fun welcome(): String {
        return "index"
    }




    @GetMapping(path = ["/welcome/selfIntroduction"])
    fun selfIntroduction(): String {
        return "selfIntroduction"
    }




    @GetMapping(path = ["/welcome/projectExamples"])
    fun projectExamples(): String {
        return "projectExamples"
    }
}