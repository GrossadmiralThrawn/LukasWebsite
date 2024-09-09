package com.lukasdiettrichwebsite.backend.projectbackend




import com.lukasdiettrichwebsite.backend.projectbackend.projectRepositorys.ProjectImageRepository
import com.lukasdiettrichwebsite.backend.projectbackend.projectRepositorys.ProjectRepository
import com.lukasdiettrichwebsite.backend.projectbackend.projectRepositorys.ProjectTextRepository
import com.lukasdiettrichwebsite.backend.projectbackend.projectdataclasses.Project
import com.lukasdiettrichwebsite.backend.projectbackend.projectdataclasses.ProjectImage
import com.lukasdiettrichwebsite.backend.projectbackend.projectdataclasses.ProjectText
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration





@Configuration
class ProjectInitializer {

    @Bean (name = ["userInit"])
    fun init(
        projectRepository: ProjectRepository,
        projectImageRepository: ProjectImageRepository,
        projectTextRepository: ProjectTextRepository,
        projectService: ProjectService
    ) = CommandLineRunner {

        // Check if projects already exist
        if (projectRepository.count() == 0L) {

            // Create Website Project
            val websiteProject = Project(
                name = "My own website",
                description = "One of the main reasons for creating this website was to create a project platform..."
            )
            val savedWebsiteProject = projectRepository.save(websiteProject)

            // Add text for Website Project
            val websiteText = ProjectText(text = "I used Spring Boot to implement the website.")
            projectTextRepository.save(websiteText)
            projectService.addTextToProject(savedWebsiteProject.id, websiteText, null)  // Pass null for position

            // Add image for Website Project
            val websiteImage = ProjectImage(projectImage = ByteArray(0), description = "Screenshot of the website", position = 1)
            projectImageRepository.save(websiteImage)
            projectService.addImageToProject(savedWebsiteProject.id, websiteImage, null)  // Pass null for position

            // Create QR Code App Project
            val qrCodeAppProject = Project(
                name = "QR Code App",
                description = "The app is available in two languages..."
            )
            projectRepository.save(qrCodeAppProject)
        }
    }
}

