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
		double expectedHeightDeduction = 5 * testChrom.getHeightCoefficient();
		double actualHeightDeduction = testObject.calculateHeightDeduction(jBlock);
		assertEquals(expectedHeightDeduction, actualHeightDeduction, DELTA);
		
	}
	
	@Test
	public void testHoleDeduction(){
		IBlock[] cases = createCases();
		double[] expectedPartialScores = {
				5, 1, 0, 1
		};
		for(int i=0; i < cases.length; i++){
			double actualScore = testObject.calculateHoleDeduction(cases[i]);
			double expectedScore = expectedPartialScores[i] * testChrom.getHoleCoefficient();
			assertEquals(expectedScore, actualScore, DELTA);
		}
	}
	
	@Test
	public void testRowClearance(){
		setupWellForClearance();
		IBlock lBlock = new L_Block(well);
		RotationAdapter adapter = new RotationAdapter(well);
		lBlock.moveDown();
		lBlock.move(3, 0);
		lBlock = adapter.rotateRight(lBlock);
		assertEquals(lBlock.getOrigin(), new Cell(8, 18));
		lBlock.drop();
		
		// Test Row Clearance
		Cell[] expectedCell = new Cell[4];
		expectedCell[0] = new Cell(1,WIDTH-1);
		expectedCell[1] = new Cell(2,WIDTH-2);
		expectedCell[2] = new Cell(2, WIDTH-1);
		expectedCell[3] = new Cell(0, WIDTH-1);
		for(int i=0; i < expectedCell.length; i++){
			assertEquals(expectedCell[i], lBlock.getCells()[i]);
		}
		
		double result = testObject.calculateRowClearanceBonus(lBlock);
		double expected = testChrom.getRowClearanceCoefficient() * 3;
		assertEquals(expected, result, DELTA);
	}
	
	@Test
	public void testCountHoles(){
		for(int i=0; i < WIDTH; i+=2){
			well.fillCell(0, i);
			well.fillCell(1, i+1);
		}
		IBlock hugeBlock = createHugeBlock();
		hugeBlock.drop();
		
		double estimatedScore = 4 * testChrom.getHoleCoefficient();
		double actualScore = testObject.calculateHoleDeduction(hugeBlock);
		assertEquals(estimatedScore, actualScore, DELTA);
	}
	
	public IBlock createHugeBlock(){
		IBlock hugeBlock = new I_Shaped_Block(well);
		Cell[] newCells = new Cell[8];
		for(int i=0; i < 4; i++){
			newCells[i] = new Cell(i, 19);
			newCells[i+4] = new Cell(i, 18);
		}
		return hugeBlock;
	}
	
	public void setupWellForClearance(){
		for(int i=0; i < WIDTH-1; i++){
			well.fillCell(0, i);
			well.fillCell(1,i);
			well.fillCell(2,i);
		}
		well.emptyCell(2, WIDTH-2);
	}
	
	@Test
	public void testBlockade(){
		IBlock[] cases = createCases();
		double[] expectedBlockades = {
				2, 4, 0, 0
		};
		for(int i=0; i < cases.length; i++){
			double actualScore = testObject.calculateBlockadeDeduction(cases[i]);
			double expectedScore = testChrom.getBlockadeCoefficient() * expectedBlockades[i];
			assertEquals(expectedScore, actualScore, DELTA);
		}
	}
	
	public IBlock[] createCases(){
		createSpecialContextForHolesAndBlockades();
		
		IBlock iBlock = new I_Shaped_Block(well);
		iBlock.move(-3, 0);
		iBlock.drop();
		
		IBlock sBlock = new S_Block(well);
		RotationAdapter adapter = new RotationAdapter(well);
		sBlock.moveDown();
		sBlock = adapter.rotateLeft(sBlock);
		sBlock.moveLeft();
		sBlock.drop();
		
		IBlock square = new Square(well);
		square.moveRight();
		square.moveRight();
		square.drop();
		
		IBlock zBlock = new Z_Block(well);
		zBlock.moveDown();
		zBlock.move(3, 0);
		zBlock = adapter.rotateRight(zBlock);
		zBlock.drop();
		
		IBlock[] cases = new IBlock[4];
		cases[0] = iBlock;
		cases[1] = sBlock;
		cases[2] = square;
		cases[3] = zBlock;
		return cases;
	}
	
	@Test
	public void testScoreCalculation(){
		fail();
	}
	
	void createSpecialContextForHolesAndBlockades(){
		for(int i=1; i < WIDTH; i++)
			well.fillCell(0,i); // first row
		well.fillCell(1,2);
		well.fillCell(1, 5);
		for(int i=0; i <=5; i++){
			if(i==3)
				continue;
			else well.fillCell(2,i);
		}
		well.fillCell(3,4);
	}
	
	@Test
	public void getChromosome(){
		IChromosome actualChrom = testObject.getChromosome();
		assertSame(testChrom, actualChrom);
	}
}
