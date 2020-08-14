
plugins {
    kotlin("jvm") version "1.3.72"
    `java-library`
    `maven-publish`
}

group = "de.moritzruth.khalk"
version = "1.0.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
}

tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
}

publishing {
    publications {
        create<MavenPublication>("khalk") {
            from(components["java"])
        }
    }
}
