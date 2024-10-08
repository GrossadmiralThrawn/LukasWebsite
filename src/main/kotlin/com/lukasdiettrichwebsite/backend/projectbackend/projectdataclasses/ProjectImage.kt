package com.lukasdiettrichwebsite.backend.projectbackend.projectdataclasses





import jakarta.persistence.*




@Entity
@Table(name = "project_images")
data class ProjectImage(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Lob
    @Column(nullable = false)
    val projectImage: ByteArray,  // Speichert das Bild als BLOB

    @Column(nullable = true)
    val description: String? = null,

    var position: Short
)
