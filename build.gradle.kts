plugins {
    java
    id("com.github.johnrengelman.shadow") version "7.0.0"
}

group = "net.azisaba"
version = "1.0.0"

repositories {
    mavenCentral()
    maven { url = uri("https://oss.sonatype.org/content/repositories/snapshots/") }
    maven { url = uri("https://repo2.acrylicstyle.xyz/") }
}

dependencies {
    implementation("net.blueberrymc:native-util:1.2.6")
    implementation("org.javassist:javassist:3.28.0-GA")
    compileOnly("net.md-5:bungeecord-api:1.18-R0.1-SNAPSHOT")
}

tasks {
    withType<ProcessResources> {
        filteringCharset = "UTF-8"
        from(sourceSets.main.get().resources.srcDirs) {
            include("**")

            val tokenReplacementMap = mapOf(
                "version" to project.version,
                "name" to project.rootProject.name,
            )

            filter<org.apache.tools.ant.filters.ReplaceTokens>("tokens" to tokenReplacementMap)
        }

        duplicatesStrategy = DuplicatesStrategy.INCLUDE

        from(projectDir) { include("LICENSE") }
    }

    shadowJar {
        relocate("javassist", "net.azisaba.bungeeLog4j2Fix.libs.javassist")
        archiveFileName.set("BungeeLog4j2Fix-${project.version}.jar")
    }
}