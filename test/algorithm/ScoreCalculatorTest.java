package algorithm;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import blocks.IBlock;
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
	
	
	@Before
	public void setUp(){
		int height = 3;
		int width = 3;
		well = new Well(height, width);
		
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
		fail("Not yet implemented");
	}
	
	@Test
	public void testWallBonus(){
		fail();
	}
	
	@Test
	public void testHeightDeduction(){
		fail();
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
