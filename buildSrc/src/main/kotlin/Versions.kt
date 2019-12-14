import kotlin.String

/**
 * Find which updates are available by running
 *     `$ ./gradlew buildSrcVersions`
 * This will only update the comments.
 *
 * YOU are responsible for updating manually the dependency version. */
object Versions {
    const val mockito_kotlin: String = "2.2.0"

    const val de_fayard_buildsrcversions_gradle_plugin: String = "0.7.0"

    const val kotlin_logging: String = "1.7.8"

    const val kotlintest_runner_junit5: String = "3.4.2"

    const val org_jetbrains_kotlin: String = "1.3.61"

    const val slf4j_api: String = "1.7.29"

    const val slf4j_jdk14: String = "1.7.29"

    const val testng: String = "7.1.0"

    /**
     *
     *   To update Gradle, edit the wrapper file at path:
     *      ./gradle/wrapper/gradle-wrapper.properties
     */
    object Gradle {
        const val runningVersion: String = "5.4"

        const val currentVersion: String = "6.0.1"

        const val nightlyVersion: String = "6.2-20191213232146+0000"

        const val releaseCandidate: String = ""
    }
}
