import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;

/****************************************************************
* Our main driver class to start our app
*
* Christian Dennis
* 26/02/2025
* 0.0.1
******************************************************************/

public class Driver extends Application
{
    // The main entry point for launching the JavaFX application
    @Override
    public void start(Stage stage)
    {
        // Set up the MenuScreen (using the previously defined MenuScreen class)
        MenuScreen menuScreen = new MenuScreen(stage);

        // Set the title of the window
        stage.setTitle("Shut The Door");

        // Optionally, you can center the window on the screen
        stage.setX(400);  
        stage.setY(100);  

        // Show the menu screen
        menuScreen.show(stage);  // This will set the scene with the menu screen

        // You can hide the menu when needed:
        // menuScreen.hide(stage);  // This will remove the menu from the stage
    }
}
