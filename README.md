# PixabayApp
An android application which uses Pixabay API for listing images.

## Prerequisites

#### 1. Check API Key

If the app cannot list images, check the API key on build.gradle.kts(:app).

	buildConfigField("String", "PIXABAY_API_KEY", "\"23033662-872c880032b08e36dbb8fc0dc\"")

#### 2. Ready to run.

## Features
- Image Features (Searching, Listing, Deleting)
- Caching Results (Offline Capability)
- Pull to Refresh
- Unit Tests

## Tech Stack
- **Kotlin** - Officially supported programming language for Android development by Google
- **Kotlin DSL** - Alternative syntax to the Groovy DSL
- **Coroutines** - Perform asynchronous operations
- **Flow** - Handle the stream of data asynchronously
- **Android Architecture Components**
  - **LiveData** - Notify views about data changes
  - **Room** - Persistence library
  - **ViewModel** - UI related data holder
  - **ViewBinding** - Allows to more easily write code that interacts with views
- **Hilt** - Dependency Injection framework
- **Retrofit** - Networking library
- **Moshi** - A modern JSON library for Kotlin and Java
- **Coil** - Image loading library
 
 ## Local Unit Tests
- The project uses MockWebServer (scriptable web server) to test Pixabay API interactions.

## Screenshots
![pixabay_app](https://user-images.githubusercontent.com/25778714/130410042-3f5d0962-3e1b-4c03-826c-ee89eb67a8d2.jpg)

## Architecture
![arch500](https://user-images.githubusercontent.com/25778714/113482640-3801f100-94a8-11eb-98d6-e15cb21a905b.png)

