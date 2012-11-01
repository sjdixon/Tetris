package data;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import data.Chromosome;

public class ChromosomeTest {

	protected IChromosome testObject;
	protected final double MAKE_MUTATION_GUARANTEED = Double.POSITIVE_INFINITY;
	protected final double MAKE_MUTATION_IMPOSSIBLE = Double.NEGATIVE_INFINITY;
	
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
	
	protected double[] createArray(double elementValue){
		double[] coefficients = new double[IChromosome.NUM_COEFFICIENTS];
		for(int i=0; i < coefficients.length; i++){
			coefficients[i] = elementValue;
		}
		return coefficients;
	}
	
	protected void testChromosomeEquality(IChromosome chrom1, IChromosome chrom2){
		assertEquals(chrom1.getClass(), chrom2.getClass());
		final double DELTA = 0.01;
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
		final double DELTA = 0.00001;
		boolean isEqual = (num1 + DELTA > num2) && (num1 - DELTA < num2);
		assertEquals(false, isEqual);
		
	}

	@Test
	public void IGOTTADOTHESE(){
		fail();
	}
	/** TODO
	 * 	// Methods Related To its Role in Genetics
	public void setFitnessThreshold(double score);
	public boolean passesFitnessThreshold();
	public IChromosome mutate();
	public double getScore();
	public void setScore(double score);
	
	 */
}
