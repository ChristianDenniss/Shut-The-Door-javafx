/****************************************************************
* Class to create all tiles and handle their logic as a whole horizontal unit
*
* Christian Dennis
* 26/02/2025
* 0.0.1
******************************************************************/

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
        this.isBoardFlipped = isBoardFlipped;  // Set initial board state (flipped or not)
        tiles = new NumberTile[9];  // Initialize array to hold 9 tiles
        
        setTranslateX(translateX);  // Set the horizontal position
        setTranslateY(translateY);  // Set the vertical position

        // Create and add each tile (with values 1 to 9)
        for (int i = 0; i < 9; i++)
        {
            tiles[i] = new NumberTile(i + 1);  // Create a tile with value 1-9
            getChildren().add(tiles[i]);  // Add tile to the HBox
        }

        setSpacing(6);  // Add spacing between tiles in the HBox

        updateTileOrientation();  // Update the tile orientation based on the flipped state
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
            return tiles[index];  // Return the tile at the specified index
        }
        else
        {
            throw new IndexOutOfBoundsException("Invalid tile index");
        }
    }

    /**
     * Getter for the array of tiles.
     * 
     * @return The array of NumberTile objects.
     */
    public NumberTile[] getTiles()
    {
        return tiles;  // Return the array of tiles
    }

    /**
     * Method to reset all tiles to their "off" state (unhighlighted).
     */
    public void resetTiles()
    {
        for (NumberTile tile : tiles)
        {
            tile.setTileState(false);  // Turn all tiles off (unhighlighted)
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
            tiles[index].setTileState(true);  // Turn the tile at the given index "on"
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
            tiles[index].setTileState(false);  // Turn the tile at the given index "off"
        }
    }

    /**
     * Method to update the orientation of the tiles based on the board's state (flipped or not).
     * If the board is flipped, the tiles will be rotated 180 degrees and horizontally flipped.
     */
    private void updateTileOrientation()
    {
        if (isBoardFlipped)
        {
            // If the board is flipped, rotate each tile 180 degrees and flip horizontally
            for (NumberTile tile : tiles)
            {
                tile.setRotate(180);  // Rotate the tile by 180 degrees
                tile.setScaleX(-1);  // Flip the tile horizontally (Player 2's view)
            }
        }
        else
        {
            // If the board is not flipped, reset any rotations and flips
            for (NumberTile tile : tiles)
            {
                tile.setRotate(0);   // Reset any rotation
                tile.setScaleX(1);   // Reset horizontal flip
            }
        }
    }

    /**
     * Method to update the board's flipped state.
     * This method will update the tile orientation based on whether the board is flipped or not.
     * 
     * @param isBoardFlipped If true, the board will be flipped (Player 2's view).
     */
    public void setBoardFlipped(boolean isBoardFlipped)
    {
        this.isBoardFlipped = isBoardFlipped;
        updateTileOrientation();  // Update the tile orientation based on the new flipped state
    }

    /**
     * Getter for the current flipped state of the board.
     * 
     * @return True if the board is flipped (Player 2's view), false if not (Player 1's view).
     */
    public boolean isBoardFlipped()
    {
        return isBoardFlipped;  // Return the current flipped state of the board
    }
}
