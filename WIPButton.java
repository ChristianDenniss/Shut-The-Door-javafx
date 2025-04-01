import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/****************************************************************
* 
*
* Christian Dennis
* 26/02/2025
* 0.0.1
******************************************************************/

public class WIPButton extends MenuButton 
{
    public WIPButton() 
    {
        // Set the button text to "WIP" with our parent class method
        super("Work In Progress");
        setOnAction(createAction());
    }

    @Override
    public EventHandler<ActionEvent> createAction() 
    {
        return new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("WIP button clicked! This feature is under construction...");
                
                // Play the button press sound using MediaHandler
                MediaHandler.playSound("mouseClick.mp3");
                
                // Get the current stage
                Stage stage = (Stage) getScene().getWindow();

                // Create a black background image to show a transition effect
                ImageView blackBackground = MediaHandler.loadImage("black.png", stage.getWidth(), stage.getHeight());

                if (blackBackground != null) 
                {
                    // Get the current layout (StackPane) from the scene
                    StackPane layout = (StackPane) getScene().getRoot();

                    // Clear the current content of the layout
                    layout.getChildren().clear();

                    // Add the black background to the layout to create a transition effect
                    layout.getChildren().add(blackBackground);

                    // Create an instance of the BackButton
                    BackButton backButton = new BackButton(stage, -450, -350); // Pass the stage to the BackButton

                    // Add the Back button to the layout (positioning handled in BackButton itself)
                    layout.getChildren().add(backButton);

                    
                }
            }
        };
    }
}
