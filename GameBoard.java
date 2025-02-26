import javafx.scene.layout.StackPane;

/**
 * Write a description of class GameBoard here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class GameBoard extends StackPane
{
    public GameBoard(StackPane layout)
    {
        // Initialize the game screen with relevant UI elements
        TileBoard player1Tiles = new TileBoard(true, 100, 0);
        TileBoard player2Tiles = new TileBoard(false, 100, 100);
        
        // Add any other game-related UI components (e.g., buttons, game board) here.
        layout.getChildren().addAll(player1Tiles, player2Tiles);
    }
}
