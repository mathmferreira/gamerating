plugins {
	java
	id("org.springframework.boot") version "3.1.3"
	id("io.spring.dependency-management") version "1.1.3"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_17
}

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

repositories {
	mavenCentral()
}

val commonsLangVersion = "3.12.0"
val commonsCollectionsVersion = "4.4"
val jwtVersion = "9.31"
val restAssuredVersion = "5.3.1"
val jacksonDatatypeJsrVersion = "2.15.2"

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-data-rest")
	implementation("org.springframework.boot:spring-boot-starter-integration")
	implementation("org.springframework.boot:spring-boot-starter-jersey")
	implementation("org.springframework.boot:spring-boot-starter-validation")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.integration:spring-integration-http")
	implementation("org.springframework.integration:spring-integration-jpa")

	developmentOnly("org.springframework.boot:spring-boot-devtools")
	developmentOnly("org.springframework.boot:spring-boot-docker-compose")

	implementation("org.apache.commons:commons-lang3:$commonsLangVersion")
	implementation("org.apache.commons:commons-collections4:$commonsCollectionsVersion")
	implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:$jacksonDatatypeJsrVersion")
	implementation("com.nimbusds:nimbus-jose-jwt:$jwtVersion")
	implementation("io.rest-assured:rest-assured:$restAssuredVersion")
	implementation("io.rest-assured:spring-mock-mvc:$restAssuredVersion")

	implementation("org.flywaydb:flyway-core")
	runtimeOnly("org.postgresql:postgresql")

	annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
	annotationProcessor("org.projectlombok:lombok")
	compileOnly("org.projectlombok:lombok")

	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.springframework.integration:spring-integration-test")
}

tasks.withType<Test> {
	useJUnitPlatform()
}
