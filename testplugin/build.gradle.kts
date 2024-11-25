import org.jetbrains.kotlin.config.KotlinCompilerVersion

plugins {
    `kotlin-dsl`
    kotlin("jvm") version "1.9.22"
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

group = "com.arrKhange1"

version = "1.0.0-SNAPSHOT"
val kotlinVersion = KotlinCompilerVersion.VERSION
val targetJavaVersion = 21
val fileName = "${project.name}-${project.version}.jar"

repositories {
    maven {
        name = "papermc"
        url = uri("https://repo.papermc.io/repository/maven-public/")
    }
}

dependencies {
    implementation("io.papermc.paper:paper-api:1.21.3-R0.1-SNAPSHOT")
    implementation("org.jetbrains.kotlin:kotlin-stdlib")
}

kotlin {
    jvmToolchain(targetJavaVersion)
}

tasks {
    val copyPluginToDevServer by registering(Copy::class) {
        println(project.layout.projectDirectory)
        from(project.layout.buildDirectory.file("libs/$fileName"))
        into(project.layout.projectDirectory.dir("../.server/plugins"))
    }

    val runDevServer by registering {
        doLast {
            exec {
                workingDir("../.server")
                commandLine("sh", "run.sh")
            }
        }
    }

    shadowJar {
        isZip64 = true
        archiveFileName.set(fileName)
    }

    build {
        dependsOn(shadowJar)
    }

    copyPluginToDevServer {
        dependsOn(build)
    }

    runDevServer {
        dependsOn(copyPluginToDevServer)
    }
}





