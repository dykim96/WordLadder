package assignment4;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

public class Tests {
	private String[] args = {"A4words.dat"};
	@Test
	public void test1() {
		Assignment4Interface wordLadderSolver = new WordLadderSolver(args);
		try{
			List<String> result = wordLadderSolver.computeLadder("money", "honey");
			assertEquals(result.get(0), "money");
			assertEquals(result.get(result.size() - 1), "honey");
			boolean correct = wordLadderSolver.validateResult("money", "honey", result);
			assertTrue(correct);
		}
		catch(NoSuchLadderException e){
			
		}
	}
	
	@Test
	public void test2() {
		Assignment4Interface wordLadderSolver = new WordLadderSolver(args);
		try{
			List<String> result = wordLadderSolver.computeLadder("heads", "tails");
			assertEquals(result.get(0), "heads");
			assertEquals(result.get(result.size() - 1), "tails");
			boolean correct = wordLadderSolver.validateResult("heads", "tails", result);
			assertTrue(correct);
		}
		catch(NoSuchLadderException e){
			
		}
	}
	
	@Test
	public void test3(){
		Assignment4Interface wordLadderSolver = new WordLadderSolver(args);
		try{
			List<String> result = wordLadderSolver.computeLadder("drive", "hello");
			assertEquals(result.get(0), "drive");
			assertEquals(result.get(result.size() - 1), "hello");
			boolean correct = wordLadderSolver.validateResult("drive", "hello", result);
			assertTrue(correct);
		}
		catch(NoSuchLadderException e){
			
		}
	}
	
	@Test
	public void test4(){
		Assignment4Interface wordLadderSolver = new WordLadderSolver(args);
		boolean failed = false;
		try{
			List<String> result = wordLadderSolver.computeLadder("hello", "ryan");
		}
		catch(NoSuchLadderException e){
			failed = true;
		}
		assertTrue(failed);
	}
	
	@Test
	public void test5(){
		Assignment4Interface wordLadderSolver = new WordLadderSolver(args);
		boolean failed = false;
		try{
			List<String> result = wordLadderSolver.computeLadder("aargh", "zowie");
		}
		catch(NoSuchLadderException e){
			failed = true;
		}
		assertTrue(failed);
	}
	
	@Test
	public void test6() {
		Assignment4Interface wordLadderSolver = new WordLadderSolver(args);
		try{
			List<String> result = wordLadderSolver.computeLadder("money", "money");
			assertEquals(result.get(0), "money");
			assertEquals(result.get(result.size() - 1), "money");
			boolean correct = wordLadderSolver.validateResult("money", "money", result);
			assertTrue(correct);
		}
		catch(NoSuchLadderException e){
			
		}
	}
}
