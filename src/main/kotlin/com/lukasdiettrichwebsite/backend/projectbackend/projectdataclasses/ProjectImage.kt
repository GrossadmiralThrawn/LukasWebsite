package com.lukasdiettrichwebsite.backend.projectbackend.projectdataclasses





import jakarta.persistence.*

@Entity
@Table(name = "project_images")
data class ProjectImage(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(nullable = false)
    val imagePath: String,

    @Column(nullable = true)
    val description: String? = null
)
