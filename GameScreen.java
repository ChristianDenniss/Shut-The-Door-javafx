import javafx.scene.layout.StackPane;
import javafx.animation.RotateTransition;
import javafx.util.Duration;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.geometry.Pos;
import javafx.animation.Timeline;
import javafx.animation.KeyFrame;
import javafx.animation.TranslateTransition;
import javafx.scene.paint.Color;
import javafx.scene.control.TextFormatter;
import javafx.util.Callback;

public class GameScreen extends StackPane
{
    private Text player1NameText;
    private Text player2NameText;
    private Player p1, p2;
    private Dice die1;
    
    //control the user access to Ui clicks
    public static boolean gamePlayable = false;
    public static int value1;
    public static int value2;
    public static boolean diceRollable = false;

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
        
        TileBoard player1Tiles = new TileBoard(false, 290, 320);
        TileBoard player2Tiles = new TileBoard(true, 290, 420);
        this.die1 = new Dice(650, 500);

        
        this.getChildren().addAll(die1, player1Tiles, player2Tiles);
        
        // Debugging: Log to confirm GameBoard was added
        System.out.println("GameBoard added to layout");// Start the player input sequence
        
        slideTextAcrossScreen(true, false, p1Text.getText(), p2Text.getText(), layout, gameBoard, player1Tiles, player2Tiles);
        
    }
    
    public void changePlayersTurn(GameBoard gb, TileBoard p1, TileBoard p2, String p1Text, String p2Text, StackPane layout)
    {
        disableGameplay();
        p1.changePlayersTurn();
        p2.changePlayersTurn();
        gb.rotateGameBoard();
        slideTextAcrossScreen(false, true, p1Text, p2Text ,layout, gb, p1, p2);
    }
    
    public void allowGameplay()
    {
        GameScreen.diceRollable = true;
        GameScreen.gamePlayable = true;
    }
    
    public void disableGameplay()
    {
        GameScreen.diceRollable = false;
        GameScreen.gamePlayable = false;
    }
    
    /**
     * Method to create a large text and slide it across the screen.
     *
     * @param p1Text The text to be displayed and moved across the screen.
     * @param pane   The pane to add the text to (could be the root pane of your scene).
     */
    public void slideTextAcrossScreen(boolean player1, boolean turn, String p1Text, String p2Text, StackPane layout, GameBoard gb, TileBoard p1, TileBoard p2) 
    {   
    
        // Create a new Text object with the value of p1Text minus the player pre fix
        //sub string he p1 text string to remove first 9 letters
        String modifiedString = p1Text.substring(9);
        
        if(player1 == false)
        {
            modifiedString = p1Text.substring(9);
        }
        
        Text text = new Text(modifiedString + " total score: " + "");
        
        if (turn) 
        {
            modifiedString = p1Text.substring(9);
            text.setText(modifiedString + " Turn");
        }

        
        // Set the style to make the text large and visually appealing
        text.setStyle("-fx-font-size: 60px; -fx-font-weight: bold;");
        // Set stroke (border) colour for our text then set stroke width (border thickness)
        text.setStroke(Color.BLUE);  
        text.setStrokeWidth(2);  
        
        // Add the text to the pane before calculating its width
        layout.getChildren().add(text);

        // Set the initial position of the text (starting off the left side of the screen)
        text.setTranslateY(100); // Adjust Y position for vertical placement
        text.setTranslateX(-600); // Adjust X position for vertical placement

        // Calculate the text's width after adding it to the pane
        double textWidth = text.getBoundsInLocal().getWidth();
    
        // Create a TranslateTransition to move the text across the screen
        TranslateTransition transition = new TranslateTransition();
        transition.setNode(text);
        
        // Duration of the slides
        //total score is slower because its more importnt info
        if(turn)
        {
            transition.setDuration(Duration.seconds(6));  
        }
        else
        {
            transition.setDuration(Duration.seconds(7));  
        }
        
         transition.setFromX(-600);
        transition.setToX(layout.getWidth());  // End position (offscreen right)
        
        // Set up the transition to run, and when it's done, remove the text from the pane
        transition.setOnFinished(event -> 
        {
            layout.getChildren().remove(text);  // Remove the text after the transition ends
            if(turn == true)
            {
                allowGameplay(); 
            } 
            else
            {
                changePlayersTurn(gb, p1, p2, p1Text, p2Text, layout);
            }
        });

        // Start the transition
        transition.play();
    }
}
