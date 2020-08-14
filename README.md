# Mercado libre Mobile Test

Within the features required by the test, these are the completed ones:
- Have a search functionality to look for mercado libre items
- List all items based on a query search
- Screen details view after clicking on a item the list
- All screens could be rotated as required without loosing data state

In summary, I liked the test, even if this is a small implementation, the test covers big part of mobile development environment on Android, the requirements were documented on a readable way so it makes easier for the actual implementation.

#### Some useful commands to execute the app:

```bash
#Execute Unit Test
$ ./gradlew testDebugUnitTest
```
```bash
#Execute Instrumented test on connected devices
$ ./gradlew connectedAndroidTest
```
```bash
#Buid apk
$ ./gradlew assembleDebug
```
```bash
#Install apk
$ ./gradlew installDebug
```

# Tech

  - Kotlin
  - [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel)
  - [Navigation Component](https://developer.android.com/guide/navigation)
  - [Paging Library](https://developer.android.com/topic/libraries/architecture/paging)
  - [Retrofit](https://square.github.io/retrofit/)
  - [Material Design](https://material.io)
  - [Koin - Dependency Injection](https://insert-koin.io/)

# Architecture
For this implementation, I decided to use a MVVM pattern given this provides high isolation of the different app layers and also, have a clear definition of each component responsibilities.

Additionally, a repository pattern was introduced to deal with the different data sources (Network and Database) that the app needs to interact with.

![Diagram](https://betabeers.com/static/uploads/blog/20190307_imagen_2.png)

