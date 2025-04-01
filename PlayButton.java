import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.scene.layout.StackPane;

/****************************************************************
* Class to create the play button which will allow us to transistion to instructions section
*
* Christian Dennis
* 26/02/2025
* 0.0.1
******************************************************************/

public class PlayButton extends MenuButton
{
    public PlayButton()
    {
        super("Play");  
        setOnAction(createAction());  
    }

    @Override
    public EventHandler<ActionEvent> createAction() 
    {
        return new EventHandler<ActionEvent>() 
        {
            @Override
            public void handle(ActionEvent event) 
            {
                System.out.println("Play button clicked! Starting the game...");

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

                    // Create an instance of Instructions (passing layout)
                    Instructions instructions = new Instructions(layout);

                    // Optionally: Once the instructions are shown, you can add a "Start" button or something to begin the game
                    // For example, you can add a listener for the last instruction to start the game
                }
            }
        };
    }
}
