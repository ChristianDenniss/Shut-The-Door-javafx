import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.StackPane;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

public class SkipButton extends MenuButton
{
    double x,y;
    Instructions instruction;
    
    public SkipButton(double x, double y, Instructions instruction)
    {
        // Set the button text to "Skip" with our parent class method
        super("Skip");
        this.x = x;
        this.y = y;
        this.instruction = instruction;
        // Set the action handler for when the button is clicked
        setOnAction(createAction()); 
        
        // Set the x and y position of the button using its params
        setTranslateX(x); 
        setTranslateY(y);
    }

    @Override
    public EventHandler<ActionEvent> createAction()
    {
        return event -> {
            System.out.println("Skipping the instructions...");

            // Get the current stage
            Stage stage = (Stage) getScene().getWindow();

            // Get the current layout (StackPane) from the scene
            StackPane layout = (StackPane) getScene().getRoot();

            // Clear the layout before adding anything new (this is the first clear)
            layout.getChildren().clear();

            // Play the background video using MediaHandler
            String videoTitle = "transVideo.mp4"; // Replace with your actual video filename
            MediaView mediaView = MediaHandler.playBackgroundVideo(stage, videoTitle);

            if (mediaView != null)
            {
                // Add the video to the layout
                layout.getChildren().add(mediaView);

                // Add an event listener to the MediaPlayer to know when the video ends
                mediaView.getMediaPlayer().setOnEndOfMedia(() -> {
                    // Once the video is done, start the game screen
                    instruction.startGame(layout);
                });
            }
        };
    }

    /** DEPRECATED METHOD (left it in to show progression); we used to have 2 
     * startGame methods 1 here and 1 in instructions class but we revised the
     * code and decided to pass it as a param so we can just reuse the method 
     * for better structure & maintainability 
     */
    private void startGame(StackPane layout)
    {
        /*
        GameScreen gameScreen = new GameScreen(layout); // Pass the layout to GameScreen

        
        layout.getChildren().add(gameScreen);  // Add the game screen to the layout */
    }
}
