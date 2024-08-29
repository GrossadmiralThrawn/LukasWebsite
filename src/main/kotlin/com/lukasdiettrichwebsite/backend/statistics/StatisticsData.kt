package com.lukasdiettrichwebsite.backend.statistics

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import java.time.LocalDateTime


@Entity
class StatisticsData {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id:        ULong = 0u
    var startTime: LocalDateTime = LocalDateTime.now()
    var endTime:   LocalDateTime = LocalDateTime.now()
    var url:       String = ""
    var ip:        String = ""
}