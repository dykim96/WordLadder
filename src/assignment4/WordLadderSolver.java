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
	private ArrayList<String> dictionary;
    private ArrayList<Boolean> alreadyWentThrough;
	
	public WordLadderSolver(String[] inputDict){
		
		 //insert code to parse the input dictionary, and set dictionary equal to this 
		BuildDictionary(inputDict);

	}
	
	public WordLadderSolver(ArrayList<String> dictionary){
		this.dictionary = dictionary;
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
    		String message = "\nAt least one of the words " + startWord + " and " + endWord + " are not legitimate 5-letter words from the dictionary";
    		throw new NoSuchLadderException(message + "\n**********");
    	}

    	//if startword is also endword just return wordladder
    	if(startWord.equals(endWord)){
        	solutionList.add(startWord);
    		System.out.println(" the following word ladder was found");
    		System.out.println(startWord);
    		System.out.println("**********");
    		return solutionList;
    	}
    	ResetBooleans();
    	solutionList = MakeLadder(startWord.toLowerCase(), endWord.toLowerCase(), 5);
    	
    	if (solutionList.size() == 1){
    		String message = "\nThere is no word ladder between " + startWord + " and " + endWord + "!";
    		throw new NoSuchLadderException(message + "\n**********");
    	}
    	else{
    		System.out.println(" the following word ladder was found");
    		for(int i = 0; i < solutionList.size(); i++){
    			System.out.print(" " + solutionList.get(i));
    		}
    		System.out.println("\n**********");
    	}
    	return solutionList;
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
					dictionary.add(s.substring(0, i).toLowerCase());
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
    
    private void ResetBooleans(){
    	alreadyWentThrough = new ArrayList<Boolean>();
    	for(int i = 0; i < dictionary.size(); i++){
    		alreadyWentThrough.add(false);
    	}
    }
    
    private int GetNextWordIndex(String currentWord, String endWord, int index){
    	int foundIndex = -1;
    	if(index != 0){
    		foundIndex = dictionary.indexOf(endWord.substring(0, 1) + currentWord.substring(1));
    		if(foundIndex > -1 && !alreadyWentThrough.get(foundIndex)){
    			alreadyWentThrough.set(foundIndex, true);
    			return foundIndex;
    		}
    		foundIndex = -1;
    	}
    	if(index != 1){
    		foundIndex = dictionary.indexOf(currentWord.substring(0, 1) + endWord.substring(1, 2) + currentWord.substring(2));
    		if(foundIndex > -1 && !alreadyWentThrough.get(foundIndex)){
    			alreadyWentThrough.set(foundIndex, true);
    			return foundIndex;
    		}
    		foundIndex = -1;
    	}
    	if(index != 2){
    		foundIndex = dictionary.indexOf(currentWord.substring(0, 2) + endWord.substring(2, 3) + currentWord.substring(3));
    		if(foundIndex > -1 && !alreadyWentThrough.get(foundIndex)){
    			alreadyWentThrough.set(foundIndex, true);
    			return foundIndex;
    		}
    		foundIndex = -1;
    	}
    	if(index != 3){
    		foundIndex = dictionary.indexOf(currentWord.substring(0, 3) + endWord.substring(3, 4) + currentWord.substring(4));
    		if(foundIndex > -1 && !alreadyWentThrough.get(foundIndex)){
    			alreadyWentThrough.set(foundIndex, true);
    			return foundIndex;
    		}
    		foundIndex = -1;
    	}
    	if(index != 4){
    		foundIndex = dictionary.indexOf(currentWord.substring(0, 4) + endWord.substring(4));
    		if(foundIndex > -1 && !alreadyWentThrough.get(foundIndex)){
    			alreadyWentThrough.set(foundIndex, true);
    			return foundIndex;
    		}
    		foundIndex = -1;
    	}
    	for(int i = 0; i < dictionary.size(); i++){
    		if(compareTwoWords(currentWord, dictionary.get(i)) && !alreadyWentThrough.get(i)){
    			foundIndex = i;
    			alreadyWentThrough.set(foundIndex, true);
    			break;
    		}
    		foundIndex = -1;
    	}
    	return foundIndex;
    }
    
    private int FindDifferentIndex(String word1, String word2){
    	for(int i = 0; i < word1.length(); i++){
    		if(word1.charAt(i) != word2.charAt(i))
    			return i;
    	}
    	return -1;
    }
    
    public ArrayList<String> MakeLadder(String startWord, String endWord, int index){
    	ArrayList<String> solution = new ArrayList<String>();
    	solution.add(startWord);
    	//check if current word and end word has one letter difference
    	if(compareTwoWords(startWord, endWord)){
    		solution.add(endWord);
    	}
    	else{
    		ArrayList<String> adjacentWords = new ArrayList<String>();
    		//get all adjacent words
    		int nextWordIndex = -1;
			nextWordIndex = GetNextWordIndex(startWord, endWord, index);
			while(nextWordIndex > -1){
    			adjacentWords.add(dictionary.get(nextWordIndex));
    			nextWordIndex = GetNextWordIndex(startWord, endWord, index);
    		}
    		//try all adjacent words
    		for(int i = 0; i < adjacentWords.size(); i++){
    			//recursively look for the path
    			ArrayList<String> possibleSolution = MakeLadder(adjacentWords.get(i), endWord, FindDifferentIndex(startWord, adjacentWords.get(i)));
    			//validate path by checking whether it contains endWord
    			if(possibleSolution.contains(endWord)){
    				solution.addAll(possibleSolution);
    				break;
    			}
    		}
    	}
    	return solution;
    }
    
}

