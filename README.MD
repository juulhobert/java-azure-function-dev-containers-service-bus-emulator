# Experiment with Devcontainers, Azure Functions, Java, and Service Bus Emulator

This project is an experiment to integrate Devcontainers, Azure Functions, Java, and the Azure Service Bus Emulator.

## Description

The project aims to demonstrate the use of Devcontainers for a Java application that interacts with Azure Functions and
the Azure Service Bus Emulator. However, there is an issue where Java is attempting to connect to port 5671 instead of
the expected port 5672.

## Prerequisites

- Docker
- IntelliJ IDEA
- Maven

## Setup

1. Clone the repository.
2. Open the project in IntelliJ IDEA.
3. Reopen the project in the Dev Container.

## Running the Application

1. Build the project:
```bash
mvn package
mvn azure-functions:run
```