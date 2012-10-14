package blocks;

import data.IWell;

public abstract class BasicBlock implements IBlock{
	protected Point[] points;
	protected IWell wellReference;

	public Point[] getPoints() {
		return points;
	}

	public void setPoints(Point[] points) {
		this.points = points;
	}
	
	public void setWellReference(IWell well){
		this.wellReference = well;
	}

	@Override
	public boolean moveLeft(int x) {
		int[] xValues = new int[points.length];
		int[] yValues = new int[points.length];
		boolean validMove = true;
		for(int i=0; i < xValues.length; i++){
			xValues[i] = points[i].getX() - x;
			yValues[i] = points[i].getY();
			if(wellReference.isCellFull(xValues[i], yValues[i])==true){
				validMove = false;
				break;
			}
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
