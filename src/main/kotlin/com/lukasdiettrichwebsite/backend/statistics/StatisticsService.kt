package com.lukasdiettrichwebsite.backend.statistics




import jakarta.servlet.http.HttpServletRequest
import org.springframework.stereotype.Service
import java.time.LocalDateTime



@Service
class StatisticsService (val statisticsDataRepository: StatisticsRepository) {
    fun recordStatistics(url: String, request: HttpServletRequest) {
        val ip = request.remoteAddr
        val sessionId = request.session.id

        // Überprüfen, ob bereits ein Eintrag für diese Sitzung existiert
        val existingStatistics = statisticsDataRepository.findBySessionIdAndUrl(sessionId, url)

        if (existingStatistics == null) {
            val statistics = StatisticsData(
                sessionId = sessionId,
                startTime = LocalDateTime.now(),
                endTime = LocalDateTime.now(), // Dies können Sie später aktualisieren, um die tatsächliche Endzeit zu speichern
                url = url,
                ip = ip
            )
            statisticsDataRepository.save(statistics)
        }
    }



    fun getAllStatistics(): List<StatisticsData> {
        return statisticsDataRepository.findAll().toList()
    }
}
