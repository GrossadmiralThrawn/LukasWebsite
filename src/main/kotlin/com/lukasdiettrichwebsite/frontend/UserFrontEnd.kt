package com.lukasdiettrichwebsite.frontend




import com.lukasdiettrichwebsite.backend.projectbackend.ProjectService
import com.lukasdiettrichwebsite.backend.projectbackend.projectdataclasses.Project
import com.lukasdiettrichwebsite.backend.projectbackend.projectdataclasses.ProjectImage
import com.lukasdiettrichwebsite.backend.statistics.StatisticsService
import com.lukasdiettrichwebsite.backend.userbackend.UserService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import org.springframework.web.servlet.mvc.support.RedirectAttributes




@Controller
class UserFrontEnd(
    private val statisticsService: StatisticsService,
    private val userService: UserService,
    private val projectService: ProjectService
) {
    @GetMapping("/user")
    fun user(model: Model): String {
        return "redirect:/user/dashboard"
    }





    @GetMapping("/user/")
    fun user2(model: Model): String {
        return "redirect:/user/dashboard"
    }






    @GetMapping("/user/login")
    fun login(): String {
        return "login"
    }

    @GetMapping("/user/dashboard")
    fun dashboard(model: Model): String {
        val lastWeekStatistics = statisticsService.getLastWeekStatistics()
        model.addAttribute("lastWeekStatistics", lastWeekStatistics)
        return "dashboard"
    }

    @GetMapping("/user/statisticsData")
    @ResponseBody
    fun getStatisticsData(): Map<String, Long> {
        return statisticsService.getLastWeekStatisticsByInterval()
    }

    @GetMapping("/user/systemData")
    @ResponseBody
    fun getSystemData(): Map<String, Double> {
        val cpuUsage = statisticsService.getCpuUsage()
        val ramUsage = statisticsService.getRamUsage()
        return mapOf("cpuUsage" to cpuUsage, "ramUsage" to ramUsage)
    }

    @GetMapping("/user/statistics")
    fun getStatistics(model: Model): String {
        val statistics = statisticsService.getAllStatistics()
        model.addAttribute("statistics", statistics)
        return "statistics"
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
    fun changePassword(
        @ModelAttribute passwordChangeRequest: PasswordChangeRequest,
        model: Model
    ): String {
        if (passwordChangeRequest.newPassword != passwordChangeRequest.confirmPassword) {
            model.addAttribute("error", "Die Passwörter stimmen nicht überein.")
            return "changePassword"
        }

        val isChanged = userService.changePassword(passwordChangeRequest.newPassword,
            passwordChangeRequest.currentPassword,
            passwordChangeRequest.newPassword
        )
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

    @GetMapping("/user/addProject")
    fun addProject(): String {
        return "addProject"
    }




    @PostMapping("/addProject")
    fun addProject(
        @ModelAttribute project: Project,
        @RequestParam("conclusionText") conclusionText: String?,
        @RequestParam("projectImages") projectImages: List<MultipartFile>,
        @RequestParam("imageDescriptions") imageDescriptions: List<String?>,
        @RequestParam("imageOrders") imageOrders: List<Int?>?, // Optional, falls keine Reihenfolge angegeben
        redirectAttributes: RedirectAttributes
    ): String {
        try {
            // Speichern des Projekts
            val savedProject = projectService.createProject(project)

            // Verarbeiten der Bilder
            projectImages.forEachIndexed { index, projectImage ->
                if (!projectImage.isEmpty) {
                    val imageBytes = projectImage.bytes
                    val imageDescription = imageDescriptions.getOrNull(index)

                    // Default-Reihenfolge, falls keine Reihenfolge angegeben wurde
                    val imageOrder = imageOrders?.getOrNull(index)?.takeIf { it > 0 } ?: (index + 1)

                    // Erstellen eines ProjectImage-Objekts
                    val image = ProjectImage(
                        projectImage = imageBytes,
                        position = imageOrder.toShort()
                    )
                    projectService.addImageToProject(savedProject.id, image, description = imageDescription)
                }
            }

            // Speichern des Fazit-Textes
            conclusionText?.let {
                projectService.updateProjectConclusionText(savedProject.id, it)
            }

            // Erfolgsnachricht setzen
            redirectAttributes.addFlashAttribute("successMessage", "Projekt erfolgreich hinzugefügt!")
            return "redirect:/user/dashboard"
        } catch (e: Exception) {
            // Fehlernachricht setzen
            redirectAttributes.addFlashAttribute("errorMessage", "Fehler beim Hinzufügen des Projekts!")
            return "redirect:/user/addProject"
        }
    }
}

data class PasswordChangeRequest(
    val currentPassword: String,
    val newPassword: String,
    val confirmPassword: String
)
