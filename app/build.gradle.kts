import java.io.ByteArrayOutputStream

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.plugin.serialization") version "2.2.10"
    id("kotlin-kapt")
    id("maven-publish")
}

group = "org.nitri.ors"
version = (findProperty("LIB_VERSION") as String?) ?: "0.1.0"

android {
    namespace = "org.nitri.ors"
    compileSdk = 36

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    testOptions {
        targetSdk = 35
    }

    lint {
        targetSdk = 35
    }

    publishing {
        singleVariant("release") {
            withSourcesJar()
            withJavadocJar()
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)

    implementation(libs.retrofit)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.retrofit2.kotlinx.serialization.converter)
    implementation(libs.logging.interceptor)
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.androidx.monitor)
    implementation(libs.androidx.junit.ktx)

    // Unit testing
    testImplementation(libs.junit)
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.mockito.core)
    testImplementation(libs.mockito.kotlin)

    androidTestImplementation(libs.kotlinx.coroutines.test)
    androidTestImplementation(libs.androidx.runner)

    androidTestImplementation(libs.androidx.rules)
}

publishing {
    publications {
        create<MavenPublication>("mavenRelease") {
            // Delay until the Android plugin has created components
            afterEvaluate {
                from(components["release"])
            }

            groupId = project.group.toString()
            artifactId = "ors-android-client"
            version = project.version.toString()

            pom {
                name.set("ORS Android Client")
                description.set("Android Client Library for OpenRouteService APIs")
                url.set("https://github.com/Pygmalion69/ors-android-client")
                licenses {
                    license {
                        name.set("Apache-2.0")
                        url.set("https://www.apache.org/licenses/LICENSE-2.0")
                    }
                }
                scm {
                    url.set("https://github.com/Pygmalion69/ors-android-client")
                    connection.set("scm:git:https://github.com/Pygmalion69/ors-android-client.git")
                    developerConnection.set("scm:git:ssh://git@github.com/Pygmalion69/ors-android-client.git")
                }
                developers {
                    developer {
                        id.set("pygmalion")
                        name.set("Serge Helfrich")
                    }
                }
            }
        }
    }
    repositories {
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/Pygmalion69/ors-android-client")
            credentials {
                username = System.getenv("GPR_USER") ?: (findProperty("gpr.user") as String?)
                password = System.getenv("GPR_TOKEN") ?: (findProperty("gpr.token") as String?)
            }
        }
        // mavenLocal()  // uncomment for local testing
    }
}



