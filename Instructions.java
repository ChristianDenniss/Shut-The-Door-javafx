import javafx.animation.TranslateTransition;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.util.Duration;

/**
 * The Instructions class manages a sequence of instructions that can be shown to a user.
 * Each time the screen is clicked, the next instruction is shown with a cooldown between clicks.
 * The user can also skip the instructions using the Skip button.
 */
public class Instructions
{
    private String[] instructions = {
        "Welcome to our group's game; Shut The Door\n\nThis is a short set of instructions/information to guide you through your playthrough\n\n                    (Click to go to the next slide)",
        "Our version of Shut The Door is a 2-player number game combining strategy, probability, and luck.",
        "The user will enter the preferred usernames of each player to label the board sides.",
        "Each player starts with numbers 1 through 9 on their board, representing the numbers they need to eliminate.",
        "Player 1 goes first. They will roll 2 six-sided dice.",
        "Based on the results of the dice roll, Player 1 can choose to either eliminate the sum of \nthe numbers or eliminate each individual number separately.",
        "Example: If Player 1 rolls a 3 and a 4, they can choose to eliminate the sum (7) or \nthe individual numbers (3 and 4).",
        "After Player 1 makes their choice, the turn switches to Player 2. The board will flip to Player 2's side.",
        "Player 2 will now roll 2 six-sided dice and make the same decision—eliminate the sum or \neliminate individual numbers based on their roll.",
        "Players take turns rolling the dice and eliminating numbers from their boards. \nThe goal is to eliminate all the numbers on your board.",
        "If a player has already eliminated the sum and at least one individual number from a \nprevious roll and cannot eliminate any more numbers from the new roll, they lose.",
        "When a player loses, their remaining numbers on the board are totaled to determine their score.\nThe best score a player can achieve being 0, having won and eliminated all the tiles",
        "The other player will continue rolling until they either run out of possible moves or \neliminate all their numbers from the board.",
        "The game ends when both players can no longer make moves. The player with the lower score wins.\nTies are possible in this game.",
        "Once the game ends, the scores are compared, and the player with the lower score is declared the winner.",
        "To restart, tap 'Restart' to play another round, or tap 'Exit' to quit the game."
    };

    private int currentIndex = 0;
    private double cooldownTime = 1.3;
    private long lastClickTime = 0;
    private Text instructionText;
    private Label warningLabel; // The warning message label
    private SkipButton skipButton; // Skip button to move to the next instruction

    /**
     * Constructor initializes the instruction display.
     * @param layout The layout where the instructions will be shown.
     */
    public Instructions(StackPane layout) 
    {
        instructionText = new Text();
        instructionText.setStyle("-fx-font-size: 20px; -fx-fill: white;");
        instructionText.setText(instructions[currentIndex]);

        layout.getChildren().add(instructionText);

        // Initialize the SkipButton and add it to the layout
        skipButton = new SkipButton(400, 300); // Passing position to SkipButton
        skipButton.setMinWidth(150);
        skipButton.setMinHeight(50);
        skipButton.setOnAction(e -> startGame(layout)); // Skip button should call startGame with layout

        layout.getChildren().add(skipButton); // Add the SkipButton to the layout

        // Set up the screen click handler
        layout.setOnMouseClicked(this::handleScreenClick);
    }

    /**
     * This method is called when the screen is clicked. It checks if enough time has passed
     * since the last click based on the cooldown timer. If the cooldown has expired, it moves
     * to the next instruction and updates the cooldown timer. If the cooldown hasn't expired,
     * it displays a message indicating how much time is left until the next click is allowed.
     * @param event The mouse click event.
     */
    private void handleScreenClick(MouseEvent event) 
    {
        long currentTime = System.currentTimeMillis();
        double elapsedTime = (currentTime - lastClickTime) / 1000.0;

        if (elapsedTime >= cooldownTime) 
        {
            if (currentIndex == instructions.length - 1) 
            {
                // When the last instruction is shown, create a TileBoard
                startGame(event);
            } else 
            {
                currentIndex = (currentIndex + 1) % instructions.length;
                if (cooldownTime > 0.9) 
                {
                    cooldownTime = 0.9;
                }
                lastClickTime = currentTime;
                instructionText.setText(instructions[currentIndex]);
            }
        } 
        else 
        {
            showWarningMessage(event);
        }
    }

    /**
     * This method shows a warning message if the user clicked before the cooldown is over.
     * The message will disappear after 0.8 seconds, and it will float upwards.
     * @param event The mouse click event.
     */
    private void showWarningMessage(MouseEvent event) 
    {
        // Create the warning label if it doesn't exist yet
        if (warningLabel == null) 
        {
            warningLabel = new Label("Please wait before clicking again!");
            warningLabel.setStyle("-fx-font-size: 18px; -fx-text-fill: red;");
        }

        // Position the warning label where the mouse was clicked
        StackPane layout = (StackPane) event.getSource();
        warningLabel.setLayoutX(event.getSceneX()); // Set X based on mouse click
        warningLabel.setLayoutY(event.getSceneY()); // Set Y based on mouse click

        // Add the warning label to the layout
        layout.getChildren().add(warningLabel);

        // Set up a TranslateTransition to animate the label moving upwards
        TranslateTransition translate = new TranslateTransition(Duration.seconds(0.8), warningLabel);
        translate.setByY(-20); // Move the label 20 pixels upwards
        translate.setOnFinished(e -> layout.getChildren().remove(warningLabel)); // Remove the label after the animation
        translate.play();
    }

    /**
     * This method is called when the last instruction is shown, and we create a TileBoard to start the game.
     */
    private void startGame(MouseEvent event) 
    {
        // Get the layout from the event source
        StackPane layout = (StackPane) event.getSource(); 
        
        // Clear all children (remove everything)
        layout.getChildren().clear();

        // Create the GameScreen and pass the layout to it
        GameScreen gameScreen = new GameScreen(layout);

        // Optionally, you can add a message or reset state for the game
        System.out.println("Instructions are over, starting the game!");
    }

    /**
     * This method is called when the last instruction is shown, and we create a TileBoard to start the game.
     * It will be used when skip button is pressed as well.
     */
    private void startGame(StackPane layout) 
    {
        // Clear all children (remove everything)
        layout.getChildren().clear();
        
        // Create the GameScreen and pass the layout to it
        GameScreen gameScreen = new GameScreen(layout);

        // Optionally, you can add a message or reset state for the game
        System.out.println("Instructions are over, starting the game!");
    }

    public String getNextInstruction() 
    {
        return instructions[currentIndex];
    }
}
