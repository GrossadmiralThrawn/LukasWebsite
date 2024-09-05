package com.lukasdiettrichwebsite.backend.statistics

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.time.LocalDateTime


@Repository
interface StatisticsRepository: CrudRepository<StatisticsData, ULong> {
    fun findBySessionIdAndUrl(id: String, userId: String): StatisticsData?
    fun findAllByStartTimeAfter(startTime: LocalDateTime): List<StatisticsData>
    fun findAllByStartTimeBetween(start: LocalDateTime, end: LocalDateTime): List<StatisticsData>
}