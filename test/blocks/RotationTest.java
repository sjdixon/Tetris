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
	protected final int HEIGHT = 20, WIDTH = 10;
	
	@Before
	public void setup(){
		testContext = new Well(HEIGHT, WIDTH);
	}
	@Test
	public void testRotate_T(){
		
	}
	@Test
	public void testRotate_L(){
		
	}
	@Test
	public void testRotate_Square(){
		testObject = new Square(testContext);
	}
	@Test
	public void testRotate_S(){
		
	}
	@Test
	public void testRotate_Z(){
		
	}
	@Test
	public void testRotate_J(){
		
	}
	@Test
	public void testRotate_I(){
		
	}

}
