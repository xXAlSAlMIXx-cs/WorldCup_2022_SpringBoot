# WorldCup_2022_SpringBoot

A Spring Boot application designed to monitor the 2022 World Cup teams, allowing users to add, delete, modify, and read team information.

## Key Features & Benefits

*   **CRUD Operations:** Comprehensive Create, Read, Update, and Delete operations for World Cup teams.
*   **RESTful API:** Utilizes REST controller concepts for seamless data management.
*   **Dynamic Web Interface:** Provides an interactive web interface for managing teams using Thymeleaf.
*   **Flag Integration:** Automatically downloads and integrates country flags.

## Technologies

*   **Java:** The core programming language for the backend.
*   **Spring Boot:** Framework for building the application.
*   **Thymeleaf:** Template engine for dynamic web pages.
*   **Maven:** Dependency management and build automation.
*   **HTML/CSS/JavaScript:** For the frontend user interface.
*   **Python:** For downloading the flags using `download_flags.py`.
*   **Bootstrap:** Used for styling HTML pages for consistency and responsiveness.
*   **Font Awesome:** Used for adding icons to HTML pages.

## Prerequisites & Dependencies

Before running this application, ensure you have the following installed:

*   **Java Development Kit (JDK):** Version 8 or higher.
*   **Maven:** Build automation tool.
*   **Python:** For running flag download script.
*   **An IDE (Integrated Development Environment):** such as IntelliJ IDEA or Eclipse

## Installation & Setup Instructions

1.  **Clone the Repository:**

    ```bash
    git clone https://github.com/xXAlSAlMIXx-cs/WorldCup_2022_SpringBoot.git
    cd WorldCup_2022_SpringBoot
    ```

2.  **Download Country Flags:**

    Run the `download_flags.py` script using Python to download flag images for all the teams. This script will create a directory named `flags` inside the `src/main/resources/static/images/` directory.

    ```bash
    python download_flags.py
    ```

    Make sure to install the `requests` library if it's not already installed:

    ```bash
    pip install requests
    ```

3.  **Build the Project:**

    Use Maven to build the project and resolve dependencies:

    ```bash
    ./mvnw clean install
    ```

4.  **Run the Application:**

    Run the Spring Boot application:

    ```bash
    ./mvnw spring-boot:run
    ```

    Alternatively, you can run the `Hw2S133334Application.java` directly from your IDE.

    The application will be accessible at `http://localhost:8080`.

## Usage Examples & API Documentation

The application provides a web interface for managing World Cup teams. Here are the main functionalities:

*   **Add Team:** Navigate to `/AddTeam` to add a new team.

*   **Delete Team:** Navigate to `/DeleteTeam` to delete a team.

*   **View Teams:** The main page displays the list of all teams. You may need to implement the view.

The core backend logic is handled by the REST controllers in the `Controller` package.  The functionality is primarily driven by the UI interaction rather than a documented API.

## Project Structure

```
WorldCup_2022_SpringBoot/
├── .gitattributes
├── .gitignore
└── .mvn/
└── wrapper/
├── maven-wrapper.properties
├── download_flags.py
├── mvnw
├── mvnw.cmd
├── pom.xml
└── src/
└── main/
└── java/
└── com/
└── tryspringboot/
└── hw2_s133334/
└── Controller/
├── CalculaterContorller.java
├── NavigationController.java
├── DatabaseAccess.java
├── Hw2S133334Application.java
└── resources/
    ├── static/
    │   ├── css/
    │   │   └── styles.css
    │   ├── js/
    │   │   └── main.js
    │   └── images/
    │       └── flags/ # Flags will be downloaded here
    └── templates/
        ├── AddTeam.html
        └── DeleteTeam.html
```

## Important Files

*   **`download_flags.py`:** Downloads country flag images from the internet.

    ```python
    import requests
    import os

    # ALL 32 TEAMS FROM FIFA WORLD CUP 2022 QATAR
    teams = [
        ("Argentina", "ar"),
        ("Australia", "au"),
        ("Belgium", "be"),
        ("Brazil", "br"),
        ("Cameroon", "cm"),
        ("Canada", "ca"),
        ("Costa Rica", "cr"),
        ("Croatia", "hr"),
        ("Denmark", "dk"),
        ("Ecuador", "ec"),
        ("England", "gb-eng"),   # England uses 'gb-eng'
        ("France", "fr"),
        ("Germany", "de"),
        ("Ghana", "gh"),
        ("Iran", "ir"),
        ("Japan", "jp"),
        ("Mexico", "mx"),...
    ```

*   **`src/main/resources/static/css/styles.css`:** CSS styles for the web interface.

    ```css
    :root {
        --primary-gradient: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
        --secondary-gradient: linear-gradient(135deg, #f093fb 0%, #f5576c 100%);
        --dark-bg: #1a1a2e;
        --darker-bg: #0f0f1e;
    }

    body {
        font-family: 'Inter', -apple-system, BlinkMacSystemFont, 'Segoe UI', sans-serif;
        min-height: 100vh;
        display: flex;
        flex-direction: column;
        background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
        position: relative;
        overflow-x: hidden;
    }

    body::b...
    ```

*   **`src/main/resources/static/js/main.js`:** JavaScript for adding dynamic behavior to the web interface.

    ```javascript
    window.addEventListener('scroll', function() {
        const navbar = document.getElementById('mainNavbar');
        if (window.scrollY > 50) {
            navbar.classList.add('scrolled');
        } else {
            navbar.classList.remove('scrolled');
        }
    });
    ```

*   **`src/main/resources/templates/AddTeam.html`:** Thymeleaf template for the "Add Team" page.

    ```html
    <!DOCTYPE html>
    <html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Add Team</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
        <link rel="stylesheet" th:href="@{/css/styles.css}">
    </head>
    <body>
    <nav class="navbar navbar-expand-lg" id="m...
    ```

*   **`src/main/resources/templates/DeleteTeam.html`:** Thymeleaf template for the "Delete Team" page.

    ```html
    <!DOCTYPE html>
    <html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Delete Team</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
        <link rel="stylesheet" th:href="@{/css/styles.css}">
    </head>
    <body>
    <nav class="navbar navbar-expand-lg" id...
    ```

## Configuration Options

The application configuration can be modified through the `application.properties` or `application.yml` file in the `src/main/resources` directory. Common configurations include:

*   **Server Port:** Configure the port on which the application runs.
    ```properties
    server.port=8080
    ```

*   **Database Configuration:** If you integrate a database, configure the connection details here.

## Contributing Guidelines

Contributions are welcome! To contribute to this project:

1.  Fork the repository.
2.  Create a new branch for your feature or bug fix.
3.  Make your changes and commit them with descriptive messages.
4.  Submit a pull request to the main branch.

## License Information

License is not specified. All rights reserved by the owner, xXAlSAlMIXx-cs.

## Acknowledgments

*   This project utilizes the Spring Boot framework, Thymeleaf template engine, and other open-source libraries.
*   Flag icons are sourced from external websites.