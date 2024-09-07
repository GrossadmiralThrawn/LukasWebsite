package com.lukasdiettrichwebsite.backend.projectbackend.projectdataclasses




import com.fasterxml.jackson.annotation.JsonBackReference
import jakarta.persistence.*




@Entity
@Table(name = "project_images_links")
data class ProjectImageLink(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @ManyToOne
    @JoinColumn(name = "project_id", nullable = false)
    @JsonBackReference
    val project: Project,

    @ManyToOne
    @JoinColumn(name = "image_id", nullable = false)
    val image: ProjectImage,

    @Column(nullable = true)
    val position: Int? = null // 'order' zu 'position' geändert
)
