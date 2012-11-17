package data;

import blocks.Cell;
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

	public boolean lockBlock(IBlock b){
		boolean success = true;
		Cell[] cells = b.getCells();
		for(int i=0; i < b.getCells().length; i++){
			if(isCellEmpty(cells[i].getRow(), cells[i].getColumn())==false){
				success = false;
				break;
			}
		}
		if(success=true){
			for(int i=0; i < cells.length; i++)
				fillCell(cells[i].getRow(), cells[i].getColumn());
		}
		
		return success;
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
		byte[] byteMap = getHexBytes(hexadecimal);
		boolean[] wellBitMap = booleanArrayFromByte(byteMap);
		for(int i=0; i < height; i++){
			for(int j=0; j < width; j++){
				int bitRow = height-1-i;
				int bitColumn = j;
				int bitIndex = bitRow * width + bitColumn;
				boolean bitValue = wellBitMap[bitIndex];
				fullCells[i][j] = bitValue;
			}
		}
	}
	
	public byte[] getHexBytes(String s){
		int len = s.length();
	    byte[] data = new byte[len / 2];
	    for (int i = 0; i < len; i += 2) {
	        data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
	                             + Character.digit(s.charAt(i+1), 16));
	    }
	    return data;
	}
	
	public static boolean[] booleanArrayFromByte(byte[] array) {
		boolean[] bs = new boolean[array.length*8];
		for(int i=0,j=0; i < bs.length; i+=8,j++){
		    bs[i] = ((array[j] & 0x80) != 0);
		    bs[i+1] = ((array[j] & 0x40) != 0);
		    bs[i+2] = ((array[j] & 0x20) != 0);
		    bs[i+3] = ((array[j] & 0x10) != 0);
		    bs[i+4] = ((array[j] & 0x08) != 0);
		    bs[i+5] = ((array[j] & 0x04) != 0);
		    bs[i+6] = ((array[j] & 0x02) != 0);
		    bs[i+7] = ((array[j] & 0x01) != 0);
		}
	    return bs;
	}
}
