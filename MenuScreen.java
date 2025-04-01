import javafx.animation.TranslateTransition;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.media.MediaView;

/****************************************************************
* Main Menu 
*
* Christian Dennis
* 26/02/2025
* 0.0.1
******************************************************************/

public class MenuScreen extends StackPane
{
    // Constructor to initialize the menu and its components (buttons, title, video background, etc.)
    public MenuScreen(Stage stage)
    {
        // Initialize the button components
        Button playButton = new PlayButton();  // Button created using PlayButton class
        Button wipButton = new WIPButton();    // Button created using WIPButton class
        Button creditsButton = new CreditsButton();  // Button created using CreditsButton class

        // Set a fixed width and height for all buttons to make them uniform in size
        double buttonWidth = 200; // Example fixed width
        double buttonHeight = 50; // Example fixed height
        playButton.setMinWidth(buttonWidth);
        wipButton.setMinWidth(buttonWidth);
        creditsButton.setMinWidth(buttonWidth);
        playButton.setMinHeight(buttonHeight);
        wipButton.setMinHeight(buttonHeight);
        creditsButton.setMinHeight(buttonHeight);

        // Use MediaHandler to load the title image from the resources folder with a specified width and height
        ImageView gameTitleImage = MediaHandler.loadImage("STD_Title.png", 800, 600); 

        // Create the animation for the title (hover effect)
        TranslateTransition titleAnimation = new TranslateTransition(Duration.seconds(4), gameTitleImage);
        titleAnimation.setByY(-200);  // Moves the title up by 20px
        titleAnimation.setCycleCount(TranslateTransition.INDEFINITE);  // Repeats the animation
        titleAnimation.setAutoReverse(true);  // Makes the title bounce back down after moving up
        titleAnimation.play();

        // Create a container for the buttons (VBox for vertical arrangement)
        VBox buttons = new VBox(40);  // 10px gap between buttons
        buttons.getChildren().addAll(playButton, wipButton, creditsButton);
        buttons.setAlignment(Pos.CENTER);  // Center buttons horizontally

        // Adjust the position of the buttons lower on the screen
        buttons.setTranslateY(150);  // This moves the buttons down (increase the value for more space)

        // Add the game title and buttons to the layout
        MediaView mediaView = MediaHandler.playBackgroundVideo(stage, "TitleScreenVideo.mp4");  // Play video
        this.getChildren().add(mediaView);  // Add the video as the first element in the stack pane
        
        // Move the title image and position it correctly
        this.getChildren().add(gameTitleImage);  // Add the game title image on top of the video
        this.getChildren().add(buttons);  // Add the buttons on top of everything else

        // Center the title image and buttons properly
        gameTitleImage.setTranslateY(-250);  // Position the title a bit higher
        buttons.setTranslateY(150);  // Keep buttons below the title
    }

    // Method to add this menu to the scene
    public void show(Stage stage) 
    {
        stage.setScene(new Scene(this, 1200, 800));
        stage.show();
    }

    // Method to remove this menu from the stage
    public void hide(Stage stage) 
    {
        stage.setScene(null);  // Removes the current scene
    }
}
