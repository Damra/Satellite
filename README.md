# Satellite

Android - Koin - Coroutines - Flow - Live Data - MVVM

Satellite is a simple project to study and play with some android components, architecture and tools
for Android development.

![GitHub Cards Preview](https://github.com/Damra/Satellite/blob/main/screenshots/screenshots.png)

## Development setup

You require the latest Android Studio Arctic Fox | 2021.3.1 Patch 3 to be able to build the app.

## Tech Stack

Satellite list and detail feature uses MVVM

### Libraries

- Application entirely written in [Kotlin](https://kotlinlang.org)
- Asynchronous processing using [Coroutines](https://kotlin.github.io/kotlinx.coroutines/)
- [Flow](https://developer.android.com/kotlin/flow) is a efficient way to handle the stream of data
  asynchronously that executes sequentially.
- [LiveData](https://developer.android.com/topic/libraries/architecture/livedata)  is an observable
  data holder class. Unlike a regular observable, LiveData is lifecycle-aware.
- [Navigation component](https://developer.android.com/guide/navigation/navigation-getting-started)
  refers to the interactions that allow users to navigate across different pieces of content within
  your app.
- Uses [okio](https://github.com/square/okio) to make it much easier to access, store, and process
  your data.
- Uses [Koin](https://insert-koin.io/) for dependency injection

## License

```
Copyright 2021 Damra KOÇ

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
