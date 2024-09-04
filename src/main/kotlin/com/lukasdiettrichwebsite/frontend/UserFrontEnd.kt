package com.lukasdiettrichwebsite.frontend




import com.lukasdiettrichwebsite.backend.statistics.StatisticsService
import com.lukasdiettrichwebsite.backend.userbackend.UserService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping




@Controller
class UserFrontEnd(
    private val statisticsService: StatisticsService,
    private val userService: UserService
) {

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




    @GetMapping("/user/changePassword")
    fun showChangePasswordForm(model: Model): String {
        model.addAttribute("passwordChangeRequest", PasswordChangeRequest("", "", ""))
        return "changePassword"
    }




    @PostMapping("/user/changePassword")
    fun changePassword(@ModelAttribute passwordChangeRequest: PasswordChangeRequest,
        model: Model): String {
        // Prüfe, ob das neue Passwort mit der Bestätigung übereinstimmt
        if (passwordChangeRequest.newPassword != passwordChangeRequest.confirmPassword) {
            model.addAttribute("error", "Die Passwörter stimmen nicht überein.")
            return "changePassword"
        }

        // Versuche, das Passwort zu ändern
        val isChanged = userService.changePassword(passwordChangeRequest.currentPassword, passwordChangeRequest.currentPassword, passwordChangeRequest.newPassword)
        if (!isChanged) {
            model.addAttribute("error", "Das aktuelle Passwort ist falsch.")
            return "changePassword"
        }

        model.addAttribute("success", "Passwort erfolgreich geändert.")
        return "changePassword"
    }




    @GetMapping("/user/logout")
    fun logout(): String {
        return "redirect:/user/login"
    }
}




data class PasswordChangeRequest(
    val currentPassword: String,
    val newPassword: String,
    val confirmPassword: String
)
