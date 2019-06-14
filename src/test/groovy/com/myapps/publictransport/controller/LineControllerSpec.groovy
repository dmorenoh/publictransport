package com.myapps.publictransport.controller

import com.blogspot.toomuchcoding.spock.subjcollabs.Collaborator
import com.blogspot.toomuchcoding.spock.subjcollabs.Subject
import com.myapps.publictransport.dto.Coordinate
import com.myapps.publictransport.dto.LineDto
import com.myapps.publictransport.query.LineQuery
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import spock.lang.Specification
import spock.lang.Unroll

import java.sql.Time

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given
import static io.restassured.module.mockmvc.RestAssuredMockMvc.standaloneSetup
import static org.hamcrest.CoreMatchers.equalTo
import static org.springframework.http.HttpStatus.BAD_REQUEST
import static org.springframework.http.HttpStatus.OK

class LineControllerSpec extends Specification {

    @Subject
    LineController lineController

    @Collaborator
    LineQuery lineQuery = Mock()

    def setup() {
        standaloneSetup(MockMvcBuilders.standaloneSetup(lineController))
    }

    @Unroll
    def "should response as #responseStatus when time is #time, x is #x and y is #y"() {
        given: "a bad request"
        def request = given()
                .param("time", time)
                .param("xCoordinate", x)
                .param("yCoordinate", y)

        when: "get response"
        def response = request.when().get(URI.create("/lines/findByTimeAndStop"))

        then: "fails as bad request"
        response.then().statusCode(responseStatus)

        where:

        time       | x        | y        || responseStatus
        "1"        | "1"      | "2"      || BAD_REQUEST.value()
        "10:00:00" | "badArg" | "2"      || BAD_REQUEST.value()
        "10:00:00" | "1"      | "badArg" || BAD_REQUEST.value()
    }

    def "should response as OK when time is #time, x is #x and y is #y"() {
        given: "a bad request"
        def request = given()
                .param("time", time)
                .param("xCoordinate", x)
                .param("yCoordinate", y)
        lineQuery.findByTimeAndCoordinate(Time.valueOf(time), new Coordinate(1, 2)) >> [new LineDto(expectedLineName)]

        when: "get response"
        def response = request.when().get(URI.create("/lines/findByTimeAndStop"))

        then: "fails as bad request"
        response.then().statusCode(responseStatus)
                .body("lines[0].name", equalTo(expectedLineName))

        where:

        time       | x   | y   || responseStatus | expectedLineName
        "10:00:00" | "1" | "2" || OK.value()     | "expected"
    }
}
