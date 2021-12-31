package com.abraham.mobilecommunicationplatformtest.integration;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
public class MobileCommunicationPlatformTestApiIT {

	private final static String DATE = "20180131";

	@Test
	public void shouldProcessJSONFile() {

		RestAssured.given().param("date", DATE).accept(ContentType.JSON).when().post("/process").then().assertThat()
				.statusCode(HttpStatus.OK.value()).contentType(ContentType.JSON);
	}
	
	@Test
	public void shouldGetMetrics() {

		RestAssured.given().accept(ContentType.JSON).when().get("/metrics").then().assertThat()
				.statusCode(HttpStatus.OK.value()).contentType(ContentType.JSON);
	}
	
	@Test
	public void shouldGetKpis() {

		RestAssured.given().accept(ContentType.JSON).when().get("/kpis").then().assertThat()
				.statusCode(HttpStatus.OK.value()).contentType(ContentType.JSON);
	}

}
