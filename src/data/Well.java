package data;

public class Well implements IWell{
	protected int height;
	protected int width;
	protected boolean[][] fullCells;
	
	public boolean[][] getCells(){
		return fullCells;
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
		if(fullCells[row][column]==false){
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
	
	public boolean isCellFull(int row, int col){
		return !isCellEmpty(row, col);
	}
	
	public boolean emptyCell(int row, int col){
		if(fullCells[row][col]==true){
			fullCells[row][col] = false;
			return true;
		}
		return false;
	}
	
	
	public boolean deleteRow(int row){
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
			if(fullCells[row][i]==false){
				isRowFull = false;
				break;
			}
		}
		return isRowFull;
	}
	
	public boolean doesBlockFit(IBlock b){
		return false;
	}
}
