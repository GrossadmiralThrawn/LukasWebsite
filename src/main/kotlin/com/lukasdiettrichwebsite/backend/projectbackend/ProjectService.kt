package com.lukasdiettrichwebsite.backend.projectbackend




import com.lukasdiettrichwebsite.backend.projectbackend.projectRepositorys.*
import com.lukasdiettrichwebsite.backend.projectbackend.projectdataclasses.*
import org.springframework.stereotype.Service




@Service
class ProjectService(
    private val projectRepository: ProjectRepository,
    private val projectTextLinkRepository: ProjectTextLinkRepository,
    private val projectImageLinkRepository: ProjectImageLinkRepository,
    private val projectTextRepository: ProjectTextRepository,
    private val projectImageRepository: ProjectImageRepository
) {
    fun createProject(project: Project): Project {
        return projectRepository.save(project)
    }

    fun addTextToProject(projectId: Long, text: ProjectText, position: Int? = null): ProjectTextLink {
        val project = projectRepository.findById(projectId).orElseThrow { Exception("Project not found") }
        val savedText = projectTextRepository.save(text)
        val link = ProjectTextLink(project = project, text = savedText, position = position)
        return projectTextLinkRepository.save(link)
    }

    fun addImageToProject(projectId: Long, image: ProjectImage, position: Int? = null, description: String? = null): ProjectImageLink {
        val project = projectRepository.findById(projectId).orElseThrow { Exception("Project not found") }
        val savedImage = projectImageRepository.save(image)
        val link = ProjectImageLink(project = project, image = savedImage, position = position, description = description)
        return projectImageLinkRepository.save(link)
    }

    fun updateProjectConclusionText(projectId: Long, conclusionText: String): Project {
        val project = projectRepository.findById(projectId).orElseThrow { Exception("Project not found") }
        project.conclusionText = conclusionText
        return projectRepository.save(project)
    }

    fun getProjectById(projectId: Long): Project {
        return projectRepository.findById(projectId).orElseThrow { Exception("Project not found") }
    }
}
