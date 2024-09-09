package com.lukasdiettrichwebsite.backend.projectbackend.projectdataclasses




import com.fasterxml.jackson.annotation.JsonBackReference
import jakarta.persistence.*




@Entity
@Table(name = "project_texts_links")
data class ProjectTextLink(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @ManyToOne
    @JoinColumn(name = "project_id", nullable = false)
    @JsonBackReference
    val project: Project,

    @ManyToOne
    @JoinColumn(name = "text_id", nullable = false)
    val text: ProjectText,

    @Column(nullable = true)
    val position: Int? = null // Hier wurde der Spaltenname von 'order' auf 'position' geändert
)
