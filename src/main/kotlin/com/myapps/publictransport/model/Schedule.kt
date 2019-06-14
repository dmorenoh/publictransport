package com.myapps.publictransport.model

import java.sql.Time
import javax.persistence.*

@Entity
@Table(name = "schedule")
data class Schedule(
    @EmbeddedId
    @AttributeOverrides(
        AttributeOverride(name = "line", column = Column(name = "line_id")),
        AttributeOverride(name = "stop", column = Column(name = "stop_id"))
    )
    val id: ScheduleId,
    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("line")
    @JoinColumn(name = "line_id")
    val line: Line,
    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("stop")
    @JoinColumn(name = "stop_id")
    val stop: Stop,
    val time: Time
)