package com.lukasdiettrichwebsite.backend.projectbackend.projectdataclasses




import jakarta.persistence.*
import com.fasterxml.jackson.annotation.JsonManagedReference




@Entity
@Table(name = "projects")
data class Project(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(nullable = false)
    val name: String,

    @Column(nullable = true)
    val description: String? = null,  // Einleitung

    @OneToMany(mappedBy = "project", cascade = [CascadeType.ALL], orphanRemoval = true)
    @JsonManagedReference
    val textLinks: MutableList<ProjectTextLink> = mutableListOf(),

    @OneToMany(mappedBy = "project", cascade = [CascadeType.ALL], orphanRemoval = true)
    @JsonManagedReference
    val imageLinks: MutableList<ProjectImageLink> = mutableListOf(),

    @Column(nullable = true)
    var conclusionText: String? = null  // Abschlusstext
)
