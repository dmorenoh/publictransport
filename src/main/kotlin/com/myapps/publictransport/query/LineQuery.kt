package com.myapps.publictransport.query

import com.myapps.publictransport.dto.Coordinate
import com.myapps.publictransport.dto.LineDto
import com.myapps.publictransport.exception.LineNotFoundException
import com.myapps.publictransport.model.Line
import com.myapps.publictransport.repository.DelayRepository
import com.myapps.publictransport.repository.LineRepository
import com.myapps.publictransport.repository.ScheduleRepository
import org.springframework.stereotype.Component
import java.sql.Time

@Component
class LineQuery(
    private val lineRepository: LineRepository,
    private val delayRepository: DelayRepository
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

    fun checkIfDelayed(lineName: String): Boolean {
        return delayRepository.findByLineName(lineName)?.let { return it.delay > 0 } ?: throw LineNotFoundException("""$lineName not found""")
    }

}