import javafx.scene.layout.StackPane;
import javafx.animation.RotateTransition;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.scene.text.Font;

public class GameBoard extends StackPane
{
    private Text p1;
    private Text p2;
    
    public GameBoard(Text p1, Text p2) 
    {
        this.p1 = p1;
        this.p2 = p2;
        
        this.getChildren().addAll(p1, p2);
        
        // Initialize the game screen with relevant UI elements
        TileBoard player1Tiles = new TileBoard(true, 310, -50);
        TileBoard player2Tiles = new TileBoard(false, 310, 50);
        ImageView boardBackGround = MediaHandler.loadImage("boardBackGround.png", 1100, 750);
        
        // Add the components to the GameBoard layout (StackPane)
        this.getChildren().addAll(boardBackGround, player1Tiles, player2Tiles);
        
        boardBackGround.setMouseTransparent(true);
        p1.setMouseTransparent(true);
        p2.setMouseTransparent(true);
        
        System.out.println("Usernames, board image, tileBoards added to screen");
    }

    /**
     * Rotates the entire GameBoard and its children 180 degrees with a smooth animation.
     */
    public void rotateGameBoard()
    {
        // Debugging: Log when the rotation function is called
        System.out.println("rotateGameBoard method called");

        // Apply rotation to the GameBoard (this StackPane)
        RotateTransition rotateTransition = new RotateTransition(Duration.seconds(4), this);

        // Set the angle of rotation
        rotateTransition.setByAngle(180);  // Rotate by 180 degrees

        // Set the interpolation for smooth rotation
        rotateTransition.setInterpolator(javafx.animation.Interpolator.EASE_BOTH);

        // Set the rotation to only happen once
        rotateTransition.setCycleCount(1);  // One cycle of rotation

        // Debugging: Log before starting the transition
        System.out.println("Starting the GameBoard rotation transition...");

        // Play the rotation animation
        rotateTransition.play();

        // Log when the rotation is completed
        rotateTransition.setOnFinished(event -> {
            System.out.println("Rotation completed for GameBoard.");
        });
    }
}
