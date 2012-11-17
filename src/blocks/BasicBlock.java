package blocks;

import data.IWell;

public abstract class BasicBlock implements IBlock{
	protected Cell[] points;
	protected IWell wellReference;
	protected String path = "";

	public void appendPath(String appendedString){
		path += appendedString + " ";
	}
	
	@Override
	public void removeLast(){
		int lastSpace = path.lastIndexOf(' ');
		if(lastSpace!=-1){
			path = path.substring(0, lastSpace+1);
		}
	}
	
	public String getPath(){
		return path;
	}
	
	public Cell[] getCells() {
		Cell[] copy = new Cell[points.length];
		for(int i=0; i < copy.length; i++)
			copy[i] = new Cell(points[i].column, points[i].row);
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
		boolean success = move(-1, 0);
		if(success==true)
			path += "left ";
		return success;
	}

	@Override
	public boolean moveRight() {
		boolean success = move(1,0);
		if(success==true)
			path += "right ";
		return success;
	}

	public boolean move(int dx, int dy){
		int[] xValues = new int[points.length];
		int[] yValues = new int[points.length];
		boolean validMove = true;
		for(int i=0; i < xValues.length; i++){
			yValues[i] = points[i].getRow() + dy;
			xValues[i] = points[i].getColumn() + dx;
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
				points[i].setRow(yValues[i]);
				points[i].setColumn(xValues[i]);
			}
		}
		return validMove;
	}
	@Override
	public boolean moveDown() {
		boolean success = move(0, -1);
		if(success==true)
			path += "left ";
		return success;
	}
	
	@Override
	public boolean moveUp() {
		return move(0,1);
	}

	protected int rotation = 0;
	@Override
	public int getRotation(){
		return rotation;
	}
	public void setRotation(int newValue){
		rotation = newValue;
	}
	
	@Override
	public Cell getOrigin(){
		Cell copyOfOrigin = new Cell(0,0);
		copyOfOrigin.row = points[0].row;
		copyOfOrigin.column = points[0].column;
		return copyOfOrigin;
	}
	
	@Override
	public void drop(){
		boolean canGoDownFurther = move(0,-1);
		path += "drop ";
		while(canGoDownFurther==true){
			canGoDownFurther = move(0,-1);
		}
	}
	
	@Override
	public void softDrop(){
		boolean canGoDownFurther = moveDown();
		while(canGoDownFurther==true){
			canGoDownFurther = moveDown();
		}
	}
	
	@Override
	public boolean equals(Object b2){
		boolean areEqual = true;
		if(this.getClass()!=b2.getClass())
			areEqual = false;
		else if(this.getCells().length != ((IBlock) b2).getCells().length){
			areEqual = false;
		}
		else if(this.getRotation()!=((IBlock) b2).getRotation())
			areEqual = false;
		else if(((IBlock) b2).getCells().length!=this.points.length)
			areEqual = false;
		else {
			Cell[] b2Cells = ((IBlock) b2).getCells();
			for(int i=0; i < this.getCells().length; i++){
				Cell c1 = this.points[i];
				boolean matchingCellExists = false;
				for(int j=0; j < b2Cells.length; j++){
					Cell c2 = b2Cells[j];
					if(c1.equals(c2)==true){
						matchingCellExists = true;
					}
				}
				if(matchingCellExists==false){
					areEqual = false;
					break;
				}
			}	
		}
		return areEqual;
		
	}
	
}
