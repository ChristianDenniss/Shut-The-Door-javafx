import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;
import java.util.Random;

public class Dice extends StackPane
{
    private Random random;
    private int x, y;
    private ImageView diceImage;
    private Text diceText;
    private boolean rollTwo;

    // Constructor to initialize Dice object with position (x, y)
    public Dice(int x, int y)
    {
        this.random = new Random();
        this.x = x;
        this.y = y;
        this.rollTwo = true;

        // Load the initial dice image using MediaHandler static method
        diceImage = MediaHandler.loadImage("diceRoller.png", 100, 100);

        if (diceImage == null)
        {
            System.out.println("Error loading the dice image.");
        }

        // Set the initial position of the dice
        diceImage.setTranslateX(x);
        diceImage.setTranslateY(y);

        // Initialize the diceText and position it below the dice image
        diceText = new Text("Ready to Roll!");
        diceText.setTranslateX(x);
        diceText.setTranslateY(y + 70); // Position text below the dice
        diceText.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        getChildren().addAll(diceText, diceImage);

        // Add MouseEvent handler to the dice image to trigger dice roll on click
        diceImage.setOnMouseClicked(this::handleDiceClick);
    }

    // Handle dice click event
    private void handleDiceClick(MouseEvent event)
    {
        if(GameScreen.gamePlayable && GameScreen.diceRollable)
        {
            System.out.println("Die Clicked");
            
            // Start the dice flipping animation for 3 seconds
            Timeline timeline = new Timeline();
            timeline.setCycleCount(Timeline.INDEFINITE);  // Set to loop indefinitely
            KeyFrame keyFrame = new KeyFrame(Duration.millis(100), e ->
            {
                // Randomly change the dice face for the animation
                int randomRoll = random.nextInt(6) + 1;  // Random number between 1 and 6
                String imageFileName = "dice_" + randomRoll + ".png";  // e.g., "dice_1.png"
                diceImage = MediaHandler.loadImage(imageFileName, 100, 100);
        
                // Make sure the dice image is updated in the layout
                getChildren().remove(diceImage); // Remove the previous dice image
                getChildren().add(diceImage);    // Add the new dice image
        
                // Reposition the dice image
                diceImage.setTranslateX(x);
                diceImage.setTranslateY(y);
            });
        
            // Add the keyframe to the timeline and play it
            timeline.getKeyFrames().add(keyFrame);
            timeline.play();
        
            // After 3 seconds, stop the animation and show the final roll result
            Timeline stopAnimationTimeline = new Timeline();
            stopAnimationTimeline.getKeyFrames().add(
                new KeyFrame(Duration.seconds(3), e ->
                {
                    // Final roll result
                    int finalRoll = rollDice();
                    diceText.setText("Rolled: " + finalRoll);
        
                    // Set the final dice image
                    String finalImageFileName = "dice_" + finalRoll + ".png";  // Final dice image
                    diceImage = MediaHandler.loadImage(finalImageFileName, 100, 100);
        
                    // Make sure the final dice image is updated in the layout
                    getChildren().remove(diceImage); // Remove the previous dice image
                    getChildren().add(diceImage);    // Add the new dice image
        
                    // Reposition the dice image
                    diceImage.setTranslateX(x);
                    diceImage.setTranslateY(y);
        
                    // Stop the flipping animation
                    timeline.stop();
                })
            );
        
            // Play the stop animation timeline after 3 seconds
            stopAnimationTimeline.play();
        }
        else
        {
            System.out.println("Dice not rollable right now");
        }
    }


    // Getter for the dice image (to add it to the layout)
    public ImageView getDiceImage()
    {
        return diceImage;
    }

    // Getter for the dice text (to add it to the layout)
    public Text getDiceText()
    {
        return diceText;
    }

    // Method to roll the dice (random number between 1 and 6)
    public int rollDice()
    {
        return (int) (Math.random() * 6) + 1;  // Random number between 1 and 6
    }
}
