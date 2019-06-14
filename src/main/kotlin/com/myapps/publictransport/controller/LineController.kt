package com.myapps.publictransport.controller

import com.myapps.publictransport.dto.CheckLineDelayDto
import com.myapps.publictransport.dto.Coordinate
import com.myapps.publictransport.dto.LineResponseDto
import com.myapps.publictransport.query.LineQuery
import org.springframework.web.bind.annotation.*
import java.sql.Time

@RestController
@RequestMapping("/lines")
class LineController(private val lineQuery: LineQuery) {

    @GetMapping("/findByTimeAndStop")
    fun getLines(
        @RequestParam(value = "time") time: Time,
        @RequestParam(value = "xCoordinate") xCoordinate: Int,
        @RequestParam(value = "yCoordinate") yCoordinate: Int
    ): LineResponseDto =
        LineResponseDto(lineQuery.findByTimeAndCoordinate(time, Coordinate(xCoordinate, yCoordinate)))

    @GetMapping("/findByStop")
    fun getLines(
        @RequestParam(value = "stopId") stopId: Long
    ): LineResponseDto =
        LineResponseDto(lineQuery.findByStop(stopId))

    @GetMapping("/{name}/delay")
    fun getDelayStatus(@PathVariable name: String): CheckLineDelayDto =
        CheckLineDelayDto(lineQuery.checkIfDelayed(name))
}