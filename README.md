# MVI Clean Architecture Project

This project demonstrates the implementation of a **Model-View-Intent (MVI)** architecture combined with **Clean Architecture** principles. It utilizes modern Android tools and libraries, such as **Jetpack Compose**, **Coroutines**, **Koin**, and **Navigation Component**, to create a modular, scalable, and testable application for displaying recent news.

## Overview

The application fetches news articles for four predefined companies (e.g., Microsoft, Apple, Google, Tesla) and displays them sequentially. The articles include details such as the title, description, publication date, and an image.

### Key Features
- **Architecture**: Combines MVI and Clean Architecture.
- **Dependency Injection**: Managed using **Koin**.
- **UI Framework**: Built with **Jetpack Compose**.
- **Image Loading**: Uses **Fresco** (can be replaced with Coil or Glide).
- **Navigation**: Managed with **Navigation Component**.
- **Asynchronous Operations**: Powered by **Coroutines**.
- **Modularization**: Each feature is implemented as a separate module.

## Project Structure

The project is organized into multiple modules to promote separation of concerns and scalability:
- **app**: The main entry point of the application.
- **domain**: Contains business logic, use cases, and entities.
- **data**: Manages data sources and repositories.
- **common**: Shared utilities and components.
- **featureCompanyNews**: Displays news for specific companies.

## Architecture Details

### Model-View-Intent (MVI)

The **Model-View-Intent (MVI)** architecture organizes user interactions into a unidirectional data flow. Here's how it works:

#### Components and Data Flow
1. **User Interface (UI)**:
    - The user interacts with the UI, generating Intents.
    - Intents initiate the application's processes.

2. **Intents**:
    - Represent user intentions (e.g., fetching news articles).
    - Processed by the **Intent Interpreter** to create Actions.

3. **Intent Interpreter**:
    - Converts Intents into Actions for the system to process.

4. **Actions**:
    - Define system-level operations (e.g., API calls).
    - Handled by the **Processor**.

5. **Processor**:
    - Executes Actions and interacts with repositories.
    - Returns a **Result** (e.g., fetched data or an error).

6. **Result**:
    - Contains the output of the Processor.
    - Passed to the **Reducer**.

7. **Reducer**:
    - Produces a new **State** based on the Result.

8. **State**:
    - Describes the application's current condition.
    - Sent to the UI for rendering.

9. **Render**:
    - Updates the UI based on the current State.

#### Advantages of MVI
- **Unidirectional Data Flow**: Simplifies debugging and ensures predictability.
- **Decoupled Layers**: Promotes maintainability.
- **Error Safety**: Reduced risk of errors due to clear separation of concerns.
- **High Testability**: Intents, States, and Reducers are easily testable.

### Clean Architecture

The **Clean Architecture** divides the application into layers to ensure scalability and maintainability.

#### Layers

1. **Presentation Layer**:
    - **UI**: Displays information and captures user interactions.
    - **Presenter**: Mediates between the UI and Domain layer.

2. **Domain Layer**:
    - **Use Cases**: Encapsulate business logic.
    - **Entities**: Core business models shared across use cases.

3. **Data Layer**:
    - **Repositories**: Abstract data sources for the domain layer.
    - **Web/DB**: Handle actual data retrieval and storage.

#### Principles
- **Dependency Rule**: Dependencies point inward to ensure the core logic is independent of external frameworks.
- **Separation of Concerns**: Each layer has a distinct responsibility.
- **Testability**: Decoupled layers enable independent testing.
- **Flexibility**: Swapping data sources is straightforward.

## Setup and Configuration

### Prerequisites
1. Obtain an API key from [NewsAPI.org](https://newsapi.org/) and add it to the `build.gradle` file under `buildConfigField`:
   ```groovy
   buildConfigField "String", "API_KEY", '"YOUR_API_KEY"'
   ```

2. Ensure all dependencies are declared in the `buildSrc` module for centralized management.

### Adding Features
To add a new feature:
1. Create a new module.
2. Follow the structure of the `featureCompanyNews` module.

### Image Loading
The project currently uses **Fresco** for image loading. You can replace it with **Coil** or **Glide** if preferred.

## Screenshots

### MVI Architecture Diagram
![MVI Architecture](https://raw.githubusercontent.com/sajjadshahbazi/MVI-Clean-Architecture/main/resources/mvi_architecture.png)

### Clean Architecture Diagram
![Clean Architecture](https://raw.githubusercontent.com/sajjadshahbazi/MVI-Clean-Architecture/main/resources/clean_architecture.png)

## License
This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

---

For more details, visit the [GitHub Repository](https://github.com/sajjadshahbazi/MVI-Clean-Architecture/tree/Feature/ShowRecentNews).

