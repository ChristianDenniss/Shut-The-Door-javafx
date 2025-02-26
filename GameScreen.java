import javafx.scene.layout.StackPane;
import javafx.scene.control.Label;

/**
 * GameScreen class represents the game screen that will be displayed once the instructions are skipped.
 */
public class GameScreen extends StackPane
{
    public GameScreen(StackPane layout)
    {
        // Initialize the game screen with relevant UI elements
        GameBoard gameBoard = new GameBoard(layout);
        
        
        // Add all gameplay related content here for strong encapsulation and replayability
        layout.getChildren().add(gameBoard);
    }
}
