import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.effect.ColorAdjust;

public class NumberTile extends StackPane
{
    private int value;  // The value of the tile (1-9)
    private boolean isOn; // Whether the tile is "on" (highlighted)
    private Rectangle tileBackground;  // The square background of the tile
    private Text valueText;  // The text displaying the number

    public NumberTile(int value)
    {
        this.value = value;
        this.isOn = false;

        // Create a square background for the tile
        tileBackground = new Rectangle(60, 60); // Tile size (adjust if needed)
        tileBackground.setFill(Color.BROWN);  // Wooden color (you can change the color or add texture)
        tileBackground.setMouseTransparent(false);
        
        // Create the text for the number
        valueText = new Text(String.valueOf(value));
        valueText.setFill(Color.WHITE);  // Text color (white for contrast)
        
         // Set the font size (for example, setting the font size to 20px)
        valueText.setStyle("-fx-font-size: 20px;");

        // Add both the background and the text to the StackPane
        getChildren().addAll(tileBackground, valueText);

        // Initially, tile is not "on" (no highlight)
        setTileState(false);   
    }

    // Public method to set the tile's state (on/off)
    public void setTileState(boolean isOn)
    {
        this.isOn = isOn;

        // If tile is "on", highlight it by changing the color and darkening the background
        if (isOn)
        {
            tileBackground.setFill(Color.GREEN);  // Set a highlighted color (green)
            // Darken the tile (reduce brightness) using a color adjust effect
            ColorAdjust colorAdjust = new ColorAdjust();
            colorAdjust.setBrightness(-0.5);  // Darken by 50%
            tileBackground.setEffect(colorAdjust);
        }
        else
        {
            tileBackground.setFill(Color.BROWN);  // Reset to the original wooden color
            tileBackground.setEffect(null);  // Remove the darkening effect
        }
    }
    
    public Text getValueText()
    {
        return valueText;
    }
    // Getter for the tile's value
    public int getValue()
    {
        return value;
    }

    // Getter for the "on" state
    public boolean isOn()
    {
        return isOn;
    }

    // Method to flip the tile (used for Player 2's view)
    public void flipTile()
    {
        // Flip the tile's content horizontally (Player 2's view)
        setScaleX(getScaleX() * -1);  // Flip horizontally
    }

    // Method to reset the flip state for Player 1's view
    public void resetFlip()
    {
        setScaleX(Math.abs(getScaleX()));  // Ensure the tile is not flipped
    }
}
