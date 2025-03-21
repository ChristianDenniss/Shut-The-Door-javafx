import javafx.scene.layout.HBox;

public class TileBoard extends HBox
{
    private NumberTile[] tiles; // Array to hold the 9 tiles on the board
    private boolean isBoardFlipped; // Flag to check if the board is flipped (Player 2's view)

    /**
     * Constructor to initialize the TileBoard.
     * This will create a 3x3 grid of NumberTile objects and place them in an HBox.
     * The board's state (flipped or not) and position (translateX, translateY) are set based on the parameters.
     *
     * @param isBoardFlipped If true, the board is flipped (Player 2's view).
     * @param translateX The horizontal position of the board.
     * @param translateY The vertical position of the board.
     */
    public TileBoard(boolean isBoardFlipped, double translateX, double translateY)
    {
        this.isBoardFlipped = isBoardFlipped; 
        tiles = new NumberTile[9];  
        
        // x and y position
        setTranslateX(translateX);  
        setTranslateY(translateY);  

        // Create tiles in normal order
        for (int i = 0; i < 9; i++)
        {
            tiles[i] = new NumberTile(i + 1);  
        }

        // If flipped, reverse order
        if (isBoardFlipped)
        {
            for (int i = 8; i >= 0; i--)
            {
                getChildren().add(tiles[i]);
            }
        }
        else
        {
            getChildren().addAll(tiles);
        }

        setSpacing(10);  
        updateTileOrientation();
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
            tile.setTileState(false);
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
            tiles[index].setTileState(true);
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
            tiles[index].setTileState(false);
        }
    }

    /**
     * Method to update the orientation of the tiles based on the board's state (flipped or not).
     */
    private void updateTileOrientation() 
    {
        for (NumberTile tile : tiles) 
        {
            // Rotate the number based on the board state (flipped or not)
            if (isBoardFlipped) 
            {
                // Rotate the number 180 degrees when the board is flipped
                tile.getValueText().setRotate(180);
                // Ensure no horizontal flipping by not changing scaleX
            } 
            else 
            {
                // Ensure the number is upright if the board is not flipped
                tile.getValueText().setRotate(0);
            }
        }
    }

    /**
     * Method to update the board's flipped state.
     * 
     * @param isBoardFlipped If true, the board will be flipped (Player 2's view).
     */
    public void setBoardFlipped(boolean isBoardFlipped)
    {
        this.isBoardFlipped = isBoardFlipped;
        updateTileOrientation();
    }

    /**
     * Getter for the current flipped state of the board.
     * 
     * @return True if the board is flipped (Player 2's view), false if not (Player 1's view).
     */
    public boolean isBoardFlipped()
    {
        return isBoardFlipped;
    }
}
