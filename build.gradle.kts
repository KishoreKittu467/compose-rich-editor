plugins {
//    id("root.deps.libs.rich_text_editor.publication")
    alias(libs.plugins.androidLibrary).apply(false)
    alias(libs.plugins.androidApplication).apply(false)
//    alias(libs.plugins.kotlinAndroid).apply(false)
    alias(libs.plugins.kotlinMultiplatform).apply(false)
    alias(libs.plugins.jetbrainsCompose).apply(false)
    alias(libs.plugins.composeCompiler).apply(false)
    alias(libs.plugins.bcv).apply(false)
}
