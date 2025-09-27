import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.jetbrainsCompose)
}

kotlin {
    js(IR) {
        outputModuleName = "RichTextEditorCoilJs"
        browser()
        binaries.executable()
    }

    @OptIn(ExperimentalWasmDsl::class)
    wasmJs {
        outputModuleName = "RichTextEditorCoilWasm"
        browser()
        binaries.executable()
    }

    sourceSets.commonMain.dependencies {
        implementation(projects.deps.libs.richTextEditor.sample.common)
    }
}
