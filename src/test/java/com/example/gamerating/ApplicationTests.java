package com.example.gamerating;

import io.restassured.http.ContentType;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.hamcrest.Matchers.emptyString;
import static org.hamcrest.Matchers.startsWith;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	private String login;

	@BeforeAll
	public void init() {
		RestAssuredMockMvc.mockMvc(mockMvc);
	}

	@Test
	void contextLoads() {

	}

	@Test
	void givenEmailAndPass_whenLogIn_ReturnStatus200() {
		login = """
				{ "email": "admin@gmail.com", "pass": "admin" }""";
		given().contentType(ContentType.JSON).body(login)
				.when().post("/login")
				.then().log().ifValidationFails()
				.assertThat().status(HttpStatus.OK)
				.body(emptyString())
				.header(HttpHeaders.AUTHORIZATION, startsWith("Bearer"));
	}

	@Test
	void givenEmailAndPass_whenLogIn_ReturnStatus401() {
		login = """
				{ "email": "example@test.com", "pass": "123" }""";
		given().contentType(ContentType.JSON).body(login)
				.when().post("/login")
				.then().log().ifValidationFails()
				.assertThat().status(HttpStatus.UNAUTHORIZED);
	}

}
