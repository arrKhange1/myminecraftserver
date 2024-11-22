plugins {
    `kotlin-dsl`
}

group = "com.arrKhange1"
version = "1.0.0-SNAPSHOT"
val targetJavaVersion = 21

repositories {
    maven {
        name = "papermc"
        url = uri("https://repo.papermc.io/repository/maven-public/")
    }
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:1.21.3-R0.1-SNAPSHOT")
}

kotlin {
    jvmToolchain(targetJavaVersion)
}

tasks {
    val copyPluginToDevServer by registering(Copy::class) {
        val fileName = "${project.name}-${project.version}.jar"
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



    copyPluginToDevServer {
        dependsOn(build)
    }

    runDevServer {
        dependsOn(copyPluginToDevServer)
    }
}





