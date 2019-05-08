package com.myapps.publictransport.query

import com.myapps.publictransport.dto.Coordinate
import com.myapps.publictransport.dto.LineDto
import com.myapps.publictransport.model.Line
import com.myapps.publictransport.repository.LineRepository
import org.springframework.stereotype.Component
import java.sql.Time

@Component
class LineQuery(
    private val lineRepository: LineRepository
) {

    fun findByTimeAndCoordinate(time: Time, coordinate: Coordinate): List<LineDto> {
        return lineRepository.findLinesByTimeAndCoordinates(
            time,
            coordinate.x,
            coordinate.y
        ).map { LineDto(it.name) }
    }

    fun findByStop(stopId: Long): List<LineDto> {
        return lineRepository.findByStopId(stopId).map { LineDto(it.name) }
    }

    fun checkIfDelayed(lineId: Long): Line {
         return lineRepository.findById(lineId).let { it }
    }
}