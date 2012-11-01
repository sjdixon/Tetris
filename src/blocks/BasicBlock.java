package blocks;

import data.IWell;

public abstract class BasicBlock implements IBlock{
	protected Cell[] points;
	protected IWell wellReference;

	public Cell[] getCells() {
		Cell[] copy = new Cell[points.length];
		for(int i=0; i < copy.length; i++)
			copy[i] = new Cell(points[i].row, points[i].column);
		return copy;
	}

	public void setPoints(Cell[] points) {
		this.points = points;
	}
	
	public void setWellReference(IWell well){
		this.wellReference = well;
	}

	@Override
	public boolean moveLeft() {
		return move(-1, 0);
	}

	@Override
	public boolean moveRight() {
		return move(1,0);
	}

	public boolean move(int dx, int dy){
		int[] xValues = new int[points.length];
		int[] yValues = new int[points.length];
		boolean validMove = true;
		for(int i=0; i < xValues.length; i++){
			xValues[i] = points[i].getRow() + dx;
			yValues[i] = points[i].getColumn() + dy;
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
			for(int i=0; i < points.length; i++){
				points[i].setRow(xValues[i]);
				points[i].setColumn(yValues[i]);
			}
		}
		return validMove;
	}
	@Override
	public boolean moveDown() {
		// TODO Auto-generated method stub
		return move(0, -1);
	}
	
	@Override
	public boolean moveUp() {
		return move(0,1);
	}


}
