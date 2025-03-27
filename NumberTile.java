import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.effect.ColorAdjust;

/****************************************************************
* Class to create a NumberTile object representing a single tile
* with a number (1-9), which can be toggled between "on" and "off"
* states. The tile has a visual representation (background and 
* number) and can be flipped for Player 2's view.
*
* Christian Dennis
* 26/02/2025
* 0.0.1
******************************************************************/

public class NumberTile extends StackPane 
{
    // The value of the tile (1-9)
    private int value;  
    
    // Whether the tile is "on" (highlighted)
    private boolean isOn; 
    
    // The square background of the tile
    private Rectangle tileBackground;  
    
    // The text displaying the number
    private Text valueText;  
    
    // If the tile is closed or opened
    public boolean closed; 

    /**
     * Constructor to initialize the NumberTile.
     * This will create a square tile with a number (1-9) displayed.
     * The tile is initially closed (not highlighted).
     * 
     * @param value The value to be displayed on the tile (1-9).
     */
    public NumberTile(int value) 
    {
        // Set the tile's initial closed state
        this.closed = true;
        
        // Set the value of the tile
        this.value = value;

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
        setInitialTileState();
    }

    /**
     * Method to set the tile's state back to its initial (off) state.
     * The tile background will return to its original color (brown), 
     * and any effects applied will be removed.
     */
    public void setInitialTileState() 
    {
        // Start as brown with no changes
        tileBackground.setFill(Color.BROWN);  
        tileBackground.setEffect(null); 

        // Set the tile as closed
        this.closed = true;
    }

    /**
     * Method to toggle the tile's state between "on" and "off".
     * When the tile is clicked, it will open (become green and darker),
     * if it's not already open.
     */
    public void setTileState() 
    {
        if (closed) 
        {
            // If tile is closed, open it and apply changes
            System.out.println("Opening clicked tile");
            tileBackground.setFill(Color.GREEN);  // Set a highlighted color (green)
            
            // Darken the tile (reduce brightness) using a color adjust effect
            ColorAdjust colorAdjust = new ColorAdjust();
            colorAdjust.setBrightness(-0.5);  // Darken by 50%
            tileBackground.setEffect(colorAdjust);

            // Mark the tile as opened
            this.closed = false;  
        } 
        else 
        {
            // If the tile is already open, print a message
            System.out.println("Tile is already open; cannot click again.");
        }
    }

    /**
     * Getter for the text representing the tile's value.
     * 
     * @return The Text object representing the number on the tile.
     */
    public Text getValueText() 
    {
        return valueText;
    }

    /**
     * Getter for the tile's value.
     * 
     * @return The value of the tile (1-9).
     */
    public int getValue() 
    {
        return value;
    }

    /**
     * Getter for the "on" state of the tile.
     * 
     * @return True if the tile is highlighted (on), false otherwise.
     */
    public boolean isOn() 
    {
        return isOn;
    }

    /**
     * Getter for the "closed" state of the tile.
     * 
     * @return True if the tile is closed (not opened), false otherwise.
     */
    public boolean isClosed() 
    {
        return closed;
    }

    /**
     * Method to open the tile (mark it as opened).
     */
    public void openTile() 
    {
        // Mark tile as opened
        closed = false;  
    }

    /**
     * Method to flip the tile for Player 2's view.
     * This will mirror the tile's contents horizontally and rotate the text.
     */
    public void flipTile() 
    {
        // Flip the tile's content horizontally (Player 2's view)
        setScaleX(getScaleX() * -1);  // Flip horizontally
        valueText.setRotate(valueText.getRotate() + 180);  // Also rotate the text
    }

    /**
     * Method to reset the tile to its original (not flipped) state.
     * This ensures the tile is correctly oriented for Player 1's view.
     */
    public void resetFlip() 
    {
        // Ensure the tile is not flipped horizontally
        setScaleX(Math.abs(getScaleX()));  
    }
}
