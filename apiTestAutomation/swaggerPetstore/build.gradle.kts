plugins {
    id("java")
    id("net.serenity-bdd.serenity-gradle-plugin") version "4.0.46"
}

group = "com.paloit"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    // JUnit 4 para escribir y ejecutar tests
    implementation("junit:junit:4.13.2")

    // JUnit Jupiter (JUnit 5) para escribir y ejecutar tests
    implementation("org.junit.jupiter:junit-jupiter-api:5.10.2")
    implementation("org.junit.jupiter:junit-jupiter-engine:5.10.2")

    // Soporte para definir suites de pruebas con JUnit Platform
    implementation("org.junit.platform:junit-platform-suite-api:1.10.2")
    implementation("org.junit.platform:junit-platform-suite-engine:1.10.2")

    // Cucumber para pruebas BDD
    implementation("io.cucumber:cucumber-java:7.15.0")
    implementation("io.cucumber:cucumber-junit-platform-engine:7.15.0")

    // Serenity BDD para automatización y reportes
    implementation("net.serenity-bdd:serenity-screenplay-rest:4.1.0")
    implementation("net.serenity-bdd:serenity-screenplay:4.1.0")
    implementation("net.serenity-bdd:serenity-core:4.1.0")
    implementation("net.serenity-bdd:serenity-ensure:4.1.0")
    implementation("net.serenity-bdd:serenity-cucumber:4.1.0")

    // Otros
    implementation("com.github.javafaker:javafaker:1.0.2")
    implementation("org.projectlombok:lombok:1.18.24")
    annotationProcessor("org.projectlombok:lombok:1.18.24")
    annotationProcessor("com.google.code.gson:gson:2.10.1")
}

tasks.test {
    maxParallelForks = Runtime.getRuntime().availableProcessors()
}
