package com.lukasdiettrichwebsite.backend.statistics




import jakarta.servlet.http.HttpServletRequest
import org.springframework.stereotype.Service
import java.time.LocalDateTime




@Service
class StatisticsService(private val statisticsDataRepository: StatisticsRepository) {

    fun recordStatistics(url: String, request: HttpServletRequest) {
        val ip = request.remoteAddr
        val sessionId = request.session.id

        val existingStatistics = statisticsDataRepository.findBySessionIdAndUrl(sessionId, url)

        if (existingStatistics == null) {
            val statistics = StatisticsData(
                sessionId = sessionId,
                startTime = LocalDateTime.now(),
                endTime = LocalDateTime.now(),
                url = url,
                ip = ip
            )
            statisticsDataRepository.save(statistics)
        }
    }

    fun getAllStatistics(): List<StatisticsData> {
        return statisticsDataRepository.findAll().toList()
    }

    fun getStatisticsForLastWeek(): List<StatisticsData> {
            val oneWeekAgo = LocalDateTime.now().minusWeeks(1)
            return statisticsDataRepository.findAllByStartTimeAfter(oneWeekAgo)
    }
}
