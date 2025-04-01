import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.control.Button;

/****************************************************************
* A backbutton to reset our layout and bring us back to a main menu
*
* Christian Dennis
* 26/02/2025
* 0.0.1
******************************************************************/

public class BackButton extends MenuButton 
{
    private Stage stage;  // Store reference to the stage

    // Constructor accepting position parameters for translateX and translateY
    public BackButton(Stage stage, double translateX, double translateY) 
    {
        super("Back");
        this.stage = stage; // Store the stage reference

        // Set the size of the button to match the size of the menu buttons (width: 200px, height: 50px)
        double buttonWidth = 200;
        double buttonHeight = 50;
        setMinWidth(buttonWidth);
        setMinHeight(buttonHeight);

        // Set the position of the button using the provided parameters
        setTranslateX(translateX); // Position the button horizontally
        setTranslateY(translateY); // Position the button vertically

        // Handle the action of the button
        setOnAction(createAction());
    }

    @Override
    protected EventHandler<ActionEvent> createAction() 
    {
        // Custom action for the Back button
        return new EventHandler<ActionEvent>() 
        {
            @Override
            public void handle(ActionEvent event) 
            {
                System.out.println("Back button clicked! Returning to the menu...");
                
                // Play the button press sound using MediaHandler
                MediaHandler.playSound("mouseClick.mp3");
                
                // Get the current layout (StackPane) from the scene
                StackPane layout = (StackPane) stage.getScene().getRoot();

                // Clear the current content of the layout
                layout.getChildren().clear();

                // Create a new MenuScreen (which is a StackPane)
                MenuScreen menuScreen = new MenuScreen(stage);

                // Add the MenuScreen to the layout
                layout.getChildren().add(menuScreen);

                // Optionally, set the scene to the stage again (if necessary)
                stage.setScene(stage.getScene()); // Refresh the stage with the same scene
                stage.show();
            }
        };
    }
}
