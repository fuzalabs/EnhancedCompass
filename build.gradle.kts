plugins {
    java
}

group = "br.com.fuzalabs.enhancedcompass"
version = "0.1.1"

repositories {
    mavenCentral()
    maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
    maven("https://oss.sonatype.org/content/repositories/snapshots")
    maven("https://repo.papermc.io/repository/maven-public/")
}

dependencies {
    // Using Bukkit API 1.8.8 for maximum compatibility (1.8 to 1.21)
    compileOnly("org.bukkit:bukkit:1.8.8-R0.1-SNAPSHOT")
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}

