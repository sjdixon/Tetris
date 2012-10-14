package data;
import static org.junit.Assert.*;


import org.junit.Test;

import blocks.Point;


public class PointTest {

	@Test
	public void testPointClass() {
		int x = 169, y = 43;
		Point testObject = new Point(x,y);
		assertEquals(x,testObject.getX());
		assertEquals(y,testObject.getY());
		
		x = 143; 
		y = 37;
		testObject.setCoords(x, y);
		assertEquals(x,testObject.getX());
		assertEquals(y,testObject.getY());
		
		x = 0;
		y = 0;
		testObject.setX(x);
		testObject.setY(y);
		assertEquals(x,testObject.getX());
		assertEquals(y,testObject.getY());
		
	}

}
