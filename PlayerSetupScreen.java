import javafx.scene.layout.StackPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.image.Image;
import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.image.ImageView;
import javafx.scene.media.MediaView;
import javafx.animation.PauseTransition;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.util.Duration;

public class PlayerSetupScreen extends StackPane 
{
    private String player1Name;
    private String player2Name;

    public PlayerSetupScreen(StackPane layout) 
    {
        // Set the background image or video for the window
        setBackground(layout);

        // Proceed with the usual setup
        askForPlayer1Name(layout);
    }

    // Method to set the background (either image or video)

    private void setBackground(StackPane layout) 
    {
        // Wait for layout to be fully initialized to get its size
        System.out.println("Player setup screen background created");
    
        // Add listeners to layout size
        layout.widthProperty().addListener((obs, oldWidth, newWidth) -> {
            layout.heightProperty().addListener((obs2, oldHeight, newHeight) -> {
                // Debugging layout size
                System.out.println("New layout size: width=" + newWidth + ", height=" + newHeight);
    
                // Define the path to the image
                String imagePath = "resources/black.png"; // Adjust path relative to the project root
    
                try 
                {
                    // Load the image from the file path
                    FileInputStream fileInputStream = new FileInputStream(imagePath);
                    Image image = new Image(fileInputStream);
    
                    // Check if the image was loaded successfully
                    if (image != null) 
                    {
                        // Create an ImageView with the loaded image
                        ImageView backgroundImage = new ImageView(image);
                        backgroundImage.setFitWidth(newWidth.doubleValue());  // Adjust the image width
                        backgroundImage.setFitHeight(newHeight.doubleValue()); // Adjust the image height
                        
                        // Add the background image to the layout
                        layout.getChildren().add(backgroundImage);
                        System.out.println("Player setup screen background added to layout");
                    } 
                    else 
                    {
                        System.out.println("Failed to load background image");
                    }
    
                } 
                catch (FileNotFoundException e) 
                {
                    // Handle file not found exception
                    System.out.println("Error: Image file not found at path: " + imagePath);
                    e.printStackTrace();
                }
            });
        });
    }




    private void askForPlayer1Name(StackPane layout) 
    {
        TextField player1InputField = new TextField();
        player1InputField.setPromptText("Enter Player 1's name");
        player1InputField.setAlignment(Pos.CENTER);
        player1InputField.setPrefWidth(250);
        player1InputField.setStyle("-fx-font-size: 16px;");
        player1InputField.setMaxWidth(350);
        player1InputField.setTranslateY(-250);

        // Disable the TextField initially
        player1InputField.setDisable(true);

        layout.getChildren().add(player1InputField);

        // Pause for 3 seconds before enabling and focusing the field
        PauseTransition pause = new PauseTransition(Duration.seconds(1.5));
        pause.setOnFinished(e -> {
            player1InputField.setDisable(false); // Enable the TextField
            player1InputField.requestFocus(); // Focus the TextField
        });
        pause.play();

        player1InputField.setOnAction(e -> {
            player1Name = player1InputField.getText().trim();
            player1InputField.setVisible(false);

            Text player1Text = new Text("Player 1: " + player1Name);
            player1Text.setTranslateY(338);
            player1Text.setTranslateX(400);
            player1Text.setFont(new Font("Arial", 24));

            askForPlayer2Name(layout, player1Name, player1Text);
        });
    }

    private void askForPlayer2Name(StackPane layout, String player1Name, Text player1Text) 
    {
        String p1Name = player1Name;
        Text player1Txt = player1Text;

        TextField player2InputField = new TextField();
        player2InputField.setPromptText("Enter Player 2's name");
        player2InputField.setAlignment(Pos.CENTER);
        player2InputField.setPrefWidth(250);
        player2InputField.setStyle("-fx-font-size: 16px;");
        player2InputField.setMaxWidth(350);
        player2InputField.setTranslateY(-250);

        // Disable the TextField initially
        player2InputField.setDisable(true);

        layout.getChildren().add(player2InputField);

        // Pause for 3 seconds before enabling and focusing the field
        PauseTransition pause = new PauseTransition(Duration.seconds(1.5));
        pause.setOnFinished(e -> {
            player2InputField.setDisable(false); // Enable the TextField
            player2InputField.requestFocus(); // Focus the TextField
        });
        pause.play();

        player2InputField.setOnAction(e -> {
            player2Name = player2InputField.getText().trim();
            player2InputField.setVisible(false);

            Text player2Text = new Text("Player 2: " + player2Name);
            player2Text.setTranslateY(-340);
            player2Text.setTranslateX(400);
            player2Text.setFont(new Font("Arial", 24));
            player2Text.setRotate(180);

            // Create Players and start the game
            Player player1 = new Player(p1Name);
            Player player2 = new Player(player2Name);
            startGame(layout, player1, player2, player1Txt, player2Text);
        });
    }

    private void startGame(StackPane layout, Player player1, Player player2, Text p1Text, Text p2Text) 
    {
        layout.getChildren().clear();
        GameScreen gameScreen = new GameScreen(layout, player1, player2, p1Text, p2Text);
        layout.getChildren().add(gameScreen);
    }
}
