package com.myapps.publictransport.repository

import com.myapps.publictransport.model.Line
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.sql.Time

@Repository
interface LineRepository : JpaRepository<Line, Long> {

    @Query("select line from Schedule as s join s.line as line where s.time = :time and s.stop.x = :xCoordinate and s.stop.y= :yCoordinate")
    fun findLinesByTimeAndCoordinates(time: Time, xCoordinate: Int, yCoordinate: Int): List<Line>

    @Query("select line from Schedule s join s.line as line where s.stop.id = :stopId")
    fun findByStopId(stopId: Long): List<Line>

}