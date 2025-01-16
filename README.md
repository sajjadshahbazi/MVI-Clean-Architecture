# MVI and Clean Architecture News Application

## Overview

This project is a sample application implementing **MVI Architecture** and **Clean Architecture**, along with **Jetpack Compose**, **Koin**, **Coroutines**, and **Navigation Component**. It fetches and displays the latest news for four companies (e.g., Microsoft, Apple, Google, Tesla) sequentially from the [News API](https://newsapi.org/). You need an API key from News API to make it functional.

---

## Table of Contents

- [Introduction](#introduction)
- [Features](#features)
- [Project Structure](#project-structure)
- [Architectures Used](#architectures-used)
    - [MVI Architecture](#mvi-architecture)
    - [Clean Architecture](#clean-architecture)
- [Technologies](#technologies)
- [Setup Instructions](#setup-instructions)
- [Adding New Features](#adding-new-features)

---

## Introduction

The goal of this project is to demonstrate a clean, scalable, and testable architecture for an Android app. It follows best practices and utilizes modern Android development tools and libraries.

---

## Features

- Fetches and displays news headlines with images for four companies.
- Utilizes **MVI Architecture** for state management and unidirectional data flow.
- Implements **Clean Architecture** for separation of concerns.
- Modular structure to easily add new features.
- Supports dependency injection using **Koin**.
- Uses **Fresco** for image loading (can be swapped with Glide or Coil).
- Fully built with **Jetpack Compose** for a modern UI experience.

---

## Project Structure

- **Presentation**: Contains UI-related code built with Jetpack Compose.
- **Domain**: Contains business logic, use cases, and entities.
- **Data**: Responsible for interacting with external data sources (e.g., APIs, databases).

---

## Architectures Used

### MVI Architecture

Model-View-Intent (MVI) provides a unidirectional data flow to ensure a predictable state for the UI.

- **Intent**: Represents user actions or events.
- **ViewModel**: Processes intents, fetches data from the repository, and updates the state.
- **State**: Immutable representation of the UI at any moment.
- **View**: Reacts to the state and renders the UI.

![MVI Architecture](https://github.com/sajjadshahbazi/MVI-Clean-Architecture/blob/main/media/MVI_detail.png?raw=true)

### Clean Architecture

Clean Architecture separates the code into layers to ensure modularity and maintainability.

- **Entities**: Business objects or data models.
- **Use Cases**: Contain business logic specific to the application.
- **Repositories**: Abstract data sources like APIs or local databases.
- **Frameworks & Drivers**: Interface with external tools and frameworks.

![Clean Architecture](https://github.com/sajjadshahbazi/MVI-Clean-Architecture/blob/main/media/Artboard_15587.png?raw=true)

---

## Technologies

- **Jetpack Compose**: Modern declarative UI toolkit for building Android apps.
- **Koin**: Lightweight dependency injection framework.
- **Coroutines**: For asynchronous programming and concurrency.
- **Fresco**: For efficient image loading and caching.
- **Navigation Component**: Handles in-app navigation.
- **Modularization**: Each feature is placed in a separate module for better scalability.

---

## Setup Instructions

1. Clone the repository:
   ```bash
   git clone https://github.com/sajjadshahbazi/MVI-Clean-Architecture.git
   ```
2. Navigate to the project directory:
   ```bash
   cd MVI-Clean-Architecture
   ```
3. Add your `API_KEY`:
    - Open the `build.gradle` file for the `data` module.
    - Add your API key:
      ```groovy
      buildConfigField("String", "API_KEY", "\"your_api_key_here\"")
      ```
4. Build and run the project:
   ```bash
   ./gradlew assembleDebug
   ```

---

## Adding New Features

To add a new feature:

1. **Create a new module**:
    - Name the module according to the feature (e.g., `featureUser`).
    - Follow the structure and practices used in the `companyNews` module.

2. **Define dependencies**:
    - Add dependencies in `buildSrc` for consistency across modules.
    - Reference these dependencies in the `build.gradle` file of your module.

3. **Use Fresco, Coil, or Glide for images**:
    - Currently, Fresco is implemented for image loading, but you can swap it with your preferred library.

4. **Follow architectural guidelines**:
    - Ensure the feature uses Clean Architecture and integrates with the existing MVI pattern.

---

This project demonstrates a robust and scalable architecture for building Android applications with modern tools and practices.

