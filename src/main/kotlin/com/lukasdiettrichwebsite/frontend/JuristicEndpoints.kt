package com.lukasdiettrichwebsite.frontend




import com.lukasdiettrichwebsite.backend.statistics.StatisticsService
import jakarta.servlet.http.HttpServletRequest
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping




@Controller
class JuristicEndpoints (val statisticsService: StatisticsService) {
    @GetMapping("/juristicEndpoints/imprint")
    fun imprint(httpServletRequest: HttpServletRequest): String {
        statisticsService.recordStatistics("/juristicEndpoints/imprint", httpServletRequest)
        return "imprint"
    }



    @GetMapping("/juristicEndpoints/ExclusionOfLiability")
    fun exclusionOfLiability(request: HttpServletRequest): String {
        statisticsService.recordStatistics("/juristicEndpoints/imprint", request)
        return "exclusionOfLiability"
    }
}