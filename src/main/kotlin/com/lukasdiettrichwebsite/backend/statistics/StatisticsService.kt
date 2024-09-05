package com.lukasdiettrichwebsite.backend.statistics




import jakarta.servlet.http.HttpServletRequest
import org.springframework.stereotype.Service
import oshi.SystemInfo
import oshi.hardware.CentralProcessor
import oshi.hardware.GlobalMemory
import java.lang.management.ManagementFactory
import java.text.NumberFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import java.util.*


@Service
class StatisticsService(private val statisticsDataRepository: StatisticsRepository) {
    private val systemInfo = SystemInfo()
    private val processor: CentralProcessor = systemInfo.hardware.processor
    private val memory: GlobalMemory = systemInfo.hardware.memory





    // Methode, um die Anzahl der Aufrufe in den letzten 7 Tagen zu ermitteln
    fun getLastWeekStatistics(): Long {
        val oneWeekAgo = LocalDateTime.now().minusDays(7)
        return statisticsDataRepository.findAllByStartTimeAfter(oneWeekAgo).size.toLong()
    }




    fun getLastWeekStatisticsByInterval(): Map<String, Long> {
        val now = LocalDateTime.now()
        val oneWeekAgo = now.minusDays(7)

        // List of intervals (6 hours) and their corresponding counts
        val intervals = mutableMapOf<String, Long>()

        // Loop through each 6-hour period in the last 7 days
        for (i in 0..27) {
            val start = oneWeekAgo.plus(i * 6L, ChronoUnit.HOURS)
            val end = start.plus(6, ChronoUnit.HOURS)
            val count = statisticsDataRepository.findAllByStartTimeBetween(start, end).size.toLong()

            // Create label for this interval, e.g., "2023-09-01 00:00 - 06:00"
            val label = "${start.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))} - ${end.format(DateTimeFormatter.ofPattern("HH:mm"))}"
            intervals[label] = count
        }

        return intervals
    }



    // Method to calculate CPU usage
    fun getCpuUsage(): Double {
        val cpuLoad = processor.getSystemCpuLoad(100)
        // Format the CPU load based on the system locale
        val locale = Locale.getDefault()  // or you can specify Locale.GERMANY if needed
        val numberFormat = NumberFormat.getNumberInstance(locale)

        // Format the number and parse it
        val formattedCpuLoad = String.format(locale, "%.2f", cpuLoad)
        return numberFormat.parse(formattedCpuLoad).toDouble()
    }



    // Methode, um die RAM-Auslastung zu berechnen
    fun getRamUsage(): Double {
        val totalMemory = memory.total
        val availableMemory = memory.available
        val usedMemory = totalMemory - availableMemory
        return (usedMemory.toDouble() / totalMemory) * 100
    }




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
}
