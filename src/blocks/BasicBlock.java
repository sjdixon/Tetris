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
	public boolean moveLeft() {
		int[] xValues = new int[points.length];
		int[] yValues = new int[points.length];
		boolean validMove = true;
		for(int i=0; i < xValues.length; i++){
			xValues[i] = points[i].getX() - 1;
			yValues[i] = points[i].getY();
			if(wellReference.isCellWithinBounds(yValues[i],xValues[i])==false){
				validMove = false;
				break;
			}
			else if(wellReference.isCellFull(yValues[i],xValues[i])==true){
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
	public boolean moveRight() {
		return false;
	}

	@Override
	public boolean moveDown() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean moveUp() {
		return false;
	}


}
