package com.lukasdiettrichwebsite.frontend




import com.lukasdiettrichwebsite.backend.projectbackend.ProjectService
import com.lukasdiettrichwebsite.backend.projectbackend.projectRepositorys.ProjectImageRepository
import com.lukasdiettrichwebsite.backend.statistics.StatisticsService
import jakarta.servlet.http.HttpServletRequest
import org.springframework.core.io.ByteArrayResource
import org.springframework.core.io.Resource
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable




@Controller
class GeneralUsageEndpoints (private val statisticsService: StatisticsService, private val projectImageRepository: ProjectImageRepository, private val projectService: ProjectService) {
    @GetMapping("/")
    fun aboutMe(model: Model, request: HttpServletRequest): String {
        statisticsService.recordStatistics("/", request)

        val websiteProjectDetails = mapOf(
            "title" to "1. My own website",
            "introduction" to listOf(
                "One of the main reasons for creating this website was to create a project platform for my job applications " +
                        "that clearly presents my skills and projects.",
                "I would also like to use this website to give beginners in the field of software development some ideas " +
                        "and inspiration for their own projects, as I often had problems finding ideas for my own projects at the beginning." +
                        " That's why I like to share my own projects and learning progress to make it easier for others to get started.",
                "I used Spring Boot to implement the website. Spring Boot is a powerful tool that greatly simplifies the " +
                        "creation of stand-alone, production-ready Spring-based applications. Spring Boot is particularly " +
                        "suitable for web applications as it enables fast and scalable solutions.",
                "Spring is available in three programming languages: Java, Kotlin, and Groovy. For me, " +
                        "Kotlin was the right choice for several reasons. Kotlin is a modern, expressive language " +
                        "that is fully interoperable with Java and offers many advantages, including a more precise syntax and " +
                        "advanced language features such as null safety. Especially in combination with Spring Boot, " +
                        "Kotlin has proven to be extremely effective."
            ),
            "frontend" to "As I started to create my website, I asked myself how I should design it. On the one hand, " +
                    "I find dark designs very appealing, but I also wanted a light-coloured design. In addition, " +
                    "users should always have the option of viewing other projects or being able to view my GitHub repository.",
            "features" to "Because of this you can see the button for my GitHub account, one button for some other of " +
                    "my projects and one to toggle between light- and dark-mode which follow you when you scroll.",
            "images" to listOf(
                "/images/WebsiteProjekt1.jpeg",
                "/images/WebsiteProjekt2.jpeg",
                "/images/WebsiteProjekt3LightMode.jpeg",
                "/images/WebsiteProjekt4Code1HTML.jpeg",
                "/images/WebsiteProjekt5CodePrimaereEndpunktdateiKotlin.jpeg"
            ),
            "codeSection" to mapOf(
                "intro" to "As you can see here I use HTML and CSS. The HTML-File does not contain any kind of design. " +
                        "The design is outsourced in a CSS-file named styles.css to create short HTML-files and make the " +
                        "design easy reusable. For the projects on this part of the website, images and texts are hardcoded " +
                        "to ensure persistent availability.",
                "images" to listOf(
                    "/images/WebsiteProjekt4Code1HTML.jpeg",
                    "/images/WebsiteProjekt5CodePrimaereEndpunktdateiKotlin.jpeg"
                )
            )
        )

        model.addAttribute("websiteProjectDetails", websiteProjectDetails)

        return "index"
    }



    @GetMapping("/projects")
    fun getAllProjects(model: Model): String {
        val projects = projectService.getAllProjects()
        if (projects.isEmpty()) {
            model.addAttribute("errorMessage", "No projects found.")
        } else {
            model.addAttribute("projects", projects)
        }
        return "projects"
    }




    @GetMapping("/images/{id}")
    fun getImage(@PathVariable id: Long): ResponseEntity<Resource> {
        val projectImage = projectImageRepository.findById(id).orElseThrow { Exception("Image not found") }
        val imageBytes = projectImage.projectImage
        val resource = ByteArrayResource(imageBytes)

        return ResponseEntity.ok()
            .contentType(MediaType.IMAGE_JPEG) // Ändere dies je nach Bildtyp
            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"image-$id.jpeg\"")
            .body(resource)
    }
}
