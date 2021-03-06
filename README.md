# The docs have been updated to have been updated to use Kotlin/js gradle plugin, please refer to the docs ("[Building Web Applications with React and Kotlin/JS](https://play.kotlinlang.org/hands-on/Building%20Web%20Applications%20with%20React%20and%20Kotlin%20JS/01_Introduction)") to see the updated logs

# react-kotlin-js-gradle
This project is an implementation of the "[Building Web Applications with React and Kotlin/JS](https://play.kotlinlang.org/hands-on/Building%20Web%20Applications%20with%20React%20and%20Kotlin%20JS/01_Introduction)" 
hands-on lab using the Gradle Kotlin/JS plugin.

The current documentation (December 2019) uses the 'create-react-app-kotlin-app' and will 
be updated to the Kotlin/JS plugin, this is a sample in the meantime to help people who want to start
a project with the Kotlin/JS plugin. There may be better ways of handling the gradle tasks.

The major differences come in how dependency management is handled. There's some information here:
https://kotlinlang.org/docs/reference/js-project-setup.html

## Running
`./gradlew run --continuous`

## Building prod package
`./gradlew build`
The production files are available in the `build/distributions` folder

## Gradle config
1. `settings.gradle.kts`: in this project, the `rootProject.name` is used as the js bundle name.
2. `src/main/resources/index.html` script tag replaces `%bundleName%` with `projectName.bundle.js`
3. Webpack config is overridden to expose the devServer on port 9000.
   1. Other webpack settings can be overridden in the `tasks.withType<KotlinWebpack>` code block. 
