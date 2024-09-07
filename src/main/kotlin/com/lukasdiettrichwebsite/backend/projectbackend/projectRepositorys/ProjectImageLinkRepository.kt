package com.lukasdiettrichwebsite.backend.projectbackend.projectRepositorys




import com.lukasdiettrichwebsite.backend.projectbackend.projectdataclasses.ProjectImageLink
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository


@Repository
interface ProjectImageLinkRepository : JpaRepository<ProjectImageLink, Long>