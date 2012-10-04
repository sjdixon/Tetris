import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import data.IBlock;
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
		height =5;
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
			testObject.deleteRow(0);
			for(int i=0; i < height; i++){
				boolean expectingCellFilled = false;
				if(i==height-2)
					expectingCellFilled = true;
				for(int j=0; j < width; j++){
					boolean isCellFull = testObject.isCellFull(i, j);
					assertEquals(expectingCellFilled, isCellFull);
				}
			}
			boolean isRowDeleted = testObject.deleteRow(2);
			boolean expectedResult = false;
			assertEquals(expectedResult, isRowDeleted);
		}
		
		@Test
		public void testBlockFit(){
			fail("Not implemented.");
		}
		
}

