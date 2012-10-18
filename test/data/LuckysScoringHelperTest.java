package data;
import static org.junit.Assert.*;


import org.junit.Before;
import org.junit.Test;

import data.LuckysScoringHelper;
import data.Well;
import data.IWell;
import data.IScoreHelper;


public class LuckysScoringHelperTest {

	protected IWell testContext;
	protected IScoreHelper testObject;
	protected int height = 10;
	protected int width = 8;
	
	@Before
	public void setUp() throws Exception {
		testContext = new Well(height, width);
		assertTrue(height >= width);
		for(int i=0; i < width; i++){
			testContext.fillCell(i, i);
			if(i>0)
				testContext.fillCell(i-1, i);
		}
		testObject = new LuckysScoringHelper(testContext);
	}
	
	@Test
	public void testHeight(){
		int[] expectedHeights = new int[width];
		int[] actualHeights = new int[width];
		assertTrue(height >= width);
		for(int i=0; i < width; i++){
			actualHeights[i] = testObject.calculateHeightOfColumn(i);
			expectedHeights[i] = i+1;
			assertEquals(expectedHeights[i], actualHeights[i]);
		}
	}
	
	@Test
	public void testCountingHoles() {
		// Setup Test - we will use a special well specifically designed for counting holes
		boolean[][] specialContext = {
				{false, true, true, false, false}, // first row
				{true, false, true, false, false}, // second row
				{false, true, true, true, false}, // third row
				{true, true, true, true, false}  // fourth row
		};
		testContext = new Well(specialContext.length+1, specialContext[0].length);
		for(int i=0; i < specialContext.length; i++){
			for(int j=0; j < testContext.getWidth(); j++){
				if(specialContext[i][j]==true)
					testContext.fillCell(i, j);
			}
		}
		testObject = new LuckysScoringHelper(testContext);
		
		//Initialize the expected results
		int[] expectedHoles = {
				2, 1, 0, 2, 4
		};
		assertEquals(expectedHoles.length, testContext.getWidth());
		
		// Verify the expected results
		int r = testContext.getHeight()-1;
		int[] actualHoles = new int[expectedHoles.length];
		for(int i=0; i < actualHoles.length; i++){
			actualHoles[i] = testObject.countHolesBelowCell(r, i);
			assertEquals(expectedHoles[i], actualHoles[i]);
		}
	}
	
	@Test
	public void testCountingBlockades(){
		// Setup Test - we will use a special well specifically designed for counting holes
		boolean[][] specialContext = {
				{false, true, true, false, false}, // first row
				{true, false, true, false, false}, // second row
				{false, true, true, true, false}, // third row
				{true, true, true, true, false}  // fourth row
		};
		testContext = new Well(specialContext.length+1, specialContext[0].length);
		for(int i=0; i < specialContext.length; i++){
			for(int j=0; j < testContext.getWidth(); j++){
				if(specialContext[i][j]==true)
					testContext.fillCell(i, j);
			}
		}
		testObject = new LuckysScoringHelper(testContext);
		
		//Initialize the expected results
		int[] expectedResults = {
			3,3,0,3,1
		};
		assertEquals(expectedResults.length, testContext.getWidth());
		
		// Verify the expected results
		int r = testContext.getHeight()-1;
		int[] actualBlockades = new int[expectedResults.length];
		for(int i=0; i < actualBlockades.length; i++){
			actualBlockades[i] = testObject.countBlockades(r, i);
			assertEquals(expectedResults[i], actualBlockades[i]);
		}
	}
	
	@Test
	public void testCheckingFloor(){
		boolean[] actualResults = {
				testObject.isCellAdjacentToFloor(0, 0),
				testObject.isCellAdjacentToFloor(0, width-1),
				testObject.isCellAdjacentToFloor(height-1,0),
				testObject.isCellAdjacentToFloor(height-1,width-1),
				testObject.isCellAdjacentToFloor(1, 0),
				testObject.isCellAdjacentToFloor(0, width-2),
				testObject.isCellAdjacentToFloor(height-2,1),
				testObject.isCellAdjacentToFloor(height-2,width-2),
		};
		boolean[] expectedResults = {
				true, true, false, false, false, true, false, false
		};
		assertEquals(expectedResults.length, actualResults.length);
		for(int i=0; i < expectedResults.length; i++)
			assertEquals(expectedResults[i], actualResults[i]);
	}

	@Test
	public void testCheckingWall(){
		assertTrue(height >= 3);
		assertTrue(width >=3);
		boolean[] actualResults = {
				testObject.isCellAdjacentToWall(0, 0),
				testObject.isCellAdjacentToWall(0, width-1),
				testObject.isCellAdjacentToWall(height-1,0),
				testObject.isCellAdjacentToWall(height-1, width-1),
				testObject.isCellAdjacentToWall(1, 1),
				testObject.isCellAdjacentToWall(1, width-2),
				testObject.isCellAdjacentToWall(height-1,1),
				testObject.isCellAdjacentToWall(height-2, width-2),
		};
		boolean[] expectedResults = {
				true, true, true, true, false, false, false, false
		};
		assertEquals(expectedResults.length, actualResults.length);
		for(int i=0; i < expectedResults.length; i++)
			assertEquals(expectedResults[i], actualResults[i]);
	}
}
