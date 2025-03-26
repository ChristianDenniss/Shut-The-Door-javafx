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
    private Player p1, p2;

    /**
     * Constructor for GameScreen.
     * @param layout The StackPane layout that will hold the game screen's UI components.
     */
    public GameScreen(StackPane layout, Player p1, Player p2, Text p1Text, Text p2Text) 
    {
        // Debugging: Log to confirm GameScreen constructor is called
        System.out.println("GameScreen constructor called");       
        
        this.p1 = p1;
        this.p2 = p2;
        
        // Create a new GameBoard instance
        GameBoard gameBoard = new GameBoard(p1Text, p2Text);  
        layout.getChildren().add(gameBoard);
        gameBoard.setMouseTransparent(false);
        
        TileBoard player1Tiles = new TileBoard(true, 290, 320);
        TileBoard player2Tiles = new TileBoard(false, 290, 420);
        
        this.getChildren().addAll(player1Tiles, player2Tiles);
        
        // Debugging: Log to confirm GameBoard was added
        System.out.println("GameBoard added to layout");// Start the player input sequence
        
        gameBoard.rotateGameBoard();
        
    }
    
}
