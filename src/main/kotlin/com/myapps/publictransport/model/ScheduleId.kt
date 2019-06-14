package com.myapps.publictransport.model

import java.io.Serializable
import javax.persistence.Column
import javax.persistence.Embeddable

@Embeddable
class ScheduleId(@Column(name = "line_id") val line: Long, @Column(name = "stop_id") val stop: Long) : Serializable