<h1 align="center">WeatherX</h1>
<p align="center">
  <a href="https://opensource.org/licenses/Apache-2.0"><img alt="License" src="https://img.shields.io/badge/License-Apache%202.0-blue.svg"/></a>
  <a href="https://android-arsenal.com/api?level=21"><img alt="API" src="https://img.shields.io/badge/API-21%2B-brightgreen.svg?style=flat"/></a>
</p>

<p align="center">  
    WeatherX 🌡️☁️❄️☀️ Android application demonstrate various Android development best practices
</p>

<p align="center">
        <img src="https://raw.githubusercontent.com/nedaluof/WeatherX/refs/heads/master/art/weatherx.png" width="1024">
</p>

## 🌀 Tech stack
- [Kotlin](https://kotlinlang.org/)
- [Coroutines](https://github.com/Kotlin/kotlinx.coroutines) for asynchronous tasks.
- [ksp](https://github.com/google/ksp): Kotlin Symbol Processing for code generation.
- Jetpack Libraries:
    - Jetpack Compose: Android’s modern declarative UI-toolkit.
    - [Android Architecture Components](https://developer.android.com/topic/libraries/architecture)
        - Lifecycle: Observe Android lifecycles and handle UI states based on the lifecycle changes.
        - ViewModel: Manage UI-related data.
        - [Room Database](https://developer.android.com/training/data-storage/room) : provides an abstraction layer over SQLite to allow fluent database access while harnessing the full power of SQLite.
        - [Hilt](https://dagger.dev/hilt/): dependency injection library for Android that reduces the boilerplate of doing manual dependency injection in your project.
        - [Compose Navigation Component](https://developer.android.com/develop/ui/compose/navigation) : The Navigation component provides support for Jetpack Compose applications. You can navigate between composables while taking advantage of the Navigation component's infrastructure and features., supported
          by [Hilt Compose Navigation](https://developer.android.com/jetpack/compose/libraries#hilt).
- [Retrofit2 & OkHttp3](https://github.com/square/retrofit): to request REST APIs.
- [Moshi](https://github.com/square/moshi/): A modern JSON library for Kotlin and Java.
- [Coil Compose](https://coil-kt.github.io/coil/getting_started/): Loading images from network.
- [Lottie Compose](https://github.com/airbnb/lottie/blob/master/android-compose.md).
- [Accompanist Permissions](https://google.github.io/accompanist/permissions/) A library which provides Android runtime permissions support for Jetpack Compose.

- Architecture
    - The app following MVVM Clean Architecture (View - ViewModel - Model) - Domain layer - Data
      layer with Repository Pattern, Which facilitates separation of concerns

<p align="center">
<br/>
    <img src="https://raw.githubusercontent.com/nedaluof/WeatherX/refs/heads/master/art/mad_arch_overview.png?raw=true" width="350">
<br>
</p>


## Weather API KEY
<p align="start">  
    before build the project an to be able to use weather forecast API you need to get your own
    <br>
    API key from 
    <a href="https://openweathermap.org/">openweathermap</a> then add it to <b>local.properties<b/> file
    <br>
</p>

```
API_KEY = [YOUR_API_KEY]
```
<br>




### License

```
Copyright 2025 Nedal Hasan ABDALLAH (NedaluOf)

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing,
software distributed under the License is distributed on an 
"AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
either express or implied. See the License for the specific 
language governing permissions and limitations under the License.

```