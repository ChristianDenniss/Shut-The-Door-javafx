import java.util.Random;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.text.Text;
import javafx.util.Duration;

/****************************************************************
* Class to create and animate the dice roll logic.
*
* Christian Dennis
* 26/02/2025
* 0.0.1
******************************************************************/
public class Dice 
{
    private Random random;

    // Constructor to initialize Dice object with Text node
    public Dice(Text diceText) 
    {
        this.random = new Random();
        
    }

    
    public void rollDice(Timeline timeline) 
    {
        
    }
}
