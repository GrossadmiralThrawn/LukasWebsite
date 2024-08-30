package com.lukasdiettrichwebsite.backend.statistics

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository


@Repository
interface StatisticsRepository: CrudRepository<StatisticsData, ULong> {
    fun findBySessionIdAndUrl(id: String, userId: String): StatisticsData?
}