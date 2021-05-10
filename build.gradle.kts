plugins {
    java
}

group = "com.epam.l2plus"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.slf4j:slf4j-api:1.7.25")
    runtimeOnly("org.slf4j:slf4j-simple:1.7.30")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.6.0")

}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}