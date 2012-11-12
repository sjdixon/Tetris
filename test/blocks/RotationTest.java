package blocks;

import static org.junit.Assert.*;

import org.junit.Before;
import data.IWell;
import data.Well;
import blocks.*;

import org.junit.Test;

public class RotationTest {
	protected IWell testContext;
	protected IBlock testObject;
	protected RotationAdapter adapter;
	protected final int HEIGHT = 20, WIDTH = 10;
	protected IBlock[] rotations;
	
	@Before
	public void setup(){
		testContext = new Well(HEIGHT, WIDTH);
		adapter = new RotationAdapter(testContext);
		rotations = null;
	}
	
	public void createRotations(){
		rotations = new IBlock[16];
		testObject.moveDown();
		rotations[0] = adapter.rotateRight(testObject);
		int i;
		for(i=1; i < 4; i++)
			rotations[i] = adapter.rotateRight(rotations[i-1]);
		for(i=4; i < 8; i++)
			rotations[i] = adapter.rotateLeft(rotations[i-1]);
		
		// Setup for next position
		testObject.drop();
		testObject.moveUp();
		rotations[8] = adapter.rotateRight(testObject);
		for(i=9; i < 12; i++){
			rotations[i] = adapter.rotateRight(rotations[i-1]);
		}
		for(i=12; i < 16; i++){
			rotations[i] = adapter.rotateLeft(rotations[i-1]);
		}
	}
	
	public void testRotations(){
		int[] expectedMatches = initializeExpectedMatches();
		for(int i=0; i < expectedMatches.length; i++){
			boolean expectedValue, actualValue;
			for(int j=i+1; j < rotations.length; j++){
				if(j==expectedMatches[i])
					expectedValue = true;
				else expectedValue = false;
				actualValue = adapter.areBlocksEqual(rotations[i], rotations[j]);
			}
		}
		testRotationValue();
	}
	
	public void testRotationValue(){
		int[] expectedValues = new int[16];
		System.err.println(testObject.getClass());
		for(int i=0; i < 4; i++){
			expectedValues[i] = (3-i)%testObject.numRotations();
			expectedValues[i+4] = (i+1)%testObject.numRotations();
			expectedValues[i+8] = expectedValues[i];
			expectedValues[i+12] = expectedValues[i+4];
		}
		int[] actualValues = new int[16];
		for(int i=0; i < actualValues.length; i++){
			actualValues[i] = rotations[i].getRotation();
			System.err.println( "expected:"+expectedValues[i] + " actual:"+actualValues[i]);
			assertEquals(expectedValues[i], actualValues[i]);
		}
	}
	
	public int[] initializeExpectedMatches(){
		int[] matches = new int[16];
		// The first set
		matches[0] = 6;
		matches[1] = 5;
		matches[2] = 4;
		matches[3] = 7;
		for(int i=0; i < 4; i++){
			int partner = matches[i];
			matches[partner] = i;
		}
		for(int i=8; i < 16; i++){
			matches[i] = matches[i-8];
		}
		return matches;
	}
	@Test
	public void testRotate_T(){
		testObject = new T_Block(testContext);
		createRotations();
		testRotations();
		
	}
	@Test
	public void testRotate_L(){
		testObject = new L_Block(testContext);
		createRotations();
		testRotations();
		
	}
	@Test
	public void testRotate_Square(){
		testObject = new Square(testContext);
		createRotations();
		testRotations();
	}
	@Test
	public void testRotate_S(){
		testObject = new S_Block(testContext);
		createRotations();
		testRotations();
	}
	@Test
	public void testRotate_Z(){
		testObject = new Z_Block(testContext);
		createRotations();
		testRotations();
	}
	@Test
	public void testRotate_J(){
		testObject = new J_Block(testContext);
		createRotations();
		testRotations();
	}
	@Test
	public void testRotate_I(){
		testObject = new I_Shaped_Block(testContext);
		createRotations();
		testRotations();
	}

	@Test
	public void testJBlock_Sequence(){
		IBlock jBlock = new J_Block(testContext);
		jBlock.drop();
		RotationAdapter adapter = new RotationAdapter(testContext);
		jBlock = adapter.rotateLeft(jBlock);
		// First test that the jblock landed
		Cell[] expectedCells = {
				new Cell(5,1),
				new Cell(5,2),
				new Cell(6,2),
				new Cell(5,0)
		};
		for(int i=0; i < expectedCells.length; i++){
			System.err.println(jBlock.getCells()[i]+","+expectedCells[i]);
			assertEquals(expectedCells[i], jBlock.getCells()[i]);
		}
	}
	
	@Test
	public void testRotationValueUnit(){
		// Setup
		IBlock b = new Square(testContext); // it doesn't really matter which block it is
		RotationAdapter adapter = new RotationAdapter(testContext);
		int[] inputs = {0,1,2,3};
		int[] expectedLeftOutputs = {1,2,3,0};
		int[] expectedRightOutputs = {3,2,1,0};
		
		int[] actualLeftOutputs = new int[inputs.length];
		int[] actualRightOutputs = new int[inputs.length];
		
		// Verify and assert
		for(int i=0; i < inputs.length; i++){
			b.setRotation(inputs[i]);
			actualLeftOutputs[i] = adapter.nextRotation(b, false);
			assertEquals(expectedLeftOutputs[i], actualLeftOutputs[i]);
			actualRightOutputs[i] = adapter.nextRotation(b, true);
			assertEquals(expectedRightOutputs[i], actualRightOutputs[i]);
		}
	}
}
