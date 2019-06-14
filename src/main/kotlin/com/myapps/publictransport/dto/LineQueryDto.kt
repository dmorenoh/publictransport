package com.myapps.publictransport.dto

import java.sql.Time

data class LineQueryDto(val time: Time, val stopDto: StopDto)