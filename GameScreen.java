import javafx.scene.layout.StackPane;
import javafx.animation.RotateTransition;
import javafx.util.Duration;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.geometry.Pos;
import javafx.animation.Timeline;
import javafx.animation.KeyFrame;
import javafx.scene.control.TextFormatter;
import javafx.util.Callback;

public class GameScreen extends StackPane
{
    private Text player1NameText;
    private Text player2NameText;

    /**
     * Constructor for GameScreen.
     * @param layout The StackPane layout that will hold the game screen's UI components.
     */
    public GameScreen(StackPane layout, Player player1, Player player2, Text p1Text, Text p2Text) 
    {
        // Debugging: Log to confirm GameScreen constructor is called
        System.out.println("GameScreen constructor called");

        // Create a new GameBoard instance
        GameBoard gameBoard = new GameBoard(p1Text, p2Text);  

        
        layout.getChildren().add(gameBoard);
        // Debugging: Log to confirm GameBoard was added
        System.out.println("GameBoard added to layout");// Start the player input sequence
    
        rotateGameScreen(gameBoard);
    }

    
     private void rotateGameScreen(GameBoard gameBoard)
    {
        // Debugging: Log when the rotation function is called
        System.out.println("rotateGameScreen method called");
        
        // Apply rotate to game board
        gameBoard.rotateGameBoard();

        // Apply the rotation to the entire GameScreen (this StackPane)
        RotateTransition rotateTransition = new RotateTransition(Duration.seconds(3), this);

        // Set the angle of rotation
        rotateTransition.setByAngle(180);  // Rotate by 180 degrees

        // Set the interpolation for smooth rotation
        rotateTransition.setInterpolator(javafx.animation.Interpolator.EASE_BOTH);

        // Set the rotation to only happen once
        rotateTransition.setCycleCount(1);  // One cycle of rotation

        // Debugging: Log before starting the transition
        System.out.println("Starting the rotation transition...");

        // Play the rotation animation
        rotateTransition.play();

        // Log when the rotation is completed
        rotateTransition.setOnFinished(event -> {
            System.out.println("Rotation completed.");
        });
    }
    
}
