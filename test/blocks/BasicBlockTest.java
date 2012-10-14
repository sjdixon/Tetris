package blocks;
import static org.junit.Assert.*;
import data.IWell;
import data.Well;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import blocks.Point;

public class BasicBlockTest  {
	
	// This test can be improved by replacing the following inner class with a mockery
	protected class BlockTestClass extends BasicBlock{
		public BlockTestClass(Point[] points, IWell well){
			setPoints(points);
			setWellReference(well);
		}
		@Override
		public Point[] rotateLeft() {
			return null;
		}

		@Override
		public Point[] rotateRight() {
			return null;
		}
	}
	
	protected IWell testContext;
	protected IBlock testObject;
	protected final int height = 12;
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
			testContext.fillCell(1, i);
			testContext.fillCell(width-2, i);
		}
		
		// Initialize the blocks
		Point[] testBlocks = new Point[4];
		testBlocks[0] = new Point(height-2, width/2);
		testBlocks[1] = new Point(height-2, width/2 +1);
		testBlocks[2] = new Point(height-2, width/2 -1);
		testBlocks[3] = new Point(height-1, width/2 -1);
		testObject = new BlockTestClass(testBlocks, testContext);
		
		// Check assumptions
		assertTrue(1 < width/2 -2);
		assertTrue(width-2 > width/2 +2);
		assertTrue(height > 2);
	}
	
	@Test
	public void testGetPoints(){
		Point[] testBlocks = new Point[4];
		testBlocks[0] = new Point(height-2, width/2);
		testBlocks[1] = new Point(height-2, width/2 +1);
		testBlocks[2] = new Point(height-2, width/2 -1);
		testBlocks[3] = new Point(height-1, width/2 -1);
		Point[] actualResults = testObject.getPoints();
		
		assertEquals(testBlocks.length, actualResults.length);
		for(int i=0; i < actualResults.length; i++){
			assertEquals(testBlocks[i].getX(), actualResults[i].getX());
			assertEquals(testBlocks[i].getY(), actualResults[i].getY());
		}
	}
	
	@Test
	public void testMoveLeft(){
		testMovingLeftByOne();
		testMovingLeftThroughObstacles();
		testMovingLeftThroughWalls();
	}
	
	protected void testMovingLeftThroughWalls(){
		int moveDistance = width/2 +1;
		boolean expectedResult = false;
		boolean actualResult = testObject.moveLeft(moveDistance);
		assertEquals(expectedResult, actualResult);
	}
	protected void testMovingLeftThroughObstacles(){
		int moveDistance = testObject.getPoints()[2].x - 1;
		boolean expectedResult = false;
		boolean actualResult = testObject.moveLeft(moveDistance);
		assertEquals(expectedResult, actualResult);
	}
	protected void testMovingLeftByOne(){
		int moveDistance = 1;
		boolean expectedResult = true;
		boolean actualResult = testObject.moveLeft(moveDistance);
		assertEquals(expectedResult, actualResult);
	}
	
	
}
