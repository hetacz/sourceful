import org.gradle.api.tasks.testing.logging.TestLogEvent
import org.gradle.api.tasks.testing.logging.TestExceptionFormat

plugins {
    id("java")
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    // https://mvnrepository.com/artifact/org.seleniumhq.selenium/selenium-java
    testImplementation("org.seleniumhq.selenium:selenium-java:4.8.1")
    // https://mvnrepository.com/artifact/org.testng/testng
    testImplementation("org.testng:testng:7.7.1")
    // https://mvnrepository.com/artifact/io.github.bonigarcia/webdrivermanager
    testImplementation("io.github.bonigarcia:webdrivermanager:5.3.2")
    // https://mvnrepository.com/artifact/com.github.javafaker/javafaker
    testImplementation("com.github.javafaker:javafaker:1.0.2")
    // https://mvnrepository.com/artifact/org.assertj/assertj-core
    testImplementation("org.assertj:assertj-core:3.24.2")
}

tasks.withType<Test> {
    useTestNG {
        suites(System.getProperty("suiteFile", "testng.xml") ?: "testng.xml")
    }

    systemProperties = System.getProperties().map { e -> Pair(e.key as String, e.value) }.toMap()

    testLogging {
        events(
            TestLogEvent.PASSED,
            TestLogEvent.FAILED,
            TestLogEvent.SKIPPED
        )
        exceptionFormat = TestExceptionFormat.FULL
    }
}
