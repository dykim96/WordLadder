/*
    ADD YOUR HEADER HERE
 */


package assignment4;

import java.util.ArrayList;
//import java.util.HashMap;
import java.util.List;

// do not change class name or interface it implements
public class WordLadderSolver implements Assignment4Interface
{
    // declare class members here.
	
//	HashMap<Integer, String> dictionary;
	ArrayList<String> dictionary;
	
	public WordLadderSolver(String[] inputDict){
		
		 //insert code to parse the input dictionary, and set dictionary equal to this 
		dictionary = new ArrayList<String>();
		dictionary.add("heads");
		dictionary.add("tails");
		dictionary.add("heals");
		dictionary.add("teals");
		dictionary.add("tells");
		dictionary.add("tolls");
		dictionary.add("toils");

	}
	


    // do not change signature of the method implemented from the interface
    @Override
    public List<String> computeLadder(String startWord, String endWord) throws NoSuchLadderException 
    {
        // implement this method
    	ArrayList<String> solutionList = new ArrayList<String>();
    	
    	solutionList.add(startWord);
    	solutionList = MakeLadder(startWord, endWord, 5, solutionList); //initially pass in 5 because you haven't changed any. in the top most call, you want to change all 5 letters
    	
    	if (solutionList.size() == 0){
    		String message = "No word ladder possible between " + startWord + " and " + endWord;
    		throw new NoSuchLadderException(message);
    	}
    	return solutionList;
      // new UnsupportedOperationException("Not implemented yet!");
    }

    @Override
    public boolean validateResult(String startWord, String endWord, List<String> wordLadder) 
    {
        throw new UnsupportedOperationException("Not implemented yet!");
    }
    
    

    // add additional methods here
    public  ArrayList<String> MakeLadder(String startWord, String endWord, int index, ArrayList<String> solutionList){
    	
    	
    	
    	
    	ArrayList<String> returnList = new ArrayList<String>();// intentionally initialized to empty. if any paths reach a dead end, they should return an empty arraylist
    	ArrayList<String> tempList = solutionList;
    	for (int i = 0; i < 4; i++){
    		for (char l = 'a'; l <= 'z'; l++){
    			
    			if (i != index){ //test all indices except the current one
    				String tempString;
    				if (i==4){
    					tempString = startWord.substring(0, 4) + l;
    					
    				}
    				else{
    				 tempString = startWord.substring(0, i) + l + startWord.substring(i+1, 5); //replaces the current word with one letter
    				}
    				if (tempString.matches(endWord)){
    					tempList.add(tempString);
        				return tempList;

    				}
    				//haven't found the word yet:( keep searching by making recursive calls to all valid permutations 
    				if ((!tempString.matches(startWord))&&(!tempList.contains(tempString)) &&(dictionary.contains(tempString)) ){ 
    					//need to check 3 things before making recursive call
    						//1. the new word you formed is an actual word
    						//2. the word you are testing is not already in the list 
    						//3. the word you are testing is not the word you passed lol
    					tempList.add(tempString);
    					returnList = MakeLadder(tempString, endWord, i, tempList);
    					
    					
    				}
    				
    			}
    		}
    	}
    	
    	
    	// if one of the permutations reaches the end word, the base case will trigger, and return the full list.
    	// all of the permutations that dont reach the end return an empty list
    	return returnList;
    	
    	
    }
    
  
    
    
}

