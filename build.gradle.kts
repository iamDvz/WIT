import org.jetbrains.kotlin.gradle.tasks.KotlinCompile


plugins {
    java
    `maven-publish`
    kotlin("jvm") version "1.7.21"
    id("com.github.johnrengelman.shadow") version "7.0.0"
}

group = "ru.iamdvz"
version = "1-SNAPSHOT"
description = "WIT"
java.sourceCompatibility = JavaVersion.VERSION_16

repositories {
    mavenLocal()
    maven(url = "https://jitpack.io")
    maven(url = "https://oss.sonatype.org/content/groups/public/")
    maven(url = "https://repo.codemc.org/repository/maven-public/")
    maven(url = "https://repo.papermc.io/repository/maven-public/")
    maven(url = "https://mvn.lumine.io/repository/maven/")
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    compileOnly("io.papermc.paper:paper-api:1.18.2-R0.1-SNAPSHOT")
    compileOnly("me.clip:placeholderapi:2.11.2")
    compileOnly("io.lumine:Mythic-Dist:5.0.1-SNAPSHOT")
}
tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "16"
}
tasks.withType<com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar> {
    archiveFileName.set("WIT-${version}.jar")
}
tasks.processResources {
    filesMatching("plugin.yml") {
        expand(project.properties)
    }
}

publishing {
    publications.create<MavenPublication>("maven") {
        from(components["java"])
    }
}

tasks.withType<JavaCompile>() {
    options.encoding = "UTF-8"
}
