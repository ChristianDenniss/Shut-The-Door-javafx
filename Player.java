
/**
 * Write a description of class Player here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Player
{
    private String username;
    private int score;
    
    public Player(String username)
    {
        this.score = 45;
        this.username = username;
        
    }
    
    public String getName()
    {
        return username;
    }
    
    public int score()
    {
        return score;
    }
    
}
