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
		public Point[] rotateCCW() {
			return null;
		}

		@Override
		public Point[] rotateCW() {
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
			testContext.fillCell(i, 1);
			testContext.fillCell(i, width-2);
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
	public void testMovingLShape(){
		boolean[] expected = {true, false};
		initializeLeftWell();
		testObject = createLShape();
		testMovingShapeLeftByX(expected);
	}
	
	@Test
	public void testMovingGammaShape(){
		boolean[] expected = {true, true, false};
		initializeLeftWell();
		testObject = createGammaShape();
		testMovingShapeLeftByX(expected);
	}
	
	@Test
	public void testMoving2x1Shape(){
		boolean[] expected = {true, true, false};
		initializeLeftWell();
		testObject = create2x1();
		testMovingShapeLeftByX(expected);
	}
	
	protected void initializeLeftWell(){
		// Setup Well
		testContext = new Well(5,4);
		testContext.fillCell(1,0);
		testContext.fillCell(2,1);
		testContext.fillCell(0, 0);
		testContext.fillCell(0, 1);
	}
	
	protected void testMovingShapeLeftByX(boolean[] expectedResults){
		Point[] originalPoints, newPoints;
		System.err.println(expectedResults.length);
		for(int i=0; i < expectedResults.length; i++){
			
			originalPoints = copyArray(testObject.getPoints());
			boolean actualReturnValue = testObject.moveLeft();
			assertEquals(expectedResults[i], actualReturnValue);
			
			newPoints = copyArray(testObject.getPoints());
			assertEquals(originalPoints.length, newPoints.length);
			
			System.err.println("i="+i);
			int dec;
			if(expectedResults[i]==true){
				dec =1;
			}
			else dec=0;
			
			for(int j=0; j < newPoints.length; j++){
				System.err.println("j="+j + " " + expectedResults[i]);
				assertEquals(originalPoints[j].x - dec, newPoints[j].x);
				assertEquals(originalPoints[j].y, newPoints[j].y);
			}
		
		}
	}
	
	protected Point[] copyArray(Point[] src){
		Point[] dest = new Point[src.length];
		for(int i=0; i < src.length; i++){
			dest[i] = new Point(src[i].x, src[i].y);
		}
		return dest;
	}
	
	protected IBlock createLShape(){
		Point[] lShape = {
				new Point(3,3),
				new Point(2,3),
				new Point(3,1),
				new Point(3,2)
		};
		IBlock lShapeBlock = new BlockTestClass(lShape, testContext);
		return lShapeBlock;
	}
	
	protected IBlock createGammaShape(){
		Point[] gammaShape = {
			new Point(3,4),
			new Point(2,4),
			new Point(3,3)
		};
		IBlock gammaBlock = new BlockTestClass(gammaShape, testContext);
		return gammaBlock;
	}
	
	protected IBlock create2x1(){
		Point[] twobyone = { new Point(4,4), new Point(3,4)};
		return new BlockTestClass(twobyone, testContext);
	}
	
	@Test
	public void testMovingLeftByOne(){
		
		// Initialize Expected Points
		Point[] expectedPoints = testObject.getPoints();
		for(int i=0; i < expectedPoints.length; i++){
			expectedPoints[i].x--;
		}
		
		// Verify Return Values
		boolean expectedReturnValue = true;
		boolean actualReturnValue = testObject.moveLeft();
		assertEquals(expectedReturnValue, actualReturnValue);
		
		// Verify new points
		Point[] actualPoints = testObject.getPoints();
		assertEquals(expectedPoints.length, actualPoints.length);
		for(int i=0; i < actualPoints.length; i++){
			assertEquals(expectedPoints[i].x, actualPoints[i].x);
			assertEquals(expectedPoints[i].y, actualPoints[i].y);
		}
	}
	
	
}
