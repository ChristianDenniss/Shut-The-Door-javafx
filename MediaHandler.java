import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/****************************************************************
* Class to handle the finding,prep, and errors behind our images and videos
*
* Christian Dennis
* 26/02/2025
* 0.0.1
******************************************************************/

public class MediaHandler
{
    // This method will handle loading and playing a video in the background
    public static MediaView playBackgroundVideo(Stage stage, String videoTitle)
    {
        // Load the video from the resources folder
        String videoFilePath = "resources/" + videoTitle;  // Path to your video file
        File file = new File(videoFilePath);

        if (!file.exists())  // Check if the file exists
        {
            System.out.println("Video file not found: " + videoFilePath);
            return null;
        }

        Media media = new Media(file.toURI().toString());  // Load media (absolute file path)

        // Create the MediaPlayer
        MediaPlayer mediaPlayer = new MediaPlayer(media);

        // Set the video to loop forever
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);

        // Create a MediaView to display the video
        MediaView mediaView = new MediaView(mediaPlayer);

        // Set the video to fill the entire window without preserving aspect ratio
        mediaView.setFitWidth(stage.getWidth());  // Stretch width to window
        mediaView.setFitHeight(stage.getHeight());  // Stretch height to window
        mediaView.setPreserveRatio(false);  // Disable preserving aspect ratio

        // Add a listener to update the video size when the window is resized
        stage.widthProperty().addListener((observable, oldValue, newValue) -> {
            mediaView.setFitWidth(newValue.doubleValue());  // Update width when stage resizes
        });
        stage.heightProperty().addListener((observable, oldValue, newValue) -> {
            mediaView.setFitHeight(newValue.doubleValue());  // Update height when stage resizes
        });

        // Start playing the video
        mediaPlayer.play();

        return mediaView;
    }

    // Method to load an image from the local resources folder with the specified filename, width, and height
    public static ImageView loadImage(String fileName, double width, double height)
    {
        try
        {
            // Hardcoded local path to the resources folder relative to the project root directory
            File file = new File("resources/" + fileName);

            if (!file.exists())  // Check if the file exists in the resources folder
            {
                System.out.println("Image file not found: " + fileName);
                return null;
            }

            FileInputStream imageStream = new FileInputStream(file);  // Read the file using FileInputStream
            Image image = new Image(imageStream);  // Create an Image object from the file input stream
            ImageView imageView = new ImageView(image);  // Wrap the image in an ImageView

            // Set the width and height of the image
            imageView.setFitWidth(width);
            imageView.setFitHeight(height);

            return imageView;  // Return the ImageView with the image
        }
        catch (IOException e)  // Handle the case where the file could not be loaded
        {
            System.out.println("Error loading image: " + fileName);
            e.printStackTrace();
            return null;
        }
    }
    
     // Method to play a sound from the resources folder
    public static MediaPlayer playSound(String soundTitle)
    {
        // Load the sound from the resources folder
        String soundFilePath = "resources/" + soundTitle;  // Path to your sound file
        File file = new File(soundFilePath);

        if (!file.exists())  // Check if the file exists
        {
            System.out.println("Sound file not found: " + soundFilePath);
            return null;
        }

        // Create Media and MediaPlayer to play the sound
        Media media = new Media(file.toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);

        // Play the sound immediately
        mediaPlayer.play();

        return mediaPlayer;  // Return the MediaPlayer for controlling playback (pause, stop, etc.)
    }

    // Method to loop a sound indefinitely
    public static MediaPlayer playLoopedSound(String soundTitle)
    {
        // Load the sound from the resources folder
        String soundFilePath = "resources/" + soundTitle;  // Path to your sound file
        File file = new File(soundFilePath);

        if (!file.exists())  // Check if the file exists
        {
            System.out.println("Sound file not found: " + soundFilePath);
            return null;
        }

        // Create Media and MediaPlayer to play the sound
        Media media = new Media(file.toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);

        // Set the MediaPlayer to loop the sound indefinitely
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);

        // Play the sound
        mediaPlayer.play();

        return mediaPlayer;  // Return the MediaPlayer for controlling playback
    }

    // Method to stop the sound
    public static void stopSound(MediaPlayer mediaPlayer)
    {
        if (mediaPlayer != null)
        {
            mediaPlayer.stop();  // Stop the currently playing sound
        }
    }

    // Method to pause the sound
    public static void pauseSound(MediaPlayer mediaPlayer)
    {
        if (mediaPlayer != null)
        {
            mediaPlayer.pause();  // Pause the sound
        }
    }

    // Method to resume the sound from where it was paused
    public static void resumeSound(MediaPlayer mediaPlayer)
    {
        if (mediaPlayer != null)
        {
            mediaPlayer.play();  // Resume playing the sound
        }
    }
}
