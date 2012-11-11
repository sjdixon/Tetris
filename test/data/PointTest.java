package data;
import static org.junit.Assert.*;


import org.junit.Test;

import blocks.Cell;


public class PointTest {

	@Test
	public void testPointClass() {
		int x = 169, y = 43;
		Cell testObject = new Cell(x,y);
		assertEquals(y,testObject.getRow());
		assertEquals(x,testObject.getColumn());
		
		x = 143; 
		y = 37;
		testObject.setCoords(y, x);
		assertEquals(y,testObject.getRow());
		assertEquals(x,testObject.getColumn());
		
		x = 0;
		y = 0;
		testObject.setRow(x);
		testObject.setColumn(y);
		assertEquals(y,testObject.getRow());
		assertEquals(x,testObject.getColumn());
		
	}

	@Test
	public void testEquality(){
		int x1 = 150, y1 = 49;
		Cell referenceObject = new Cell(x1,y1);
		Object[] testObjects = new Object[5];
		testObjects[0] = new Cell(x1,y1);
		testObjects[1] = new Cell(x1+1, y1);
		testObjects[2] = new Cell(x1, y1-1);
		testObjects[3] = new Cell(x1-1, y1+1);
		testObjects[4] = new Object();
		
		assertNotSame(referenceObject, testObjects[0]);
		
		boolean expected = true;
		assertEquals(expected, referenceObject.equals(testObjects[0]));
		for(int i=1; i < testObjects.length; i++){
			expected = false;
			assertEquals(expected, referenceObject.equals(testObjects[i]));
		}
		
	} 
	
	@Test
	public void testToString(){
		Cell TestObject = new Cell(-41, 433);
		String expected = "(-41,433)";
		assertEquals(expected, TestObject.toString());
	}
}
