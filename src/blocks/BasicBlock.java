package blocks;

import data.Well;

public abstract class BasicBlock implements IBlock{
	protected Point[] points;

	@Override
	public boolean moveLeft(int x) {
		int[] xValues = new int[points.length];
		boolean validMove = true;
		for(int i=0; i < xValues.length; i++){
			xValues[i] = points[i].getX() - x;
			if(xValues[i]<0)
				validMove = false;
		}
		if(validMove==true){
			for(int i=0; i < points.length; i++)
				points[i].setX(xValues[i]);
		}
		return validMove;
		
	}

	@Override
	public boolean moveRight(int x) {
		return false;
	}

	@Override
	public boolean moveDown(int y) {
		// TODO Auto-generated method stub
		return false;
	}



}
