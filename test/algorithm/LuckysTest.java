package algorithm;

import static org.junit.Assert.*;

import org.jmock.Mockery;
import org.jmock.Expectations;

import org.junit.Before;
import org.junit.Test;

import algorithms.LuckysAlgorithm;
import data.IWell;
import blocks.BasicBlock;
import blocks.IBlock;
import data.IChromosome;
import data.Well;

public class LuckysTest {
	protected LuckysAlgorithm testObject;
	protected IWell testContext;
	protected IBlock testBlock; 
	protected Mockery context = new Mockery();
	protected IChromosome testChromosome;
	
	@Before
	public void setUp() throws Exception {
		int height = 5;
		int width = 5;
		testContext = new Well(height, width);
		testChromosome = context.mock(IChromosome.class);
		testObject = new LuckysAlgorithm(testContext, testChromosome);
		testBlock = context.mock(IBlock.class);
		
	}

	@Test
	public void testScoringAlgorithm() {
		
	}

}
