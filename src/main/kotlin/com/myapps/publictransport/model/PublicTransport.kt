package com.myapps.publictransport.model

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "line")
data class Line(@Id val id: Long, val name: String)

@Entity
@Table(name = "stop")
data class Stop(@Id val id: Long, val x: Int, val y: Int)

@Entity
@Table(name = "delay")
data class Delay(@Id val lineName: String, val delay: Int)




