Project Overview
This is a native Android application built to showcase modern Android development practices. 
The app fetches and displays public GitHub repositories while demonstrating a clean, scalable architecture and maintainable code structure.


* Clean Architecture with clear separation of responsibilities
* Modular project structure
* Domain-driven design principles
* Custom design system for consistent UI
* Unit testing for core business logic

Project Structure
The project is split into multiple modules, each with a clear role:
`:app` — Presentation Layer
This module contains everything related to the user interface and user interaction:
* Composable screens and UI logic
* ViewModels for state management
* Navigation setup
* Dependency injection configuration

`:domain` — Business Logic
A pure Kotlin module that holds the core application logic:
* Use cases that represent business actions
* Domain models
* Repository interfaces
* Shared domain types
  Keeping this layer independent from Android makes it easier to reuse, test and maintain.

`:data` — Data Layer
Responsible for handling data sources and implementation details:
* Repository implementations
* Network API integration
* DTO models and mappers
* Remote and mock data sources
* Local preference storage

`:designsystem` — UI Component Library
A reusable set of UI components and styling rules:
* Custom composable widgets
* Theme configuration (colors, typography, spacing)
* Consistent Material3 styling across the app

Architecture
The project follows Clean Architecture principles with three main layers:
* Presentation – UI and interaction logic
* Domain – core business rules and use cases
* Data – networking, persistence, and external data handling
  Within the presentation layer, the app uses the MVI pattern:
   -View (Compose UI) displays state
   -ViewModel manages state and interactions
   -Use cases handle business logic
Data flows in a single direction, helping keep the code predictable and easy to reason about.

Design Approach
The project applies domain-driven design ideas to keep the business logic organized and independent of frameworks. 
Each use case has a single responsibility, repositories abstract data access, and domain models represent core business entities.

Testing
Unit tests focus mainly on the domain layer to ensure business logic behaves correctly. 
Mocked dependencies allow testing use cases independently and help prevent regressions.

Features
* Browse public GitHub repositories
* View repository details
* Material3 UI with light and dark theme support
* Mock and remote data sources
* Loading, empty, and error states