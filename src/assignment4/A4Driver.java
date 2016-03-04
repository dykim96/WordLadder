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
        long time = 0;
        try 
        {
            List<String> result = wordLadderSolver.computeLadder("money", "honey");
            time = System.currentTimeMillis();
            result = wordLadderSolver.computeLadder("heads", "tails");
            result = wordLadderSolver.computeLadder("ryan", "joe");
            result = wordLadderSolver.computeLadder("altas", "zebra");
            /*boolean correct = wordLadderSolver.validateResult("money", "honey", result);
            if(correct){
            	System.out.println("Found!");
            }
            else{
            	System.out.println("Not Found!");
            }*/
        } 
        catch (NoSuchLadderException e) 
        {
            time = System.currentTimeMillis() - time;
            System.out.println(time);
            //e.printStackTrace();
        }
    }
}
