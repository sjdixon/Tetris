package data;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import data.Chromosome;

public class ChromosomeTest {

	protected IChromosome testObject;
	protected final double MAKE_MUTATION_GUARANTEED = Double.POSITIVE_INFINITY;
	protected final double MAKE_MUTATION_IMPOSSIBLE = Double.NEGATIVE_INFINITY;
	final double DELTA = 0.00001;
	
	@Before
	public void setUp() throws Exception {
		double[] coefficients = createArray(0);
		testObject = new Chromosome(coefficients);
	}

	@Test
	public void testMating() {
		double[] mateCoefficients = createArray(2.0);
		Chromosome mate = new Chromosome(mateCoefficients);
		mate.mutationProbability = MAKE_MUTATION_IMPOSSIBLE;
		IChromosome offspring = mate.produceOffspring(testObject);
		
		double[] expectedOffspringCoefficients = createArray(1.0);
		IChromosome expectedOffspring = new Chromosome(expectedOffspringCoefficients);
		testChromosomeEquality(expectedOffspring, offspring);
	}
	
	static public double[] createArray(double elementValue){
		double[] coefficients = new double[IChromosome.NUM_COEFFICIENTS];
		for(int i=0; i < coefficients.length; i++){
			coefficients[i] = elementValue;
		}
		return coefficients;
	}
	
	protected void testChromosomeEquality(IChromosome chrom1, IChromosome chrom2){
		assertEquals(chrom1.getClass(), chrom2.getClass());
		assertEquals(chrom1.getBlockadeCoefficient(), chrom2.getBlockadeCoefficient(), DELTA);
		assertEquals(chrom1.getFloorCoefficient(), chrom2.getFloorCoefficient(), DELTA);
		assertEquals(chrom1.getHeightCoefficient(), chrom2.getHeightCoefficient(), DELTA);
		assertEquals(chrom1.getHoleCoefficient(), chrom2.getHoleCoefficient(), DELTA);
		assertEquals(chrom1.getRowClearanceCoefficient(), chrom2.getRowClearanceCoefficient(), DELTA);
		assertEquals(chrom1.getWallCoefficient(), chrom2.getWallCoefficient(), DELTA);
	}
	
	@Test
	public void testMutation(){
		Chromosome mate = new Chromosome(createArray(2));
		IChromosome mutant = testObject.mutate();
		
		mate.mutationProbability = MAKE_MUTATION_IMPOSSIBLE;
		IChromosome normalChild = mate.produceOffspring(testObject);
		
		mate.mutationProbability = MAKE_MUTATION_GUARANTEED;
		IChromosome mutantChild = mate.produceOffspring(testObject);
		
		testChromosomeInequality(normalChild, mutantChild);
		testChromosomeInequality(normalChild, mutant);
		testChromosomeInequality(mutant, mutantChild);
		
	}
	
	protected void testChromosomeInequality(IChromosome chrom1, IChromosome chrom2){
		assertNotEquals(chrom1.getBlockadeCoefficient(), chrom2.getBlockadeCoefficient());
		assertNotEquals(chrom1.getFloorCoefficient(), chrom2.getFloorCoefficient());
		assertNotEquals(chrom1.getHeightCoefficient(), chrom2.getHeightCoefficient());
		assertNotEquals(chrom1.getHoleCoefficient(), chrom2.getHoleCoefficient());
		assertNotEquals(chrom1.getRowClearanceCoefficient(), chrom2.getRowClearanceCoefficient());
		assertNotEquals(chrom1.getWallCoefficient(), chrom2.getWallCoefficient());
	}
	
	protected void assertNotEquals(double num1, double num2){
		assertFalse((num1 + DELTA > num2) && (num1 - DELTA < num2));
	}

	@Test
	public void testScore(){
		double expectedScore = 10;
		testObject.setScore(expectedScore);
		double actualScore = testObject.getScore();
		assertEquals(expectedScore, actualScore, DELTA);
		
		expectedScore = 50;
		testObject.setScore(expectedScore);
		actualScore = testObject.getScore();
		assertEquals(expectedScore, actualScore, DELTA);
	}
	
	@Test
	public void testFitness(){
		int numChroms = 10;
		IChromosome[] testObjects = new Chromosome[numChroms];
		for(int i=0; i < numChroms; i++){
			testObjects[i] = new Chromosome(createArray(i));
			testObjects[i].setScore(i * 100);
			assertEquals(i*100, testObjects[i].getScore(), DELTA);
		}
		for(int i=0; i < numChroms; i++){
			testObjects[i].setFitnessThreshold(testObjects[i].getScore());
			for(int j=0; j < numChroms; j++){

				//System.err.println(i + " " +j);
				boolean expectedResult;
				if(j < i){
					expectedResult = false;
				}
				else {
					expectedResult = true;
				}
				boolean actualResult = testObjects[j].passesFitnessThreshold();
				assertEquals(expectedResult, actualResult);
			}
		}
	}
	
	@Test
	public void testCoefficientChanges(){
		Random gen = new Random();
		double[] newValues = new double[IChromosome.NUM_COEFFICIENTS];
		double[] actualValues = new double[IChromosome.NUM_COEFFICIENTS];
		for(int i=0; i < newValues.length; i++){
			newValues[i] = gen.nextGaussian();
			testObject.setCoefficient(i, newValues[i]);
			actualValues[i] = compareCoefficients(i);
			assertEquals(newValues[i], actualValues[i], DELTA);
		}
		
		// Test illegal coefficients
		int[] illegalCoefficients = new int[2];
		illegalCoefficients[0] = -1;
		illegalCoefficients[1] = IChromosome.NUM_COEFFICIENTS;
		for(int i : illegalCoefficients){
			testObject.setCoefficient(i, 0);
			for(int j=0; j < IChromosome.NUM_COEFFICIENTS; j++){
				double actualValue = compareCoefficients(j);
				double expectedValue = newValues[j];
				assertEquals(expectedValue, actualValue, DELTA);
			}
		}
		
	}
	
	public double compareCoefficients(int i){
		double returnValue;
		switch(i){
		case IChromosome.HOLE: 
			returnValue = testObject.getHoleCoefficient();
			break;
		case IChromosome.HEIGHT:
			returnValue = testObject.getHeightCoefficient();
			break;
		case IChromosome.BLOCKADE:
			returnValue = testObject.getBlockadeCoefficient();
			break;
		case IChromosome.FLOOR:
			returnValue  = testObject.getFloorCoefficient();
			break;
		case IChromosome.ROWCLEAR:
			returnValue = testObject.getRowClearanceCoefficient();
			break;
		default: case IChromosome.WALL:
			returnValue  = testObject.getWallCoefficient();
			break;
		}
		return returnValue;
	}
}
