package com.lukasdiettrichwebsite.backend.statistics




import jakarta.servlet.http.HttpServletRequest
import org.springframework.stereotype.Service
import oshi.SystemInfo
import oshi.hardware.CentralProcessor
import oshi.hardware.GlobalMemory
import oshi.hardware.HWDiskStore
import oshi.software.os.NetworkParams
import oshi.software.os.OperatingSystem
import java.lang.management.ManagementFactory
import javax.management.AttributeList
import javax.management.MBeanServerConnection
import javax.management.ObjectName
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import java.io.File




@Service
class StatisticsService(private val statisticsDataRepository: StatisticsRepository) {
    private val systemInfo = SystemInfo()
    private val processor: CentralProcessor = systemInfo.hardware.processor
    private val memory: GlobalMemory = systemInfo.hardware.memory
    private val os: OperatingSystem = systemInfo.operatingSystem
    private val diskStores: Array<HWDiskStore> = systemInfo.hardware.diskStores.toTypedArray()
    private val networkParams: NetworkParams = os.networkParams




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




    // Methode zur Berechnung der CPU-Auslastung
    fun getCpuUsage(): Double {
        return try {
            val mbs: MBeanServerConnection = ManagementFactory.getPlatformMBeanServer()
            val objectName = ObjectName("java.lang:type=OperatingSystem")
            val attributes: AttributeList = mbs.getAttributes(objectName, arrayOf("SystemCpuLoad"))

            if (attributes.isEmpty()) return 0.0

            val cpuLoadAttribute = attributes[0] as javax.management.Attribute
            val cpuLoad = cpuLoadAttribute.value as Double

            // `SystemCpuLoad` gibt die CPU-Auslastung als Prozentsatz von 0 bis 1 zurück
            cpuLoad * 100
        } catch (e: Exception) {
            e.printStackTrace()
            0.0 // Im Fehlerfall 0% Auslastung zurückgeben
        }
    }




    // Methode, um die RAM-Auslastung zu berechnen
    fun getRamUsage(): Double {
        val totalMemory = memory.total
        val availableMemory = memory.available
        val usedMemory = totalMemory - availableMemory
        return (usedMemory.toDouble() / totalMemory) * 100
    }




    // Festplattennutzung berechnen
    fun getDiskUsage(): Map<String, Double> {
        val fileSystem = systemInfo.operatingSystem.fileSystem
        return fileSystem.fileStores.associate { fileStore ->
            val totalSpace = fileStore.totalSpace.toDouble()
            val usableSpace = fileStore.usableSpace.toDouble()

            if (totalSpace == 0.0) {
                fileStore.name to 0.0
            } else {
                val usedPercentage = ((totalSpace - usableSpace) / totalSpace) * 100
                fileStore.name to usedPercentage
            }
        }
    }






    // Methode zur Berechnung der Netzwerkauslastung (Netzwerkinformationen)
    fun getNetworkUsage(): Map<String, String> {
        return mapOf(
            "Host Name" to networkParams.hostName,
            "Domain Name" to networkParams.domainName,
            "IPv4 Gateway" to networkParams.ipv4DefaultGateway,
            "IPv6 Gateway" to networkParams.ipv6DefaultGateway,
            "DNS Servers" to networkParams.dnsServers.joinToString(", ")
        )
    }

    // Methode zur Berechnung der Fehlerraten
    fun getErrorRates(): Double {
        // Dummy implementation, Fehlerraten müssen über Log-Analyse oder API ermittelt werden
        return 0.0
    }

    // Methode, um die Anzahl der aktiven Sitzungen zu ermitteln
    fun getActiveUserSessions(): Long {
        return statisticsDataRepository.findAllByEndTimeIsNull().size.toLong()
    }

    // Methode zur Ermittlung der Performance von Datenbankabfragen
    fun getDatabaseQueryPerformance(): Map<String, Long> {
        // Beispiel für eine einfache Performance-Analyse von häufigen Abfragen
        val startTime = System.nanoTime()
        statisticsDataRepository.findAll() // Beispielabfrage
        val endTime = System.nanoTime()

        val duration = endTime - startTime
        return mapOf("QueryDurationInNanoSeconds" to duration)
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
