package com.lukasdiettrichwebsite.backend.projectbackend.projectRepositorys




import com.lukasdiettrichwebsite.backend.projectbackend.projectdataclasses.ProjectTextLink
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository




@Repository
interface ProjectTextLinkRepository : JpaRepository<ProjectTextLink, Long>