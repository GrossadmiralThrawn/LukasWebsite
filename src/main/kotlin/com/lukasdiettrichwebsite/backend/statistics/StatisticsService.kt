package com.lukasdiettrichwebsite.backend.statistics




import jakarta.servlet.http.HttpServletRequest
import org.springframework.stereotype.Service
import oshi.SystemInfo
import oshi.hardware.CentralProcessor
import oshi.hardware.GlobalMemory
import java.lang.management.ManagementFactory
import java.text.NumberFormat
import java.time.LocalDateTime
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




    /*fun getCpuUsageOverTime(): Double {
        val prevTicks = processor.systemCpuLoadTicks
        Thread.sleep(1000) // Eine Sekunde warten
        val currentTicks = processor.systemCpuLoadTicks
        val cpuLoad = processor.getSystemCpuLoadBetweenTicks(prevTicks, currentTicks) * 100
        return String.format("%.2f", cpuLoad).toDouble()
    }*/




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
