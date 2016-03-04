/*  Assignment 4: Word Ladder
 *  Create word ladder by using dictionary
 *  Section: 16185
 *  Name: Doyoung Kim, Rohan Tanna
 *  UTEID: dk24338, rrt494
 */
package assignment4;

public class NoSuchLadderException extends Exception
{
    private static final long serialVersionUID = 1L;

    public NoSuchLadderException(String message)
    {
        super(message);
    }

    public NoSuchLadderException(String message, Throwable throwable)
    {
        super(message, throwable);
    }
}
