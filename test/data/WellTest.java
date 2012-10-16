package data;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import blocks.IBlock;

import data.IWell;
import data.Well;


public class WellTest {
	protected IWell testObject;
	protected int height;
	protected int width;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		height =10;
		width = 6;
		testObject = new Well(height, width);
	}

	@Test
	public void testConstructor() {
		boolean expectingEmptyCell = true;
		boolean expectingNonemptyCell = false;
		for(int i=0; i < height; i++){
			for(int j=0; j < width; j++){
				// Check that all cells start out as empty
				assertEquals(expectingEmptyCell, testObject.isCellEmpty(i,j));
				assertEquals(expectingNonemptyCell, testObject.isCellFull(i, j));
			}
		}
	}
	
	@Test
	public void testCellGetMethods(){
		int length = height >= width? width: height;
		for(int i=0; i < length; i++){
			// Fill the cell
			testObject.fillCell(i, i);
			// Check if filled cell is "empty"
			boolean expectingEmptyCell = false;
			boolean isCellEmpty = testObject.isCellEmpty(i,i);
			assertEquals(expectingEmptyCell, isCellEmpty);
			// Check if the filled cell is "full"
			boolean expectingFullCell = true;
			boolean isCellFull = testObject.isCellFull(i, i);
			assertEquals(expectingFullCell, isCellFull);
		}
	}
	
	@Test
	public void testCellSetMethods(){
		for(int i=0; i < height; i++){
			for(int j=0; j < width; j++){
				// Fill an empty cell
				boolean cellFilled = testObject.fillCell(i, j);
				boolean expectedResult = true;
				assertEquals(expectedResult, cellFilled);
				boolean expectingCellFull = true;
				boolean isCellFull = testObject.isCellFull(i, j);
				assertEquals(expectingCellFull, isCellFull);
				
				// Fill a full cell
				cellFilled = testObject.fillCell(i,j);
				expectedResult = false;
				assertEquals(expectedResult, cellFilled);
				expectingCellFull = true;
				isCellFull = testObject.isCellFull(i,j);
				assertEquals(expectingCellFull, isCellFull);
				
				// Empty a full cell
				boolean cellEmptied = testObject.emptyCell(i, j);
				expectedResult = true;
				assertEquals(expectedResult, cellEmptied);
				expectingCellFull = false;
				isCellFull = testObject.isCellFull(i,j);
				assertEquals(expectingCellFull, isCellFull);
				
				// Empty an empty cell
				cellEmptied = testObject.emptyCell(i,j);
				expectedResult = false;
				assertEquals(expectedResult, cellEmptied);
				expectingCellFull = false;
				isCellFull = testObject.isCellFull(i,j);
				assertEquals(expectingCellFull, isCellFull);
			}
		}
	}
		
		@Test
		public void testDeletingARow(){
			
			for(int i=0; i < width; i++){
				testObject.fillCell(0,i);
				testObject.fillCell(height-1, i);	
			}
			testObject.clearRow(0);
			for(int i=0; i < height; i++){
				boolean expectingCellFilled = false;
				if(i==height-2)
					expectingCellFilled = true;
				for(int j=0; j < width; j++){
					boolean isCellFull = testObject.isCellFull(i, j);
					assertEquals(expectingCellFilled, isCellFull);
				}
			}
			boolean isRowDeleted = testObject.clearRow(2);
			boolean expectedResult = false;
			assertEquals(expectedResult, isRowDeleted);
		}
		
		@Test
		public void placeIllegalCells(){
			boolean[] actualResults = {
					testObject.fillCell(0,-1),
					testObject.fillCell(-1,0),
					testObject.fillCell(height+1, 0),
					testObject.fillCell(0, width+1)
			};
			for(int i=0; i < actualResults.length; i++){
				boolean expectedResult = false;
				assertEquals(expectedResult, actualResults[i]);
			}
		}
		
		@Test
		public void ToStringTest(){
			height = 5;
			width = 6;
			testObject = new Well(height, width);
			boolean[][] expected = new boolean[height][];
			for(int i=0; i < height; i++){
				expected[i] = new boolean[width];
				for(int j=0; j < i; j++)
					expected[i][j] = false;
				for(int j=i; j < width; j++){
					testObject.fillCell(i,j);
					expected[i][j] = true;
				}
			}
			String expectedMessage = "" +
				"false  false  false  false  true  true  \n" +
				"false  false  false  true  true  true  \n" +  
				"false  false  true  true  true  true  \n" +  
				"false  true  true  true  true  true  \n" +
				"true  true  true  true  true  true  \n";
			String actualMessage = testObject.toString();
			assertEquals(expectedMessage, actualMessage);
		}
		
		@Test
		public void testBoundChecking(){
			int placeholder = 1;
			int[][] illegalBounds = {{-1, height}, {-1, width}};
			int[][] legalBounds = {{0, height-1}, {0, width-1}};
			assertEquals("Both arrays should have the same number of rows", illegalBounds.length, legalBounds.length);
			
			// Test every combination that contains at least 1 illegal term
			for(int i=0; i < illegalBounds.length; i++){
				assertEquals("Row " + i + " of each array should have the same length",
						illegalBounds[i].length, legalBounds[i].length);
				for(int j=0; j < illegalBounds.length; j++){
					// Test isCellWithinBounds
					boolean expectedResult = false;
					boolean actualResult = testObject.isCellWithinBounds(illegalBounds[0][i], illegalBounds[1][j]);
					assertEquals(expectedResult, actualResult);
					actualResult = testObject.isCellWithinBounds(legalBounds[0][i], illegalBounds[1][j]);
					assertEquals(expectedResult, actualResult);
					actualResult = testObject.isCellWithinBounds(illegalBounds[0][i], legalBounds[1][j]);
					assertEquals("i= " + i + " j=" + j, expectedResult, actualResult);
					
					// Test isCellEmpty
					actualResult = testObject.isCellEmpty(illegalBounds[0][i], illegalBounds[0][j]);
					assertEquals(expectedResult, actualResult);
					actualResult = testObject.isCellEmpty(legalBounds[0][i], illegalBounds[0][j]);
					assertEquals(expectedResult, actualResult);
					actualResult = testObject.isCellEmpty(illegalBounds[0][i], legalBounds[0][j]);
					assertEquals(expectedResult, actualResult);
					
					// Test isCellFull
					actualResult = testObject.isCellFull(illegalBounds[0][i], illegalBounds[0][j]);
					assertEquals(expectedResult, actualResult);
					actualResult = testObject.isCellFull(illegalBounds[0][i], legalBounds[0][j]);
					assertEquals(expectedResult, actualResult);
					actualResult = testObject.isCellFull(legalBounds[0][i], illegalBounds[0][j]);
					assertEquals(expectedResult, actualResult);
				}
			}
			
			// Test within bounds
			for(int i=0; i < legalBounds.length; i++){
				for(int j=0; j < legalBounds[i].length; j++){
					boolean expectedResult = true;
					boolean actualResult;
					actualResult = testObject.isCellWithinBounds(legalBounds[0][i], legalBounds[1][j]);
					assertEquals(expectedResult, actualResult);
					
					testObject.fillCell(legalBounds[0][i], legalBounds[1][j]);
					actualResult = testObject.isCellFull(legalBounds[0][i], legalBounds[1][j]);
					assertEquals(expectedResult, actualResult);

					testObject.emptyCell(legalBounds[0][i], legalBounds[1][j]);
					actualResult = testObject.isCellEmpty(legalBounds[0][i], legalBounds[1][j]);
					assertEquals(expectedResult, actualResult);
				}
			}
		}
		
		
}

