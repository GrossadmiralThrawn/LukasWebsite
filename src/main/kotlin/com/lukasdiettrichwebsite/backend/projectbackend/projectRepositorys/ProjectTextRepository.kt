package com.lukasdiettrichwebsite.backend.projectbackend.projectRepositorys




import com.lukasdiettrichwebsite.backend.projectbackend.projectdataclasses.ProjectText
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository




@Repository
interface ProjectTextRepository : JpaRepository<ProjectText, Long>