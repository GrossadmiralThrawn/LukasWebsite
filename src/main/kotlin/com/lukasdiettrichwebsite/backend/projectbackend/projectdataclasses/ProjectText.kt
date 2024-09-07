package com.lukasdiettrichwebsite.backend.projectbackend.projectdataclasses



import jakarta.persistence.*




@Entity
@Table(name = "project_texts")
data class ProjectText(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(nullable = false)
    val text: String
)
