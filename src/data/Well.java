package data;

import blocks.IBlock;

public class Well implements IWell{
	protected int height;
	protected int width;
	protected boolean[][] fullCells = null;
	
	public boolean[][] getCells(){
		return fullCells;
	}
	
	public int getHeight() {
		return height;
	}

	public int getWidth() {
		return width;
	}

	public Well(int height, int width){
		this.height = height;
		this.width = width;
		fullCells = new boolean[height][];
		for(int i=0; i < height; i++){
			fullCells[i] = new boolean[width];
			for(int j=0; j < width; j++)
				fullCells[i][j] = false;
		}
	}
	
	public boolean fillCell(int row, int column){
		if(isCellEmpty(row, column)==true){
			fullCells[row][column] = true;
			return true;
		}
		return false;
	}
	
	public boolean isCellEmpty(int row, int col){
		if(fullCells[row][col]==false)
			return true;
		else return false;
	}
	
	public boolean isCellWithinBounds(int row, int col){
		if(row < 0 || col < 0)
			return false;
		if(row >= height || col >= width)
			return false;
		return true;
	}
	
	public boolean isCellFull(int row, int col){
		if(fullCells[row][col]==true)
			return true;
		else return false;
	}
	
	public boolean emptyCell(int row, int col){
		if(isCellFull(row, col)==true){
			fullCells[row][col] = false;
			return true;
		}
		return false;
	}
	
	
	public boolean clearRow(int row){
		boolean isRowFull = isRowFull(row);
		if(isRowFull==true){
			// Bring all the rows above the current one down a row
			int topRow = fullCells.length -1;
			for(int i=row; i < topRow; i++){
				for(int j=0; j < fullCells[topRow].length; j++)
					fullCells[i][j] = fullCells[i+1][j];
			}
			for(int j=0; j < fullCells[topRow].length; j++)
				fullCells[topRow][j] = false;
		}
		return isRowFull;
	}
	
	public boolean isRowFull(int row){
		boolean isRowFull = true;
		for(int i=0; i < fullCells[row].length; i++){
			if(isCellEmpty(row, i)==true){
				isRowFull = false;
				break;
			}
		}
		return isRowFull;
	}
	
	@Override
	public String toString(){
		String matrix = "";
		for(int i=fullCells.length-1; i >=0; i--){
			for(int j=0; j < fullCells[i].length; j++){
				matrix += fullCells[i][j] + "  ";
				if(fullCells[i][j]==true)
					matrix+=" ";
			}
			matrix += "\n";
		}
		return matrix;
	}

	@Override
	public void readHexadecimalBoard(String hexadecimal) {
		// TODO Auto-generated method stub
		
	}
}
