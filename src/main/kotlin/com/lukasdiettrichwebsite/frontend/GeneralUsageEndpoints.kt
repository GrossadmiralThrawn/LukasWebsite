package com.lukasdiettrichwebsite.frontend

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping




@Controller
class GeneralUsageEndpoints {

    @GetMapping
    fun aboutMe(model: Model): String {
        val introductionText = mapOf(
            "intro1" to "My name is Lukas Diettrich. I was born on 16th September 2000 in Halle (Saale) in Saxony-Anhalt and study computer science with a focus on economy. When I started to learn programming I used to learn C. Later I continued with Java. Nowadays, I use C++ and Kotlin.",
            "intro2" to "I am interested in writing apps in Android Studio and any kind of C++ project. I would like to invite you to have a look at some of my projects. You can find two (2) of my most important projects below. You can find more projects by clicking on the projects button on the top right."
        )

        val websiteProjectDetails = mapOf(
            "title" to "1. My own website",
            "introduction" to listOf(
                "One of the main reasons for creating this website was to create a project platform for my job applications that clearly presents my skills and projects.",
                "I would also like to use this website to give beginners in the field of software development some ideas and inspiration for their own projects, as I often had problems finding ideas for my own projects at the beginning. That's why I like to share my own projects and learning progress to make it easier for others to get started.",
                "I used Spring Boot to implement the website. Spring Boot is a powerful tool that greatly simplifies the creation of stand-alone, production-ready Spring-based applications. Spring Boot is particularly suitable for web applications as it enables fast and scalable solutions.",
                "Spring is available in three programming languages: Java, Kotlin, and Groovy. For me, Kotlin was the right choice for several reasons. Kotlin is a modern, expressive language that is fully interoperable with Java and offers many advantages, including a more precise syntax and advanced language features such as null safety. Especially in combination with Spring Boot, Kotlin has proven to be extremely effective."
            ),
            "frontend" to "As I started to create my website, I asked myself how I should design it. On the one hand, I find dark designs very appealing, but I also wanted a light-coloured design. In addition, users should always have the option of viewing other projects or being able to view my GitHub repository.",
            "features" to "Because of this you can see the button for my GitHub account, one button for some other of my projects and one to toggle between light- and dark-mode which follow you when you scroll",
            "images" to listOf(
                "/images/WebsiteProjekt1.jpeg",
                "/images/WebsiteProjekt2.jpeg",
                "/images/WebsiteProjekt3LightMode.jpeg",
                "/images/WebsiteProjekt4Code1HTML.jpeg",
                "/images/WebsiteProjekt5CodePrimaereEndpunktdateiKotlin.jpeg"
            ),
            "codeSection" to mapOf(
                "intro" to "As you can see here I use HTML and CSS. The HTML-File does not contain any kind of design. The design is outsourced in a CSS-file named styles.css to create short HTML-files and make the design easy reusable."
                + "For the projects on this part of the website, images and texts are hardcoded to ensure persistent availability. On other parts, the data is outsourced to database systems.",
                "images" to listOf(
                    "/images/WebsiteProjekt4Code1HTML.jpeg",
                    "/images/WebsiteProjekt5CodePrimaereEndpunktdateiKotlin.jpeg"
                )
            )
        )

        val qrCodeAppDetails = mapOf(
            "title" to "2. QR-Code App für Android",
            "intro" to "This is an Android app that allows users to generate and scan QR codes easily.",
            "images" to listOf(
                "/images/ScreenshotApp2.jpg",
                "/images/ScreenshotApp3.jpg",
                "/images/ScreenshotApp4.jpg",
                "/images/ScreenshotApp5.jpg",
                "/images/ScreenshotApp6.jpg",
                "/images/ScreenshotApp7.jpg")
        )

        model.addAttribute("introductionText", introductionText)
        model.addAttribute("websiteProjectDetails", websiteProjectDetails)
        model.addAttribute("qrCodeAppDetails", qrCodeAppDetails)
        return "index"
    }




    @GetMapping(path = ["/statistics"])
    fun statistics(model: Model): String {
        return "statistics"
    }




    @PostMapping(path = ["/statistics"])
    fun statistics(): String {
        return "statistics"
    }




    @GetMapping(path = ["/projects"])
    fun projects(model: Model): String {
        return "projects"
    }
}
