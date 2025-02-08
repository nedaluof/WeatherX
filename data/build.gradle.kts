import java.util.Properties

plugins {
  alias(libs.plugins.android.library)
  alias(libs.plugins.kotlin.android)
  alias(libs.plugins.ksp)
  alias(libs.plugins.hilt)
}

android {
  namespace = "com.nedaluof.data"
  compileSdk = Integer.valueOf(libs.versions.target.sdk.get())

  defaultConfig {
    minSdk = Integer.valueOf(libs.versions.min.sdk.get())
    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    consumerProguardFiles("consumer-rules.pro")   
    buildConfigField("String", "BASE_URL", "\"https://api.openweathermap.org/data/2.5/\"")
    buildConfigField("String", "WEATHER_IMAGE_URL", "\"https://openweathermap.org/img/wn/\"")
    val localProperties = Properties().apply {
      val localPropertiesFile = rootProject.file("local.properties")
      if (localPropertiesFile.exists()) {
        localPropertiesFile.inputStream().use { load(it) }
      }
    }
    buildConfigField("String", "API_KEY", "\"${localProperties["API_KEY"]}\"")
  }

  buildTypes {
    release {
      isMinifyEnabled = false
      proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
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
    buildConfig = true
  }
}

dependencies {
  //hilt dependencies injection
  implementation(libs.hilt)
  ksp(libs.hilt.compiler)
  //datastore-preferences
  implementation(libs.datastore.preferences)
  //remote
  implementation(libs.retrofit) { exclude(module = "okhttp") }
  implementation(libs.retrofit.moshi.converter)
  implementation(libs.logging.interceptor)
  implementation(libs.moshi)
  ksp(libs.moshi.kotlin.codegen)
  //Coroutines
  api(libs.coroutines)
  //room database
  implementation(libs.bundles.room)
  ksp(libs.room.compiler)
  //testing
  testImplementation(libs.junit)
  androidTestImplementation(libs.androidx.junit)
  androidTestImplementation(libs.androidx.espresso.core)
}