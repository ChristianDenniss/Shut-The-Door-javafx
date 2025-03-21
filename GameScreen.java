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
    public GameScreen(StackPane layout) 
    {
        // Debugging: Log to confirm GameScreen constructor is called
        System.out.println("GameScreen constructor called");

        // Create a new GameBoard instance
        GameBoard gameBoard = new GameBoard();  

        // Add the GameBoard to the layout (as a child of GameScreen)
        layout.getChildren().add(gameBoard);

        // Debugging: Log to confirm GameBoard was added
        System.out.println("GameBoard added to layout");

        // Start the player input sequence
        askForPlayerNames(layout);
    }

    /**
     * Method to handle the player name input sequence.
     * It asks for Player 1's name, then Player 2's name.
     */
    private void askForPlayerNames(StackPane layout)
    {
        // Create the Player 1 input field
        TextField player1InputField = new TextField();
        player1InputField.setPromptText("Please enter player 1's username here");
        player1InputField.setAlignment(Pos.CENTER);
        player1InputField.setPrefWidth(250);  // Set a smaller width for the input field
        player1InputField.setStyle("-fx-font-size: 16px;");  // Increase font size for the input field
        player1InputField.setMaxWidth(350);  // Limit the width to make it not too wide
        player1InputField.setTranslateY(-250);  // Move input field higher up

        // Disable initial focus
        player1InputField.setFocusTraversable(false);  // This will prevent the field from focusing automatically

        // Set the maximum length to 15 characters
        player1InputField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.length() > 15) {
                player1InputField.setText(oldValue);  // Revert to the old value if the new value exceeds maxLength
            }
        });

        // Show the Player 1 input field
        layout.getChildren().add(player1InputField);

        // Delay enabling focus by 3 seconds
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(3), event -> {
            player1InputField.setFocusTraversable(true);  // Enable focus after 3 seconds
        }));
        timeline.setCycleCount(1);
        timeline.play();  // Start the timeline to wait for 3 seconds

        // Only focus when the user clicks on the field
        player1InputField.setOnMouseClicked(event -> {
            player1InputField.requestFocus();  // Set focus when clicked
        });

        // When Player 1 presses Enter, hide the input field and show Player 2's input field
        player1InputField.setOnAction(e -> {
            String player1Name = player1InputField.getText();
            player1InputField.setVisible(false);  // Hide Player 1 input field

            // Create Player 1 text to display their name (larger font for better visibility)
            player1NameText = new Text("Player 1: " + player1Name);
            player1NameText.setTranslateY(340);  // Set the position where you want it displayed
            player1NameText.setTranslateX(400);
            player1NameText.setFont(new Font("Arial", 24));  // Set a larger font size for player name
            layout.getChildren().add(player1NameText);

            // Show the Player 2 input field
            showPlayer2InputField(layout);
        });

        // Ensure prompt text stays until the user starts typing
        player1InputField.setOnMouseClicked(event -> {
            if (player1InputField.getText().isEmpty()) {
                player1InputField.clear();  // Clear prompt when clicked if it's empty
            }
        });

        // Clear prompt text only after typing begins
        player1InputField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.isEmpty() && oldValue.isEmpty()) {
                // The user has started typing, clear the prompt text
                player1InputField.setPromptText("");
            }
        });
    }

    /**
     * Method to show the Player 2 input field after Player 1 has entered their name.
     * @param layout The StackPane layout to add the Player 2 input field to.
     */
    private void showPlayer2InputField(StackPane layout)
    {
        // Create the Player 2 input field
        TextField player2InputField = new TextField();
        player2InputField.setPromptText("Please enter player 2's username here");
        player2InputField.setAlignment(Pos.CENTER);
        player2InputField.setPrefWidth(250);  // Set a smaller width for the input field
        player2InputField.setStyle("-fx-font-size: 16px;");  // Increase font size for the input field
        player2InputField.setMaxWidth(350);  // Limit the width to make it not too wide
        player2InputField.setTranslateY(-250);  // Move input field higher up

        // Set the maximum length to 15 characters
        player2InputField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.length() > 15) {
                player2InputField.setText(oldValue);  // Revert to the old value if the new value exceeds maxLength
            }
        });

        // Show the Player 2 input field
        layout.getChildren().add(player2InputField);

        // Delay enabling focus by 3 seconds
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(3), event -> {
            player2InputField.setFocusTraversable(true);  // Enable focus after 3 seconds
        }));
        timeline.setCycleCount(1);
        timeline.play();  // Start the timeline to wait for 3 seconds

        // Only focus when the user clicks on the field
        player2InputField.setOnMouseClicked(event -> {
            player2InputField.requestFocus();  // Set focus when clicked
        });

        // When Player 2 presses Enter, hide the input field and display the username
        player2InputField.setOnAction(e -> {
            String player2Name = player2InputField.getText();
            player2InputField.setVisible(false);  // Hide Player 2 input field

            // Create Player 2 text to display their name (larger font for better visibility)
            player2NameText = new Text("Player 2: " + player2Name);
            player2NameText.setTranslateY(-340);  
            player2NameText.setTranslateX(400);
            player2NameText.setFont(new Font("Arial", 24));  // Set a larger font size for player name

            // Rotate Player 2's name 180 degrees so it appears upside down
            player2NameText.setRotate(180);  

            layout.getChildren().add(player2NameText);
        });

        // Ensure prompt text stays until the user starts typing
        player2InputField.setOnMouseClicked(event -> {
            if (player2InputField.getText().isEmpty()) {
                player2InputField.clear();  // Clear prompt when clicked if it's empty
            }
        });

        // Clear prompt text only after typing begins
        player2InputField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.isEmpty() && oldValue.isEmpty()) {
                // The user has started typing, clear the prompt text
                player2InputField.setPromptText("");
            }
        });
    }
}
