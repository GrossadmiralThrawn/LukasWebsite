package com.lukasdiettrichwebsite.backend.projectbackend.projectRepositorys




import com.lukasdiettrichwebsite.backend.projectbackend.projectdataclasses.ProjectImage
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository




@Repository
interface ProjectImageRepository : JpaRepository<ProjectImage, Long>