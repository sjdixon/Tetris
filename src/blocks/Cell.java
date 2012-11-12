package blocks;

public class Cell {
	protected int row;
	protected int column;
	
	public Cell(int x, int y)
	{
		this.row = y;
		this.column = x;
	}
	
	public int getRow()
	{ 
		return row;
	}
	
	public int getColumn()
	{ 
		return column;
	}
	
	public void setRow(int row){
		this.row = row;
	}
	
	public void setColumn(int col){
		this.column = col;
	}
	
	public void setCoords(int r, int col){
		setRow(r);
		setColumn(col);
	}
	
	public String toString(){
		
		// show in form (X),(Y)
		return "("+column+","+row+")";
	}
	
	public boolean equals(Object p){
		if(p.getClass()!= this.getClass())
			return false;
		if(((Cell) p).getRow() == row && ((Cell) p).getColumn()==column)
			return true;
		else return false;
	}
	
	public void flip(){
		int temp = row;
		row=column;
		column = temp;
	}
}
