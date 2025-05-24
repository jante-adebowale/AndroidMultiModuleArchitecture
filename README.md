# Android Multi-Module Architecture

## Overview
A robust and scalable Android project template demonstrating modern multi-module architecture with Kotlin and Jetpack Compose. It features clean separation of concerns across layers and supports both MVVM and MVI architectural patterns for feature modules.

Studying this project will provide valuable insights into effectively modularizing your Android app while adopting best practices for code organization, navigation and build configuration. It serves as a practical example of Android app modularization done right.

Connects to a Ktor backend [here](https://github.com/jante-adebowale/ktor-jwt-auth) using JWT (JSON Web Token) authentication.
It demonstrates a full login, auto-refresh token handling, and auto-logout flow for secure user sessions.

### 📸 Screenshots (Light theme)
![Screenshot showing Login and Home screen](https://github.com/jante-adebowale/AndroidMultiModuleArchitecture/blob/master/screenshots/mtm_light.jpg?raw=true "Screenshot showing Login and Home screen")
### 📸 Screenshots (Dark theme)
![Screenshot showing Login and Home screen](https://github.com/jante-adebowale/AndroidMultiModuleArchitecture/blob/master/screenshots/mtm_dark.jpg?raw=true "Screenshot showing Login and Home screen")

## ✨Features

-  Modularized project structure for better maintainability and scalability
-  Separation of concerns with core, data, domain, and presentation modules
-  Supports MVVM and MVI architectural patterns
-  Type-safe navigation across modules for safer and clearer routing
-  JWT Authentication (Access + Refresh Tokens)
-  Auto Refresh Expired Access Tokens
-  Auto Logout When Refresh Token Expires
-  Secure Token Storage Using Jetpack Security
-  Built Fully with Jetpack Compose
-  Retrofit + OkHttp Integration
-  Koin for Dependency Injection

## Type-Safe Navigation
Navigating between features in a multi-module Android project can quickly become error-prone if relying on plain strings or intents. This project embraces type-safe navigation to ensure:

-  Compile-time safety: Avoid runtime crashes due to invalid route names or missing arguments
-  Clear contracts: Navigation actions are defined as sealed classes or strongly-typed interfaces shared between modules
-  Better refactoring support: IDE-aware navigation paths make renaming and restructuring simpler
-  Decoupled modules: Modules expose navigation APIs rather than direct implementation details, preserving encapsulation
-  Koin for Dependency Injection

For more information on modularizing
your Android app, refer to the
[Guide to Android App Modularization](https://developer.android.com/topic/modularization).

## 🧰 Tech Stack

- Kotlin
- Retrofit
- Coroutines + Flow
- Type Safe Navigation
- Koin for Dependency Injection
- Jetpack DataStore (for token storage)
- OkHttp Interceptor (for token refreshing)
- Jetpack Compose (optional UI layer)
- Material 3 (Light & Dark)

## 🔐 Auth Flow

1. Register/Login with Ktor backend.
2. Save access + refresh tokens securely.
3. Automatically add `Authorization: Bearer <token>` headers.
4. Detect 401, use refresh token to get a new access token.

## 🛡️ Token Storage
- Access token & Refresh token are stored securely using Jetpack Security's EncryptedSharedPreferences.

**Connect with me on:**
* [Portfolio](https://www.janteadebowale.com)
* [Youtube](https://www.youtube.com/@jante-adebowale)
* [LinkedIn](https://www.linkedin.com/in/jante-adebowale)
* [Github](https://github.com/jante-adebowale)


