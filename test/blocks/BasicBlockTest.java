package blocks;
import static org.junit.Assert.*;
import data.IWell;
import data.Well;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import blocks.Cell;

public class BasicBlockTest  {
	
	// This test can be improved by replacing the following inner class with a mockery
	protected class BlockTestClass extends BasicBlock{
		public BlockTestClass(Cell[] points, IWell well){
			setPoints(points);
			setWellReference(well);
		}
		@Override
		public Cell[] rotateLeft() {
			return null;
		}

		@Override
		public Cell[] rotateRight() {
			return null;
		}
	}
	
	protected IWell testContext;
	protected IBlock testObject;
	protected final int height = 20;
	protected final int width = 10;
	
	@Before
	public void setUp(){
		
		// Initialize the Well
		int numCells = width + height*2 - 2;
		testContext = new Well(height, width);
		
		// Fill the well
		for(int i=0; i < width; i++){
			testContext.fillCell(0, i);
		}
		for(int i=1; i < height; i++){
			testContext.fillCell(i, 1);
			testContext.fillCell(i, width-2);
		}
		
		// Initialize the blocks
		Cell[] testBlocks = getTestBlocks();
		testObject = new BlockTestClass(testBlocks, testContext);
		
		// Check assumptions
		assertTrue(1 < width/2 -2);
		assertTrue(width-2 > width/2 +2);
		assertTrue(height > 2);
	}
	
	public Cell[] getTestBlocks(){
		Cell[] testBlocks = new Cell[4];
		testBlocks[0] = new Cell(height-2, width/2);
		testBlocks[1] = new Cell(height-2, width/2 +1);
		testBlocks[2] = new Cell(height-2, width/2 -1);
		testBlocks[3] = new Cell(height-1, width/2 -1);
		return testBlocks;
	}
	
	@Test
	public void testGetPoints(){
		Cell[] testBlocks = getTestBlocks();
		Cell[] actualResults = copyArray(testObject.getCells());
		
		assertEquals(testBlocks.length, actualResults.length);
		for(int i=0; i < actualResults.length; i++){
			assertEquals(testBlocks[i].getRow(), actualResults[i].getRow());
			assertEquals(testBlocks[i].getColumn(), actualResults[i].getColumn());
		}
	}
	
	
	@Test
	public void testMovingLShape(){
		boolean[] expected = {true, false};
		//System.err.println("L");
		initializeLeftWell();
		testObject = createLShape();
		testMovingShapeLeft(expected);
	}
	
	@Test
	public void testMovingGammaShape(){
		boolean[] expected = {true, true, false};
		initializeLeftWell();
		//System.err.println("Gamma");
		testObject = createGammaShape();
		testMovingShapeLeft(expected);
	}
	
	@Test
	public void testMoving2x1Shape(){
		boolean[] expected = {true, true, false};
		initializeLeftWell();
		//System.err.println("2x1");
		testObject = create2x1();
		testMovingShapeLeft(expected);
	}
	
	protected void initializeLeftWell(){
		// Setup Well
		testContext = new Well(5,4);
		testContext.fillCell(1,0);
		testContext.fillCell(2,1);
		testContext.fillCell(0, 0);
		testContext.fillCell(0, 1);
	}
	
	protected void testMovingShapeLeft(boolean[] expectedResults){
		Cell[] originalPoints, newPoints;
	//	System.err.println(expectedResults.length);
		for(int i=0; i < expectedResults.length; i++){
			
			originalPoints = copyArray(testObject.getCells());
			boolean actualReturnValue = testObject.moveLeft();
			assertEquals(expectedResults[i], actualReturnValue);
			
			newPoints = copyArray(testObject.getCells());
			assertEquals(originalPoints.length, newPoints.length);
			
			int dec;
			if(expectedResults[i]==true){
				dec =1;
			}
			else dec=0;
			
			for(int j=0; j < newPoints.length; j++){
				//System.err.println("j="+j + " " + expectedResults[i]);
				assertEquals(originalPoints[j].row, newPoints[j].row);
				assertEquals(originalPoints[j].column - dec, newPoints[j].column);
			}
		
		}
	}
	
	protected Cell[] copyArray(Cell[] src){
		Cell[] dest = new Cell[src.length];
		for(int i=0; i < src.length; i++){
			dest[i] = new Cell(src[i].column, src[i].row);
		}
		return dest;
	}
	
	protected IBlock createLShape(){
		Cell[] lShape = {
				new Cell(3,3),
				new Cell(2,3),
				new Cell(1,3),
				new Cell(1,2)
		};
		IBlock lShapeBlock = new BlockTestClass(lShape, testContext);
		return lShapeBlock;
	}
	
	protected IBlock createGammaShape(){
		Cell[] gammaShape = {
			new Cell(3,4),
			new Cell(2,4),
			new Cell(3,3)
		};
		IBlock gammaBlock = new BlockTestClass(gammaShape, testContext);
		return gammaBlock;
	}
	
	protected IBlock create2x1(){
		Cell[] twobyone = { new Cell(2,4), new Cell(3,4)};
		return new BlockTestClass(twobyone, testContext);
	}
	
	@Test
	public void testMove(){
		testContext = createDonutWell();
		int[] xMoves = {-2, -1, 0, 1, 2};
		int[] yMoves = {-2, -1, 0, 1, 2};
		
		//System.out.println(testContext);
		// For each (dx, dy) pair, test whether the square can move inside the donut well
		for(int i=0; i < xMoves.length; i++){
			for(int j=0; j < yMoves.length; j++){
				testObject = createSquare();
				boolean isLegalMove = (xMoves[i] < 2 && xMoves[i] > -2)
						&& (yMoves[j] < 2 && yMoves[j] > -2);
				
				// Setup Expected Results
				Cell[] expectedPoints = testObject.getCells();
				for(int k=0; k < expectedPoints.length; k++){
					// Adjust expected x coordinate
					if(isLegalMove){
						expectedPoints[k].setRow(expectedPoints[k].row + yMoves[j]);
						expectedPoints[k].setColumn(expectedPoints[k].column + xMoves[i]);
					}
				}
				
				// Verify the expected results
				boolean wasLegalMove = testObject.move(xMoves[i], yMoves[j]);
				Cell[] actualResults = testObject.getCells();
				assertEquals(isLegalMove, wasLegalMove);
				assertEquals(expectedPoints.length, actualResults.length);
				assertNotSame(expectedPoints, actualResults);
				//System.err.println("i "+xMoves[i]+" j "+yMoves[j]);
				for(int k=0; k < actualResults.length; k++){
					//System.err.println(expectedPoints[k] + "=" + actualResults[k]);
					assertTrue(actualResults[k].equals(expectedPoints[k]));
					assertNotSame(actualResults[k], expectedPoints[k]);
				}
			}
		}
		
	}
	
	protected IWell createDonutWell(){
		int length = 6;
		IWell well = new Well(length,length);
		for(int i=0; i < length; i++){
			well.fillCell(i, 0);
			well.fillCell(0, i);
			well.fillCell(length-1, i);
			well.fillCell(i, length-1);
		}
		return well;
	}
	
	protected IBlock createSquare(){
		Cell[] points = new Cell[4];
		points[0] = new Cell(2,2);
		points[1] = new Cell(3,3);
		points[2] = new Cell(2,3);
		points[3] = new Cell(3,2);
		IBlock block = new BlockTestClass(points, testContext);
		return block;
	}
	

	@Test
	public void testMoveEachDirection(){
		// Setup
		testContext = createDonutWell();
		testObject = createSquare();
		Cell[] expected = testObject.getCells();
		boolean expectedMoveResult = true;
		boolean moved;
		
		// Exercise
		moved = testObject.moveRight();
		assertEquals(expectedMoveResult, moved);
		moved = testObject.moveUp();
		assertEquals(expectedMoveResult, moved);
		moved = testObject.moveDown();
		assertEquals(expectedMoveResult, moved);
		moved = testObject.moveLeft();
		assertEquals(expectedMoveResult, moved);
		
		// Verify
		Cell[] actual = testObject.getCells();
		assertEquals(expected.length, actual.length);
		assertNotSame(expected, actual);
		for(int i=0; i < expected.length; i++){
			assertTrue(expected[i].equals(actual[i]));
			assertNotSame(expected[i], actual[i]);
		}
	}
}
