import org.jetbrains.compose.ExperimentalComposeLibrary
import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.bcv)
    id("module.publication")
}

kotlin {
    explicitApi()
    applyDefaultHierarchyTemplate()

    androidTarget {
        publishLibraryVariants("release")
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_21)
        }
    }

    jvm("desktop") {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_21)
        }
    }

    js(IR) {
        outputModuleName = "RichTextEditorJs"
        browser()
    }
    @OptIn(ExperimentalWasmDsl::class)
    wasmJs {
        outputModuleName = "RichTextEditorComposeWasm"
        browser {
            testTask {
                enabled = false
            }
        }
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "ComposeApp"
            isStatic = true
        }
    }

    sourceSets.commonMain.dependencies {
        implementation(compose.runtime)
        implementation(compose.foundation)
        implementation(compose.material)
        implementation(compose.material3)

        // HTML parsing library
        implementation(libs.ksoup.html)
        implementation(libs.ksoup.entities)

        // Markdown parsing library
        implementation(libs.jetbrains.markdown)
    }

    sourceSets.commonTest.dependencies {
        implementation(kotlin("test"))
        @OptIn(ExperimentalComposeLibrary::class)
        implementation(compose.uiTest)
    }

    sourceSets.named("desktopTest").dependencies {
        implementation(compose.desktop.uiTestJUnit4)
        implementation(compose.desktop.currentOs)
    }
}

android {
    namespace = "com.mohamedrejeb.richeditor.compose"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
        consumerProguardFile("proguard-rules.pro")
    }

    java {
        toolchain {
            languageVersion.set(
                JavaLanguageVersion.of(JavaVersion.VERSION_21.majorVersion.toInt())
            )
        }
    }
    kotlin {
        jvmToolchain {
            languageVersion.set(
                JavaLanguageVersion.of(JavaVersion.VERSION_21.majorVersion.toInt())
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }
    buildFeatures {
        compose = true
    }
}

apiValidation {
    @OptIn(kotlinx.validation.ExperimentalBCVApi::class)
    klib {
        enabled = true
    }
}