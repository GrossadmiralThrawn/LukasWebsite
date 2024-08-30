package com.lukasdiettrichwebsite.frontend




import com.lukasdiettrichwebsite.backend.statistics.StatisticsRepository
import com.lukasdiettrichwebsite.backend.statistics.StatisticsService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping




@Controller
class UserFrontEnd (var statisticsService: StatisticsService, statisticsRepository: StatisticsRepository){
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
        val statistics = statisticsService.getAllStatistics()
            model.addAttribute("statistics", statistics)
            return "statistics"  // Rendert das Thymeleaf-Template "statistics.html"
    }




    @PostMapping("/user/statistics")
    fun postStatistics(model: Model): String {
        val statistics = statisticsService.getAllStatistics()
        model.addAttribute("statistics", statistics)
        return "statistics"
    }
}
