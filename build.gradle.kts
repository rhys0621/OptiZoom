plugins {
    id("net.fabricmc.fabric-loom") version "1.15-SNAPSHOT"
    id("java")
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

val minecraftVersion = "26.2"
val loaderVersion = "0.19.3"
val fabricVersion = "0.152.2+26.2"

group = "me.rhys"
version = "1.2"

repositories {
    mavenCentral()
}

dependencies {
    minecraft("com.mojang:minecraft:$minecraftVersion")
    implementation("net.fabricmc:fabric-loader:$loaderVersion")
    implementation("net.fabricmc.fabric-api:fabric-api:$fabricVersion")
}

java {
    sourceCompatibility = JavaVersion.VERSION_25
    targetCompatibility = JavaVersion.VERSION_25

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

        exclude("lombok.config")
    }

    withType(JavaCompile::class.java) {
        options.release.set(25)
    }

    jar {
        from("LICENSE") {
            rename { "${it}_OptiZoom" }
        }

        duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    }

    withType<Copy> {
        duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    }

    named<Jar>("sourcesJar") {
        duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    }
}