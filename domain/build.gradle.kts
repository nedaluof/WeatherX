plugins {
  alias(libs.plugins.android.library)
  alias(libs.plugins.kotlin.android)
  alias(libs.plugins.ksp)
  alias(libs.plugins.hilt)
}

android {
  namespace = "com.nedaluof.domain"
  compileSdk = Integer.valueOf(libs.versions.target.sdk.get())

  defaultConfig {
    minSdk = Integer.valueOf(libs.versions.min.sdk.get())
    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    consumerProguardFiles("consumer-rules.pro")
    multiDexEnabled = true
  }

  buildTypes {
    release {
      isMinifyEnabled = false
      proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
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
}

dependencies {
  implementation(libs.androidx.core.ktx)
  //hilt dependencies injection
  implementation(libs.hilt)
  ksp(libs.hilt.compiler)
  //data
  api(project(path = ":data"))
  //desugar_jdk_libs
  coreLibraryDesugaring(libs.desugar.jdk.libs)
  //testing
  testImplementation(libs.junit)
  androidTestImplementation(libs.androidx.junit)
  androidTestImplementation(libs.androidx.espresso.core)
}