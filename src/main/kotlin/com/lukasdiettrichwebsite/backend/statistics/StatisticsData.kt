package com.lukasdiettrichwebsite.backend.statistics

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import java.time.LocalDateTime




@Entity
data class StatisticsData(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id:        ULong         = 0UL,
    var sessionId: String,
    var startTime: LocalDateTime = LocalDateTime.now(),
    var endTime:   LocalDateTime,
    var url:       String,
    var ip:        String, )