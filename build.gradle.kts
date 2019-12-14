group = "com.merricklabs.adventofcode2019"

repositories {
    mavenCentral()
    jcenter()
    maven("https://jitpack.io")
}

plugins {
    id("de.fayard.buildSrcVersions") version Versions.de_fayard_buildsrcversions_gradle_plugin
    kotlin("jvm") version Versions.org_jetbrains_kotlin
}

dependencies {
    implementation(Libs.kotlin_stdlib_jdk8)
    implementation(Libs.slf4j_api)
    implementation(Libs.slf4j_jdk14)
    implementation(Libs.kotlin_logging)

    testImplementation(Libs.testng)
    testImplementation(Libs.kotlintest_runner_junit5)
    testImplementation(Libs.mockito_kotlin)
}

tasks {
    test {
        useTestNG()
    }

    compileKotlin {
        kotlinOptions {
            jvmTarget = "1.8"
        }
    }

    compileTestKotlin {
        kotlinOptions {
            jvmTarget = "1.8"
        }
    }
}