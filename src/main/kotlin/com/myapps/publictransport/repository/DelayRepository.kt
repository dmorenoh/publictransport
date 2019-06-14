package com.myapps.publictransport.repository

import com.myapps.publictransport.model.Delay
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface DelayRepository : JpaRepository<Delay, Long> {
    fun findByLineName(lineName: String): Delay?
}