plugins {
    id("java-library")
    alias(libs.plugins.jetbrains.kotlin.jvm)
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
    implementation(project(":domain"))
    implementation(libs.kotlinx.coroutines.core)
    //koin
    implementation(platform(libs.koin.bom))
    implementation(libs.koin.core)
    testImplementation(libs.junit)
    testImplementation(libs.mockito.kotlin)
    testImplementation(kotlin("test"))
}