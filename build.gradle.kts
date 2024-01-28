plugins {
    kotlin("multiplatform") version "1.9.22"
}

repositories {
    mavenCentral()
}

kotlin {
    linuxX64("linux64") {
        binaries {
            executable()
        }
    }
    linuxArm64("linuxArm") {
        binaries {
            executable()
        }
    }

    sourceSets {
        commonMain.dependencies {
            val ktorVersion = "2.3.7"
            implementation("org.jetbrains.kotlinx:kotlinx-io-core:0.3.0")
            implementation("com.kgit2:kommand:2.0.1")
            implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.0-RC2")
            implementation("io.ktor:ktor-server-core:$ktorVersion")
            implementation("io.ktor:ktor-server-cio:$ktorVersion")
            implementation("io.ktor:ktor-server-websockets:$ktorVersion")
        }
    }
}
