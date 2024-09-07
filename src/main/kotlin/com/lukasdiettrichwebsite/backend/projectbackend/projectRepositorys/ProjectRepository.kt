package com.lukasdiettrichwebsite.backend.projectbackend.projectRepositorys




import com.lukasdiettrichwebsite.backend.projectbackend.projectdataclasses.Project
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository




@Repository
interface ProjectRepository : JpaRepository<Project, Long>