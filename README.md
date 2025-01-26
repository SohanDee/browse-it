
# Browse-It

**Browse-It** is a simple browser application built using JavaFX. The browser is capable of loading web pages and is designed as a learning project to understand the following concepts:

- Client-server architecture
- HTTP request-response cycles
- Theories of sockets and network communication

## Features
- Loads web pages via URL input.
- Displays basic web content using JavaFX WebView.
- Handles HTTP protocol.

## Purpose
The primary purpose of this project is educational:
- To deepen understanding of how browsers communicate with servers.
- To explore the concepts of HTTP request-response mechanisms.
- To study the basics of sockets and how data is transferred over the network.

## Technologies Used
- **JavaFX**: For the graphical user interface and WebView.
- **Java**: As the programming language for the application logic.

## Setup Instructions

1. Clone the repository:
   ```bash
   git clone https://github.com/yourusername/browse-it.git  
   cd browse-it  
   ```  

2. Open the project in your favorite IDE (e.g., IntelliJ IDEA, Eclipse, or VS Code).

3. Set up JavaFX:
    - Ensure you have JavaFX properly configured in your environment. Follow the [JavaFX Setup Guide](https://openjfx.io/openjfx-docs/) for detailed steps.
    - Add the JavaFX library to your project dependencies.

4. Run the application:
    - Locate and run the `AppInitializer` class as a Java application.

## Usage
1. Launch the application.
2. Enter the URL of the web page you want to load in the provided text field.
3. Press **Enter** to fetch and display the content.

### Example
```plaintext  
Input URL: https://www.example.com  

Output: The browser displays the content of the website in the WebView.  
```  

## Project Structure
- **`src/main/java/`**: Contains the Java source files.
    - **`AppInitializer.java`**: The main entry point of the application, responsible for initializing the JavaFX application.
    - **`controller/`**: Contains controllers for handling the UI logic.
        - **`MainSceneController.java`**: Implements the core logic for loading and rendering web pages.
- **`src/main/resources/`**: Contains resource files used in the application.
    - **`fxml/`**: Contains FXML files for the application's UI layout.
        - **`MainScene.fxml`**: Defines the main scene layout for the browser.
    - **`images/`**: Contains images or icons for the browser (if applicable).

## Future Improvements
- Add support for browser navigation (Back/Forward).
- Implement custom error pages (e.g., 404 or connection errors).
- Add basic debugging tools to view headers and response details.
- Introduce support for JavaScript and CSS rendering in WebView.
- Improve the UI with a modern, user-friendly design.

## License
Copyright &copy; 2025 Snap-Access. All Rights Reserved.  
This project is licensed under the MIT License. See the [LICENSE](LICENSE.txt) file for details.

## Acknowledgments
- [JavaFX Documentation](https://openjfx.io/)
- Inspiration for this project came from exploring network communication and web technologies.

---  

Feel free to fork, modify, or use **Browse-It** as a learning resource! 😊  
