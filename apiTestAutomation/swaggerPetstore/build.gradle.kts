plugins {
    id ("java")
    id ("net.serenity-bdd.serenity-gradle-plugin") version "4.0.46"
}

group = "com.paloit"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(platform("org.junit:junit-bom:5.9.1"))
    implementation("org.junit.jupiter:junit-jupiter")
    implementation("junit:junit:4.13.2")

    implementation("net.serenity-bdd:serenity-screenplay-rest:4.1.0")
    implementation("net.serenity-bdd:serenity-screenplay:4.1.0")
    implementation("net.serenity-bdd:serenity-core:4.1.0")
    implementation("net.serenity-bdd:serenity-ensure:4.1.0")
    implementation("net.serenity-bdd:serenity-cucumber:4.1.0")

    implementation("io.cucumber:cucumber-java:7.15.0")
    implementation("io.cucumber:cucumber-junit:7.15.0")

    implementation("com.github.javafaker:javafaker:1.0.2")

    compileOnly("org.projectlombok:lombok:1.18.24")
    annotationProcessor("org.projectlombok:lombok:1.18.24")

    annotationProcessor("com.google.code.gson:gson:2.10.1")
}

tasks.test {
    maxParallelForks = Runtime.getRuntime().availableProcessors().div(2) ?: 1
}
