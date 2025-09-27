import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.jetbrainsCompose)
}

kotlin {
    jvm()

    sourceSets.jvmMain.dependencies {
        implementation(projects.deps.libs.richTextEditor.sample.common)
        implementation(compose.desktop.currentOs)
    }
}

compose.desktop {
    application {
        mainClass = "MainKt"
        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "compose-richeditor"
            packageVersion = "1.0.0"

            macOS {
                jvmArgs(
                    "-Dapple.awt.application.appearance=system"
                )
            }
        }
    }
}