# EcosystemApp

EcosystemApp is a Java application using JavaFX, serving to simulate a world of organisms in a 2D environment. The project is educational and allows practicing object-oriented programming, concurrency, and creating a GUI in JavaFX.

## Features

- Creating a dynamic 2D world with organisms.
- Every organism has its own life cycle and can perform actions such as moving.
- Automatic board refreshing using Timeline in JavaFX.
- Different types of organisms may have their own appearance (avatar) and field color.
- Simulation of interactions between organisms and the board, as well as between the organisms themselves.

## Tech stack

- Java 17+
- JavaFX
- Gradle
- UUID for unique identification of organisms

## Project structure and class descriptions

`World`

- Represents the 2D world.
- Stores the board array with organism identifiers (UUID).
- Manages the list of organisms.
- Responsible for organism movement and their interactions.

`Organism`

- Abstract base class for all organisms.
- Contains fields x, y, and UUID id.
- The updateLocation method updates the position of the organism in the world.
- Each organism can be movable (isMoveable) and react to the environment.

`HelloController`

- JavaFX controller.
- Manages the board view (GridPane) and refreshes it each turn.
- Responsible for initializing the world and organisms.
- Starts the Timeline to automatically refresh the board at a specified interval.

The board is displayed as a grid (GridPane), and each organism is represented by a Label with an avatar and color.
