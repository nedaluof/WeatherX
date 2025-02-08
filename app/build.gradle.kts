plugins {
  alias(libs.plugins.android.application)
  alias(libs.plugins.kotlin.android)
  alias(libs.plugins.kotlin.compose)
  alias(libs.plugins.ksp)
  alias(libs.plugins.hilt)
}

android {
  namespace = "com.nedaluof.weatherx"
  compileSdk = Integer.valueOf(libs.versions.target.sdk.get())

  defaultConfig {
    applicationId = "com.nedaluof.weatherx"
    minSdk = Integer.valueOf(libs.versions.min.sdk.get())
    targetSdk = Integer.valueOf(libs.versions.target.sdk.get())
    versionCode = Integer.valueOf(libs.versions.version.number.get())
    versionName = libs.versions.version.name.get()
    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
  }

  buildTypes {
    release {
      isDebuggable = true
      isMinifyEnabled = false
      proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
      signingConfig = signingConfigs.getByName("debug")
    }
  }
  compileOptions {
    isCoreLibraryDesugaringEnabled = true
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
  //compose
  implementation(libs.androidx.activity.compose)
  implementation(platform(libs.androidx.compose.bom))
  implementation(libs.androidx.ui)
  implementation(libs.androidx.ui.graphics)
  implementation(libs.androidx.ui.tooling.preview)
  implementation(libs.androidx.material3)
  implementation(libs.androidx.material3)
  implementation(libs.androidx.material.icons.extended)
  debugImplementation(libs.androidx.ui.tooling)
  debugImplementation(libs.androidx.ui.test.manifest)
  //permissions
  implementation(libs.accompanist.permissions)
  //location
  implementation(libs.play.services.location)
  //lifecycle
  implementation(libs.androidx.lifecycle.runtime.ktx)
  implementation(libs.androidx.lifecycle.viewmodel)
  //navigation component
  implementation(libs.compose.navigation.component)
  //coil image loader
  implementation(libs.coil.compose)
  implementation(libs.lottie.compose)
  //hilt dependencies injection
  implementation(libs.hilt)
  ksp(libs.hilt.compiler)
  implementation(libs.androidx.hilt.navigation.compose)
  //domain
  api(project(path = ":domain"))
  //desugar_jdk_libs
  coreLibraryDesugaring(libs.desugar.jdk.libs)
  //testing
  testImplementation(libs.junit)
  androidTestImplementation(libs.androidx.junit)
  androidTestImplementation(libs.androidx.espresso.core)
  androidTestImplementation(platform(libs.androidx.compose.bom))
  androidTestImplementation(libs.androidx.ui.test.junit4)
}