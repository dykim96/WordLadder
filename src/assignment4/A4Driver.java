/*  Assignment 4: Word Ladder
 *  Create word ladder by using dictionary
 *  Section: 16185
 *  Name: Doyoung Kim, Rohan Tanna
 *  UTEID: dk24338, rrt494
 */
package assignment4;

import java.util.List;

public class A4Driver
{
    public static void main(String[] args)
    {
        // Create a word ladder solver object
        Assignment4Interface wordLadderSolver = new WordLadderSolver(args);
        try 
        {
            List<String> result = wordLadderSolver.computeLadder("money", "honey");
            boolean correct = wordLadderSolver.validateResult("money", "honey", result);
        } 
        catch (NoSuchLadderException e) 
        {
            e.printStackTrace();
        }
    }
}
