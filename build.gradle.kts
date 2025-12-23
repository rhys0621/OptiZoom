plugins {
    id("fabric-loom") version "1.14-SNAPSHOT"
    id("java")
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

val minecraftVersion = "1.21.11"
val loaderVersion = "0.18.2"
val fabricVersion = "0.139.5+1.21.11"

group = "me.rhys"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven {
        url = uri("https://jitpack.io")

        content {
            includeGroup("com.github.Oryxel")
        }
    }
}

dependencies {
    implementation("com.google.code.gson:gson:2.13.2")

    compileOnly("org.projectlombok:lombok:1.18.36")
    annotationProcessor("org.projectlombok:lombok:1.18.36")

    minecraft("com.mojang:minecraft:$minecraftVersion")
    mappings(loom.officialMojangMappings())
    modImplementation("net.fabricmc:fabric-loader:$loaderVersion")
    modImplementation("net.fabricmc.fabric-api:fabric-api:$fabricVersion")
}

java {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21

    withSourcesJar()
}

base {
    archivesName.set("OptiZoom")
}

tasks {
    processResources {
        inputs.property("version", version)

        filesMatching("fabric.mod.json") {
            expand("version" to version)
        }

        exclude("**/*.proto")
        exclude("lombok.config")
    }

    withType(JavaCompile::class.java) {
        options.release.set(21)
    }

    jar {
        from("LICENSE") {
            rename { "${it}_OptiZoom" }
        }

        duplicatesStrategy = DuplicatesStrategy.EXCLUDE

        val mappingFiles = provider {
            rootProject.configurations.mappings.get().map(::zipTree)
        }

        inputs.files(mappingFiles).withPropertyName("mappingFiles")

//        from(files(mappingFiles.get())) {
//            include("mappings/mappings.tiny")
//        }
    }

    withType<Copy> {
        duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    }

    wrapper {
        version = "9.2.1"
    }

    named<Jar>("sourcesJar") {
        duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    }

    shadowJar {
        configurations = listOf(project.configurations.shadow.get())
        exclude("META-INF")
        archiveClassifier.set("fat")

        val mappingFiles = provider {
            rootProject.configurations.mappings.get().map(::zipTree)
        }

        inputs.files(mappingFiles).withPropertyName("mappingFiles")

        from(files(mappingFiles.get())) {
            include("mappings/mappings.tiny")
        }
    }


    remapJar {
        archiveClassifier.set("")
    }

    register<Jar>("remapShadowJar") {
        dependsOn(shadowJar)
        mustRunAfter(shadowJar)

        from(zipTree(shadowJar.get().archiveFile))
        archiveClassifier.set("fat")
    }
}

sourceSets {
    main {
        java.srcDirs(
            "src/main/java",
        )
    }
}
