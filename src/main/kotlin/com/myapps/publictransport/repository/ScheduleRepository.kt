package com.myapps.publictransport.repository

import com.myapps.publictransport.model.Line
import com.myapps.publictransport.model.Schedule
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import java.sql.Time

interface ScheduleRepository : CrudRepository<Schedule, Long> {
    @Query("select s.line from schedule s where s.time = :time and s.stop.x = :x and s.stop.y= :y")
    fun findLinesByTimeAndCoordinates(time: Time, x: Int, y: Int): List<Line>
}