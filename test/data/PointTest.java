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

	public void testEquality(){
		int x1 = 150, y1 = 49;
		Point referenceObject = new Point(x1,y1);
		Object[] testObjects = new Point[5];
		testObjects[0] = new Point(x1,y1);
		testObjects[1] = new Point(x1+1, y1);
		testObjects[2] = new Point(x1, y1-1);
		testObjects[3] = new Point(x1-1, y1+1);
		testObjects[4] = new Object();
		
		assertNotSame(referenceObject, testObjects[0]);
		
		boolean expected = true;
		assertEquals(expected, referenceObject.equals(testObjects[0]));
		for(int i=1; i < testObjects.length; i++){
			expected = false;
			assertEquals(expected, referenceObject.equals(testObjects[i]));
		}
		
	}
}
