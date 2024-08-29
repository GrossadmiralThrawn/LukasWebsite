package com.lukasdiettrichwebsite.backend.statistics




import java.time.LocalDateTime




class TrackingData {
    var startTime:          LocalDateTime        = LocalDateTime.now()
    var endTime:            LocalDateTime        = LocalDateTime.now()
    var statisticsDataList: List<StatisticsData> = listOf()
}