import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.StackPane;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

public class SkipButton extends MenuButton
{
    public SkipButton(double x, double y)
    {
        // Set the button text to "Skip" with our parent class method
        super("Skip");
        
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
                    startGame(layout);
                });
            }
        };
    }

    /**
     * This method is called after the video ends, and it will transition to the game screen.
     */
    private void startGame(StackPane layout)
    {
        // Create a GameScreen instance and pass the layout to it
        GameScreen gameScreen = new GameScreen(layout); // Pass the layout to GameScreen

        // Add the game screen to the layout (no need to clear the layout again)
        layout.getChildren().add(gameScreen);  // Add the game screen to the layout
    }
}
