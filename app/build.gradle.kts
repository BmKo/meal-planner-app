import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.hilt)
    id("kotlin-kapt")
}

android {
    namespace = "com.bmko.mealplanner"
    compileSdk {
        version = release(36)
    }

    defaultConfig {
        applicationId = "com.bmko.mealplanner"
        minSdk = 28
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        val properties = Properties()
        properties.load(project.rootProject.file("local.properties").reader())
        buildConfigField("String", "APPWRITE_ENDPOINT", "\"${properties.getProperty("APPWRITE_ENDPOINT")}\"")
        buildConfigField("String", "APPWRITE_PROJECT_ID", "\"${properties.getProperty("APPWRITE_PROJECT_ID")}\"")
        buildConfigField("String", "DATABASE_ID", "\"${properties.getProperty("DATABASE_ID")}\"")
        buildConfigField("String", "MEALS_TABLE_ID", "\"${properties.getProperty("MEALS_TABLE_ID")}\"")
        buildConfigField("String", "ROTATIONS_TABLE_ID", "\"${properties.getProperty("ROTATIONS_TABLE_ID")}\"")
        buildConfigField("String", "PLANS_TABLE_ID", "\"${properties.getProperty("PLANS_TABLE_ID")}\"")
        buildConfigField("String", "INGREDIENTS_TABLE_ID", "\"${properties.getProperty("INGREDIENTS_TABLE_ID")}\"")
        // TODO: Remove when plans implemented
        buildConfigField("String", "PLAN_ID", "\"${properties.getProperty("PLAN_ID")}\"")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation("androidx.compose.material:material-icons-extended")

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)

    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)

    // Hilt / Dagger
    implementation(libs.dagger)
    kapt(libs.dagger.compiler)
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)
    implementation(libs.hilt.navigation.compose)

    // Appwrite
    implementation(libs.appwrite)
}