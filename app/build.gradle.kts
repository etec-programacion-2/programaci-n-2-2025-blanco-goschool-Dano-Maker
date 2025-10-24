plugins {
    alias(libs.plugins.kotlin.jvm)
    application
    id("org.openjfx.javafxplugin") version "0.1.0"
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
    testImplementation(libs.junit.jupiter.engine)
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    implementation(libs.guava)
    implementation(libs.bundles.javafx)
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21) // Versión estable
    }
}

// ✅ Configuración de JavaFX
javafx {
    version = "21" // Debe coincidir con tu versión de Java
    modules = listOf("javafx.controls", "javafx.fxml")
}

application {
    mainClass = "org.example.AppKt"
}

tasks.named<Test>("test") {
    useJUnitPlatform()
}