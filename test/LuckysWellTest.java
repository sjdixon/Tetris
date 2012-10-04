import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import data.LuckysWell;
import data.Well;


public class LuckysWellTest extends WellTest {

	@Before
	@Override
	public void setUp() throws Exception {
		height =5;
		width = 6;
		testObject = new LuckysWell(height, width);
		System.out.println("I got called.");
	}
	
	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
