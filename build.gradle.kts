import org.jetbrains.kotlin.gradle.targets.js.webpack.KotlinWebpack
import org.jetbrains.kotlin.gradle.targets.js.webpack.KotlinWebpackConfig.DevServer

plugins {
    kotlin("js") version "1.3.61"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven(url = "https://kotlin.bintray.com/kotlin-js-wrappers/")
    jcenter()
}

dependencies {

}

kotlin{
    target.browser {
    }

    sourceSets {
        main {
            dependencies {
                implementation(kotlin("stdlib-js"))

                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core")

                // include for client-side
                implementation("org.jetbrains:kotlin-react:16.9.0-pre.89-kotlin-1.3.60")
                implementation("org.jetbrains:kotlin-react-dom:16.9.0-pre.89-kotlin-1.3.60")
                implementation("org.jetbrains:kotlin-styled:1.0.0-pre.89-kotlin-1.3.60")


                implementation(npm("react", "16.12.0"))
                implementation(npm("react-dom", "16.12.0"))
                implementation(npm("styled-components", "4.4.1"))
                implementation(npm("inline-style-prefixer", "5.1.0"))
                implementation(npm("react-player", "1.14.2"))
            }
        }
    }
}

tasks.withType<KotlinWebpack> {
    //make sure %bundleName% is replaced with the actual bundle name
    doLast {
        copy {
            from(file("$projectDir/src/main/resources"))
            into(compilation.output.resourcesDir.canonicalPath)
            filter { line -> line.replace("%bundleName%", "${rootProject.name}.bundle.js") }
        }
    }

    //webpack config
    outputFileName = "${rootProject.name}.bundle.js"
    devServer = DevServer(
        port = 9000,
        contentBase = listOf(compilation.output.resourcesDir.canonicalPath)
    )
}

//make sure %bundleName% is replaced with the actual bundle name for the prod package
tasks.register<Copy>("copyIndex") {
    from(file("$projectDir/src/main/resources"))
    into(file("$buildDir/distributions"))
    filter { line -> line.replace("%bundleName%", "${rootProject.name}.bundle.js") }
}


tasks.named("build") {
    dependsOn(":copyIndex")
}

