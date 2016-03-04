/*  Assignment 4: Word Ladder
 *  Create word ladder by using dictionary
 *  Section: 16185
 *  Name: Doyoung Kim, Rohan Tanna
 *  UTEID: dk24338, rrt494
 */
package assignment4;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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
		BuildDictionary(inputDict);

	}
	
	public WordLadderSolver(ArrayList<String> dictionary){
		this.dictionary = dictionary;
	}
	
	private void BuildDictionary(String[] inputDict){
		dictionary = new ArrayList<String>();
		try 
		{
			FileReader freader = new FileReader(inputDict[0]);
			BufferedReader reader = new BufferedReader(freader);
			
			for (String s = reader.readLine(); s != null; s = reader.readLine()) 
			{
				//add if line is not comment
				if(!s.substring(0, 1).equals("*")){
					int i;
					for(i = 0; i < s.length(); i++){
						//if character is not letter then now i is at the end of word
						if(!Character.isLetter(s.charAt(i))){
							break;
						}
					}
					dictionary.add(s.substring(0, i));
				}
			}
			reader.close();
		} 
		catch (FileNotFoundException e) 
		{
			System.err.println ("Error: File not found. Exiting...");
			e.printStackTrace();
			System.exit(-1);
		} catch (IOException e) 
		{
			System.err.println ("Error: IO exception. Exiting...");
			e.printStackTrace();
			System.exit(-1);
		}
	}

    // do not change signature of the method implemented from the interface
    @Override
    public List<String> computeLadder(String startWord, String endWord) throws NoSuchLadderException 
    {
    	System.out.print("For the input words \"" + startWord + "\" and \"" + endWord + "\"");
        // implement this method
    	ArrayList<String> solutionList = new ArrayList<String>();

    	//if dictionary does not contain startword or endword return empty arraylist 
    	if(!dictionary.contains(startWord) || !dictionary.contains(endWord)){
    		System.out.println("\nAt least one of the words " + startWord + " and " + endWord + " are not legitimate 5-letter words from the dictionary");
    		return solutionList;
    	}
    	
    	solutionList.add(startWord);
    	//if startword is also endword just return wordladder
    	if(startWord.equals(endWord)){
    		System.out.println(" the following word ladder was found");
    		System.out.println(startWord);
    		return solutionList;
    	}
    	solutionList = MakeLadder(startWord, endWord, 5, solutionList); //initially pass in 5 because you haven't changed any. in the top most call, you want to change all 5 letters
    	
    	if (solutionList.size() == 0){
    		String message = "\nThere is no word ladder between " + startWord + " and " + endWord + "!";
    		throw new NoSuchLadderException(message);
    	}
    	else{
    		System.out.println(" the following word ladder was found");
    		for(int i = 0; i < solutionList.size(); i++){
    			System.out.print(" " + solutionList.get(i));
    		}
    		System.out.println("");
    	}
    	return solutionList;
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

