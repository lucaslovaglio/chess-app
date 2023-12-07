plugins {
    java
    application
    kotlin("jvm") version "1.9.0"
    id("org.openjfx.javafxplugin").version("0.0.13")

}

group = "edu.austral.dissis.chess"
version = "1.0.0"

repositories {
//    mavenLocal()
    mavenCentral()
    maven {
        url = uri("https://maven.pkg.github.com/austral-ingsis/chess-ui")
        credentials {
            username = "lucaslovaglio"
            password = "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"
        }
    }
    maven {
        url = uri("https://maven.pkg.github.com/austral-ingsis/chess-simple-client-server")
        credentials {
            username = "lucaslovaglio"
            password = "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"
        }
    }
}

dependencies {
    implementation(platform("org.jetbrains.kotlin:kotlin-bom"))
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("edu.austral.dissis.chess:chess-ui:2.0.1")
    implementation("edu.austral.dissis.chess:simple-client-server:1.2.0")
    testImplementation(kotlin("test"))
    testImplementation("org.testng:testng:7.1.0")
}

tasks.test {
    useJUnitPlatform()
}

javafx {
    version = "18"
    modules = listOf("javafx.graphics")
}

application {
    // Define the main class for the application.
    mainClass.set("edu.austral.dissis.chess.AppKt")
}