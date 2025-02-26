import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/****************************************************************
* Abstract class for our menu buttons to set visual effects and required methods
*
* Christian Dennis
* 26/02/2025
* 0.0.1
******************************************************************/

// Base button class, will be inherited by specific button types
public abstract class MenuButton extends Button
{
    // Constructor to set common styles or properties for all buttons
    public MenuButton(String text)
    {
        super(text);
        
        // Set our initial button color (green) and text size
        setStyle("-fx-font-size: 16px; -fx-padding: 10px; -fx-background-color: #32CD32; -fx-text-fill: white;");

        // Apply hover effect for glowing and text growth
        setOnMouseEntered(event -> {
            setStyle("-fx-font-size: 20px; -fx-padding: 10px; -fx-background-color: #32CD32; -fx-text-fill: white; -fx-effect: dropshadow(gaussian, yellow, 15, 0.8, 0, 0);");
        });
        
        // Reset the button styles when the mouse exits (no glow and text shrinks back)
        setOnMouseExited(event -> {
            setStyle("-fx-font-size: 16px; -fx-padding: 10px; -fx-background-color: #32CD32; -fx-text-fill: white; -fx-effect: none;");
        });
    }
    
    // Abstract method that subclasses will need to implement to define their own action behavior
    protected abstract EventHandler<ActionEvent> createAction();
    
    // Handle mouse entering the button - change the background color and add glow effect
    private void handleMouseEnter(MouseEvent event)
    {
        setStyle("-fx-font-size: 16px; -fx-padding: 10px; -fx-background-color: #388E3C; -fx-text-fill: white; -fx-effect: dropshadow(gaussian, green, 10, 0, 0, 0);");
    }

    // Handle mouse exiting the button - revert the button's style to the original
    private void handleMouseExit(MouseEvent event)
    {
        setStyle("-fx-font-size: 16px; -fx-padding: 10px; -fx-background-color: #4CAF50; -fx-text-fill: white;");
    }
}
