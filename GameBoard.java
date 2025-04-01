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
    private ImageView boardBackGround;
    
    public GameBoard(Text p1, Text p2) 
    {
        this.p1 = p1;
        this.p2 = p2;
        
        // Initialize the game screen with relevant UI elements
        //TileBoard player1Tiles = new TileBoard(true, 310, -50);
        //TileBoard player2Tiles = new TileBoard(false, 310, 50);
        this.boardBackGround = MediaHandler.loadImage("boardBackGround.png", 1100, 750);
        
        // Add the components to the GameBoard layout (StackPane)
        this.getChildren().addAll(boardBackGround);//, player1Tiles, player2Tiles);
        
        //must come after board to not be over lapped, fixed mistake
        this.getChildren().addAll(p1, p2);  
        
        boardBackGround.setMouseTransparent(true);
        p1.setMouseTransparent(true);
        p2.setMouseTransparent(true);
        
        System.out.println("Usernames, board image, tileBoards added to screen");
    }

}
