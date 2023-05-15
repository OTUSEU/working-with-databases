plugins {
    kotlin("jvm") version "1.8.21"

}


group = "ru.otus"
version = "1.0-SNAPSHOT"


repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-reflect:1.8.21")
    implementation(kotlin("stdlib"))
    val exposedVersion = "0.41.1"
    implementation("org.jetbrains.exposed:exposed-core:$exposedVersion") // Базовый модуль, содержит DSL api
    implementation("org.jetbrains.exposed:exposed-dao:$exposedVersion") // DAO api
    implementation("org.jetbrains.exposed:exposed-jdbc:$exposedVersion")

    implementation("org.xerial:sqlite-jdbc:3.41.2.1")
    implementation("org.slf4j:slf4j-simple:2.0.7")


    testImplementation("org.junit.jupiter:junit-jupiter-engine:5.9.3")
}

//tasks.register("prepareKotlinBuildScriptModel"){}
tasks.prepareKotlinBuildScriptModel {
}

tasks.withType<Test> {
    useJUnitPlatform()
}