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
	
	public WordLadderSolver(ArrayList<String> dictionary){
		this.dictionary = dictionary;
	}


    // do not change signature of the method implemented from the interface
    @Override
    public List<String> computeLadder(String startWord, String endWord) throws NoSuchLadderException 
    {
        // implement this method
    	ArrayList<String> solutionList = new ArrayList<String>();

    	//if dictionary does not contain startword or endword return empty arraylist 
    	if(!dictionary.contains(startWord) || !dictionary.contains(endWord)){
    		return solutionList;
    	}
    	
    	solutionList.add(startWord);
    	//if startword is also endword just return wordladder
    	if(startWord.equals(endWord)){
    		return solutionList;
    	}
    	solutionList = MakeLadder(startWord, endWord, 5, solutionList); //initially pass in 5 because you haven't changed any. in the top most call, you want to change all 5 letters
    	
    	if (solutionList.size() == 0){
    		String message = "No word ladder possible between " + startWord + " and " + endWord;
    		throw new NoSuchLadderException(message);
    	}
    	return solutionList;
      // new UnsupportedOperationException("Not implemented yet!");
    }

    private boolean compareTwoWords(String word1, String word2){
    	//check if they are equal
    	if(word1.equals(word2)){
    		return false;
    	}
    	//check if their lengths are same
    	if(word1.length() != word2.length()){
    		return false;
    	}
    	boolean oneDifferenceFound = false;
    	char[] w1 = word1.toCharArray();
    	char[] w2 = word2.toCharArray();
    	//check if words' letters are different
    	for(int i = 0; i < w1.length; i++){
    		//if letters are different
    		if(w1[i] != w2[i]){
    			if(oneDifferenceFound){//this means there are more than one difference
    				return false;
    			}
    			else{
    				oneDifferenceFound = true;
    			}
    		}
    	}
    	return true;
    }
    @Override
    public boolean validateResult(String startWord, String endWord,List<String> wordLadder) 
    {
    	//return false if:
    	//1) wordLadder is empty
    	//2) first element of wordLadder is not startWord
    	//3) last element of wordLadder is not endWord
    	if(wordLadder.isEmpty() || !startWord.equals(wordLadder.get(0)) || !endWord.equals(wordLadder.get(wordLadder.size() - 1))){
    		return false;
    	}
    	//check for adjacent words i.e. 1st and 2nd, 2nd and 3rd
    	//return false if they are not different by one letter
    	if(wordLadder.size() > 1){
    		for(int i = 0; i < wordLadder.size() - 1; i++){
    			if(!compareTwoWords(wordLadder.get(i), wordLadder.get(i + 1))){
    				return false;
    			}
    		}
    	}
    	return true;
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

