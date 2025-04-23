plugins {
    kotlin("multiplatform")
    kotlin("native.cocoapods")
    id("com.android.library")
    kotlin("plugin.serialization")
}
group = "com.example.schedulingapp"

kotlin {
    android()
    iosX64()
    iosArm64()
    iosSimulatorArm64()

    val frameworkName = "shared"

    cocoapods {
        summary = "Some description for the Shared Module"
        homepage = "Link to the Shared Module homepage"
        version = "1.0"
        ios.deploymentTarget = "14.1"
        podfile = project.file("../iosApp/Podfile")
        framework {
            baseName = frameworkName
            isStatic = false
            freeCompilerArgs = listOf("-Xobjc-generics")
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                api("org.jetbrains.kotlinx:kotlinx-datetime:0.4.0")
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")
                implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.5.0")
                implementation("com.russhwolf:multiplatform-settings-no-arg:1.1.1")
                implementation("com.russhwolf:multiplatform-settings-coroutines:1.1.1")
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.3")
                implementation("io.ktor:ktor-client-core:2.3.5")
                implementation("io.ktor:ktor-client-content-negotiation:2.3.5")
                implementation("io.ktor:ktor-serialization-kotlinx-json:2.3.5")
            }
        }

        val iosMain by creating {
            dependsOn(commonMain)
            dependencies {
                implementation("io.ktor:ktor-client-darwin:2.0.0") // 👈 required
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val iosX64Main by getting {
            dependsOn(iosMain)
        }
        val iosArm64Main by getting {
            dependsOn(iosMain)
        }
        val iosSimulatorArm64Main by getting {
            dependsOn(iosMain)
        }

        val iosTest by creating {
            dependsOn(commonTest)
        }
    }

    listOf(iosArm64(), iosSimulatorArm64()).forEach {
        it.binaries.framework {
            baseName = frameworkName
            isStatic = false
            freeCompilerArgs += listOf("-Xobjc-generics")
        }
    }

    jvmToolchain(11)
}

android {
    namespace = "com.example.schedulingapp"
    compileSdk = 33
    defaultConfig {
        minSdk = 21
        targetSdk = 33
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
    kotlinOptions.jvmTarget = "11"
}

tasks.register("assembleXCFramework") {
    dependsOn(
        tasks.named("linkReleaseFrameworkIosArm64"),
        tasks.named("linkReleaseFrameworkIosSimulatorArm64")
    )

    doLast {
        val outputDir = buildDir.resolve("XCFrameworks/Release")
        outputDir.mkdirs()

        val frameworkArm64 = buildDir.resolve("bin/iosArm64/ReleaseFramework/shared.framework")
        val frameworkSim = buildDir.resolve("bin/iosSimulatorArm64/ReleaseFramework/shared.framework")

        val xcframeworkOutput = outputDir.resolve("shared.xcframework")

        exec {
            commandLine(
                "xcodebuild", "-create-xcframework",
                "-framework", frameworkArm64.absolutePath,
                "-framework", frameworkSim.absolutePath,
                "-output", xcframeworkOutput.absolutePath
            )
        }
    }
}