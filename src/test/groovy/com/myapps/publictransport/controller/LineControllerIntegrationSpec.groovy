package com.myapps.publictransport.controller


import io.restassured.builder.RequestSpecBuilder
import io.restassured.specification.RequestSpecification
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.web.server.LocalServerPort
import spock.lang.Specification

import static io.restassured.RestAssured.given
import static org.hamcrest.CoreMatchers.equalTo
import static org.hamcrest.collection.IsCollectionWithSize.hasSize
import static org.springframework.http.HttpStatus.NOT_FOUND
import static org.springframework.http.HttpStatus.OK

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class LineControllerIntegrationSpec extends Specification {

    @LocalServerPort
    private int port

    def "should return #expectedLine when time is #time, x as #x and y as #y"() {
        given: "a request "
        def request = given().spec(requestSpec(time, x, y))

        when: "request a line for such request"
        def response = request.when().get(URI.create("/lines/findByTimeAndStop"))

        then: "line as #lines"
        response.then()
                .statusCode(responseStatus).log().body()
                .body("lines", hasSize(1))
                .body("lines[0].name", equalTo("M4"))
        where:

        time       | x   | y   | responseStatus | expectedLine
        "10:00:00" | "1" | "1" | OK.value()     | "M4"
    }

    def "should return #items lines when stop is #stopId"() {
        given: "a request "
        def request = given().spec(requestSpec(stopId))

        when: "request a line for such request"
        def response = request.when().get(URI.create("/lines/findByStop"))

        then: "line as #lines"
        response.then()
                .statusCode(responseStatus).log().body()
                .body("lines", hasSize(items))
        where:

        stopId | responseStatus | items
        "3"    | OK.value()     | 3
    }

    def "should return as #delayed for line as #lineName"() {
        given: "a request "
        def request = given().port(port)

        when: "request a line for such request"
        def response = request.when().get(URI.create("/lines/" + lineName + "/delay"))

        then: "line as #lines"
        response.then()
                .statusCode(responseValue)

        where:

        lineName   | responseValue
        "M4"       | OK.value()
        "notFound" | NOT_FOUND.value()
    }

    private RequestSpecification requestSpec(String time, String x, String y) {
        return new RequestSpecBuilder()
                .setPort(port)
                .addParam("time", time)
                .addParam("xCoordinate", x)
                .addParam("yCoordinate", y)
                .build()
    }

    private RequestSpecification requestSpec(String stopId) {
        return new RequestSpecBuilder()
                .setPort(port)
                .addParam("stopId", stopId)
                .build()
    }
}
