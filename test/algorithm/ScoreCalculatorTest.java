package algorithm;

import static org.junit.Assert.*;


import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import blocks.*;
import algorithms.ICalculator;
import algorithms.ScoreCalculator;
import data.Chromosome;
import data.IChromosome;
import data.Well;
import data.IWell;

public class ScoreCalculatorTest {

	protected ICalculator testObject;
	protected double[] coefficients;
	protected IWell well;
	protected IChromosome testChrom;
	protected final int WIDTH = 10;
	protected final int HEIGHT = 20;
	protected final double DELTA = 0.0001;
	
	@Before
	public void setUp(){
		well = new Well(HEIGHT, WIDTH);
		
		Random gen = new Random();
		coefficients = new double[IChromosome.NUM_COEFFICIENTS];
		for(int i=0; i < coefficients.length; i++){
			coefficients[i] = gen.nextGaussian();
		}
		testChrom = new Chromosome(coefficients);
		testObject = new ScoreCalculator(well, testChrom);
	}
	
	@Test
	public void testFloorBonus() {
		IBlock square = new Square(this.well);
		square.drop();
		double result = testObject.calculateFloorBonus(square); 
		double expectedResult = 2 * testChrom.getFloorCoefficient();
		assertEquals(expectedResult, result, DELTA);
		
		square.moveUp();
		result = testObject.calculateFloorBonus(square);
		expectedResult = 0;
		assertEquals(expectedResult, result, DELTA);
	}
	@Test
	public void testFloorSpecialCase(){
		IBlock square = new Square(this.well);
		well.fillCell(0,5);
		well.fillCell(0,4);
		square.drop();
		double result = testObject.calculateFloorBonus(square);
		double expectedResult = 2 * testChrom.getFloorCoefficient();
		assertEquals(expectedResult, result, DELTA);
		
		square.moveLeft();
		result = testObject.calculateFloorBonus(square);
		expectedResult = 1 * testChrom.getFloorCoefficient();
		assertEquals(expectedResult, result, DELTA);
	}
	
	@Test
	public void testWallBonus(){
		IBlock lshape = new L_Block(this.well);
		lshape.drop();
		
		// Assert no wall bonus
		double expectedBonus = 0;
		double actualBonus = testObject.calculateWallBonus(lshape);
		assertEquals(expectedBonus, actualBonus, DELTA);
		
		// Move to left wall
		boolean moveLeft = lshape.moveLeft();
		while(moveLeft==true)
			moveLeft = lshape.moveLeft();
		expectedBonus = 2 * testChrom.getWallCoefficient();
		actualBonus = testObject.calculateWallBonus(lshape);
		assertEquals(expectedBonus, actualBonus, DELTA);
		
		// Move to the right wall
		boolean moveRight = lshape.moveRight();
		while(moveRight==true)
			moveRight = lshape.moveRight();
		expectedBonus = 1 * testChrom.getWallCoefficient();
		actualBonus = testObject.calculateWallBonus(lshape);
		assertEquals(expectedBonus, actualBonus, DELTA);
		
	}
	
	@Test
	public void testHeightDeduction(){
		IBlock jBlock = new J_Block(well);
		jBlock.drop();
		RotationAdapter adapter = new RotationAdapter(well);
		jBlock = adapter.rotateLeft(jBlock);
		// First test that the jblock landed
		Cell[] expectedCells = {
				new Cell(5,1),
				new Cell(5,2),
				new Cell(6,2),
				new Cell(5,0)
		};
		double expectedHeightDeduction = 0;
		for(int i=0; i < expectedCells.length; i++){
			System.err.println(jBlock.getCells()[i]);
			assertEquals(expectedCells[i], jBlock.getCells()[i]);
			expectedHeightDeduction+= expectedCells[i].getRow() * testChrom.getHeightCoefficient();
		}
		double actualHeightDeduction = testObject.calculateHeightDeduction(jBlock);
		assertEquals(expectedHeightDeduction, actualHeightDeduction, DELTA);
		
	}
	
	@Test
	public void testHoleDeduction(){
		fail();
	}
	
	@Test
	public void testRowClearance(){
		fail();
	}
	
	@Test
	public void testBlockade(){
		fail();
	}
	
	@Test
	public void testScoreCalculation(){
		fail();
	}
	
	@Test
	public void getChromosome(){
		IChromosome actualChrom = testObject.getChromosome();
		assertSame(testChrom, actualChrom);
	}
}
