# Train Station Manager

This project is a Train Station Manager application that manages train stations, their connections, and shortest routes between them. It initializes stations and their connections from a file, calculates shortest routes between stations, and provides methods to retrieve travel times, trace routes, and get departure times for stations.



## Getting Started

### Prerequisites

- Java Development Kit (JDK) 8 or higher
- An IDE such as Eclipse or IntelliJ IDEA

### Installation

1. Clone the repository:
    ```sh
    git clone https://github.com/your-username/train-station-manager.git
    ```
2. Open the project in your preferred IDE.

### Running the Application

1. Ensure that the `stations.csv` file is placed in the `inputFiles` directory.
2. Run the `main/StationGUI.java` file to start the graphical user interface for displaying train station schedules.

### Running Tests

1. Open the `testers/StudentTester.java` file.
2. Run the tests using your IDE's built-in test runner.

## Project Components

### Main Classes

- **TrainStationManager**: Manages train stations, their connections, and shortest routes between them.
- **Station**: Represents a train station with its name and distance from another station.
- **StationGUI**: Creates a graphical user interface for displaying train station schedules.

### Data Structures

- **ArrayListStack**: A stack implementation using an `ArrayList`.
- **CircularArrayQueue**: A queue implementation using a circular array.
- **LinkedListStack**: A stack implementation using a linked list.
- **ListQueue**: A queue implementation using a list.

### Interfaces

- **Queue**: Defines the operations for a queue data structure.

### Testers

- **StudentTester**: Contains unit tests for the application.

## Authors

- Julio E. Camacho Alicea
