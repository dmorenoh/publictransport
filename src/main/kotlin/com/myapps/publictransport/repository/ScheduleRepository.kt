package com.myapps.publictransport.repository

import com.myapps.publictransport.model.Schedule
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.sql.Time

@Repository
interface ScheduleRepository : JpaRepository<Schedule, Long> {
    @Query("select s from Schedule s where s.time = :time and s.stop.x = :xCoordinate and s.stop.y= :yCoordinate")
    fun findLinesByTimeAndCoordinates(time: Time, x: Int, y: Int): List<Schedule>
}