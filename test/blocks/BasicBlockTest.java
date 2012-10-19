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
	
	public Point[] getTestBlocks(){
		Point[] testBlocks = new Point[4];
		testBlocks[0] = new Point(height-2, width/2);
		testBlocks[1] = new Point(height-2, width/2 +1);
		testBlocks[2] = new Point(height-2, width/2 -1);
		testBlocks[3] = new Point(height-1, width/2 -1);
		return testBlocks;
	}
	
	@Test
	public void testGetPoints(){
		Point[] testBlocks = getTestBlocks();
		Point[] actualResults = copyArray(testObject.getPoints());
		
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
	//	System.err.println(expectedResults.length);
		for(int i=0; i < expectedResults.length; i++){
			
			originalPoints = copyArray(testObject.getPoints());
			boolean actualReturnValue = testObject.moveLeft();
			assertEquals(expectedResults[i], actualReturnValue);
			
			newPoints = copyArray(testObject.getPoints());
			assertEquals(originalPoints.length, newPoints.length);
			
			//System.err.println("i="+i);
			int dec;
			if(expectedResults[i]==true){
				dec =1;
			}
			else dec=0;
			
			for(int j=0; j < newPoints.length; j++){
				//System.err.println("j="+j + " " + expectedResults[i]);
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
		Point[] twobyone = { new Point(2,4), new Point(3,4)};
		return new BlockTestClass(twobyone, testContext);
	}
	
	@Test
	public void testMove(){
		testContext = createDonutWell();
		testObject = createSquare();
		int[] xMoves = {-2, -1, 0, 1, 2};
		int[] yMoves = {-2, -1, 0, 1, 2};
		
		System.out.println(testContext);
		// For each (dx, dy) pair, test whether the square can move inside the donut well
		for(int i=0; i < xMoves.length; i++){
			System.err.println(i);
			for(int j=0; j < yMoves.length; j++){
				boolean isLegalMove = xMoves[i] < 2 && xMoves[i] > -2;
				isLegalMove = isLegalMove && (yMoves[j] < 2 && yMoves[j] > -2);
				
				// Setup Expected Results
				Point[] expectedPoints = testObject.getPoints();
				for(int k=0; k < expectedPoints.length; k++){
					// Adjust expected x coordinate
					if(isLegalMove){
						expectedPoints[k].setX(expectedPoints[k].x + xMoves[i]);
						expectedPoints[k].setY(expectedPoints[k].y + yMoves[j]);
					}
				}
				
				// Verify the expected results
				testObject.move(xMoves[i], yMoves[j]);
				Point[] actualResults = testObject.getPoints();
				assertEquals(expectedPoints.length, actualResults.length);
				assertNotSame(expectedPoints, actualResults);
				for(int k=0; k < actualResults.length; k++){
					System.err.println("j"+j+"k"+k);
					System.err.println(expectedPoints[k]);
					System.err.println(actualResults[k]);
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
		Point[] points = new Point[4];
		points[0] = new Point(2,2);
		points[1] = new Point(3,3);
		points[2] = new Point(2,3);
		points[3] = new Point(3,2);
		IBlock block = new BlockTestClass(points, testContext);
		return block;
	}
}
