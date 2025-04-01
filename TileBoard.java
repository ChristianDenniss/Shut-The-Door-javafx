import javafx.scene.layout.GridPane;
import javafx.scene.effect.ColorAdjust;

/****************************************************************
* Class to create the 1x9 grid of Number tile objects and manipulate them
*
* Christian Dennis
* 26/02/2025
* 0.0.1
******************************************************************/

public class TileBoard extends GridPane 
{
    private NumberTile[] tiles; // Array to hold the 9 tiles on the board
    private boolean myTurn; // Flag to check if the board is flipped (Player 2's view)

    /**
     * Constructor to initialize the TileBoard.
     * This will create a 1-row, 9-column grid of NumberTile objects and place them in a GridPane.
     * The board's state (flipped or not) and position (translateX, translateY) are set based on the parameters.
     *
     * @param isBoardFlipped If true, the board is flipped (Player 2's view).
     * @param translateX The horizontal position of the board.
     * @param translateY The vertical position of the board.
     */
    public TileBoard(boolean myTurn, double translateX, double translateY) 
    {
        System.out.println("Constructor called with myTurn: " + myTurn);
        this.myTurn = myTurn;
        tiles = new NumberTile[9];
    
        // Set the position of the board
        setTranslateX(translateX);
        setTranslateY(translateY);
    
        // Create tiles in normal order (1 to 9)
        for (int i = 0; i < 9; i++) 
        {
            tiles[i] = new NumberTile(i + 1);  // 1 to 9
            tiles[i].setMouseTransparent(false);
    
            // Adding a click handler for each tile
            int index = i;  
            tiles[i].setOnMouseClicked(event -> handleTileClick(index)); 
            tiles[i].setOnMouseEntered(event -> handleMouseEntered(index));  
            tiles[i].setOnMouseExited(event -> handleMouseExited(index)); 
        }
    
        // Add tiles to the GridPane in a single row with 9 columns
        // If myTurn is false, Player 2's view, reverse the order vertically.
        if (!myTurn) 
        {
            for (int i = 0; i < 9; i++) 
            {
                add(tiles[8 - i], i, 0);  // Add tiles in reverse order (9 to 1 from top to bottom)
            }
        } 
        else 
        {
            for (int i = 0; i < 9; i++) 
            {
                add(tiles[i], i, 0);  // Add tiles in normal order (1 to 9 from top to bottom)
            }
        }
    
        setHgap(10);  // Horizontal gap between tiles
        setVgap(10);  // Vertical gap between tiles
    
        updateTileOrientation();  // Update the tile orientations based on myTurn
    }

    /**
     * Method to handle the mouse hover in event.
     * You can modify this method to apply any visual effects when hovering over a tile.
     *
     * @param index The index of the tile being hovered over.
     */
    private void handleMouseEntered(int index) 
    {
        if (tiles[index].isClosed() == true) 
        {
            // Example: Change the tile's opacity or apply a visual effect on hover
            System.out.println("Mouse entered tile at index: " + index);

            // Applying a color effect (adjusting brightness on hover)
            ColorAdjust colorAdjust = new ColorAdjust();
            colorAdjust.setBrightness(0.2); // Increase brightness when mouse enters
            tiles[index].setEffect(colorAdjust);
        }
    }

    /**
     * Method to handle the mouse hover out event.
     * Revert any changes made during hover in.
     *
     * @param index The index of the tile that the mouse exited.
     */
    private void handleMouseExited(int index) 
    {
        if (tiles[index].isClosed() == true) 
        {
            // Example: Revert the tile's opacity or effect when hover is removed
            System.out.println("Mouse exited tile at index: " + index);

            // Removing the effect when the mouse exits the tile
            tiles[index].setEffect(null);
        }
    }

    /**
     * Method to get a specific tile by its index (0-8).
     * 
     * @param index The index of the tile to retrieve (0-8).
     * @return The NumberTile object at the given index.
     * @throws IndexOutOfBoundsException If the index is invalid (not between 0 and 8).
     */
    public NumberTile getTile(int index) 
    {
        if (index >= 0 && index < 9) 
        {
            return tiles[index];
        }
        throw new IndexOutOfBoundsException("Invalid tile index");
    }
    
    /**
     * Method to get the sum of the values of the tiles that are closed.
     *
     * @return The sum of the values of closed tiles.
     */
    public int getClosedTilesSum() 
    {
        int sum = 0;  // Initialize sum to 0
    
        // Iterate through all the tiles on the board
        for (NumberTile tile : tiles) 
        {
            // Check if the tile is closed
            if (tile.isClosed()) 
            {
                // Add the value of the closed tile to the sum
                sum += tile.getValue();
            }
        }
    
        // Return the total sum of the values of closed tiles
        return sum;
    }
    
    /**
     * Getter for the array of tiles.
     * 
     * @return The array of NumberTile objects.
     */
    public NumberTile[] getTiles() 
    {
        return tiles;
    }

    /**
     * Method to reset all tiles to their "off" state (unhighlighted).
     */
    public void resetTiles() 
    {
        for (NumberTile tile : tiles) 
        {
            tile.openTile();
            tile.setInitialTileState();
        }
    }

    /**
     * Method to turn a specific tile "on" by its index.
     * 
     * @param index The index of the tile to turn on (0-8).
     */
    public void setTileOn(int index) 
    {
        if (index >= 0 && index < 9) 
        {
            tiles[index].openTile();
            tiles[index].setTileState();
        }
    }

    /**
     * Method to turn a specific tile "off" by its index.
     * 
     * @param index The index of the tile to turn off (0-8).
     */
    public void setTileOff(int index) 
    {
        if (index >= 0 && index < 9) 
        {
            tiles[index].setTileState();
        }
    }

    /**
     * Method to update the orientation of the tiles based on the board's state (flipped or not).
     */
    private void updateTileOrientation() 
    {
        for (int i = 0; i < tiles.length; i++) 
        {
            if (!myTurn) 
            {
                // Rotate the number 180 degrees when the board is flipped (Player 2's view)
                tiles[i].getValueText().setRotate(180);
            } 
            else 
            {
                // Ensure the number is upright if the board is not flipped (Player 1's view)
                tiles[i].getValueText().setRotate(0);
            }
        }
    }

    /**
     * Method to update the board's flipped state.
     * 
     * @param isBoardFlipped If true, the board will be flipped (Player 2's view).
     */
    public void changePlayersTurn() 
    {
        //if it was just the players turn it is no longer and vice versa
        if(this.myTurn == true )
        {
            this.myTurn = false;
        }
        
        else
        {
            this.myTurn = true;
        }
    }

    /**
     * Getter for the current flipped state of the board.
     * 
     * @return True if the board is flipped (Player 2's view), false if not (Player 1's view).
     */
    public boolean isMyTurn() 
    {
        return myTurn;
    }

    /**
     * Method to handle tile click event and get the clicked tile index.
     * 
     * @param index The index of the clicked tile.
     */
    private void handleTileClick(int index) 
    {
        // Handle tile click logic here, using the index of the clicked tile
        System.out.println("Tile clicked at index: " + index + " Tile value: " + tiles[index].getValue() + " is closed:" + tiles[index].isClosed());
        if(GameScreen.gamePlayable)
        {
            if (!myTurn) 
            {
                MediaHandler.playSound("failClick.mp3");
                
                System.out.println("not this tile boards turn");
            } 
            else 
            {
                MediaHandler.playSound("succesfulClick.mp3");
                
                System.out.println("it is this tile boards turn");
                tiles[index].setTileState();
            }
        }
        else
        {
            MediaHandler.playSound("failClick.mp3");
            System.out.println("Game isn't playable right now");
        }
    }
}
