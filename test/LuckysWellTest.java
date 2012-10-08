import static org.junit.Assert.*;


import org.junit.Before;
import org.junit.Test;

import data.LuckysScoringHelper;
import data.Well;
import data.IWell;
import data.IScoreHelper;


public class LuckysWellTest {

	protected IWell testContext;
	protected IScoreHelper testObject;
	protected int height = 5;
	protected int width = 6;
	
	@Before
	public void setUp() throws Exception {
		
		testContext = new Well(height, width);
		for(int i=0; i < height && i < width; i++){
			testContext.fillCell(i, i);
			if(i>0)
				testContext.fillCell(i-1, i);
		}
		testObject = new LuckysScoringHelper();
	}
	
	@Test
	public void testHeight(){
		for(int i=0; i < width; i++){
			
		}
	}
	
	@Test
	public void testCountingHoles() {
		
	}

}
