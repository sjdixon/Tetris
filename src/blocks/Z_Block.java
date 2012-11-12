package blocks;

import data.IWell;

public class Z_Block extends BasicBlock{
	public Z_Block(IWell well){
		this.points = new Cell[4];
		points[0] = new Cell(5,19);
		points[1] = new Cell(4,19);
		points[2] = new Cell(5,18);
		points[3] = new Cell(6,18);
		this.wellReference = well;
	}
	
	@Override
	public Cell[] rotateRight() {
		// TODO Auto-generated method stub
		Cell[] rotated = new Cell[4];
		rotated[0] = getOrigin();
		int r = rotated[0].row;
		int c = rotated[0].column;
		if(rotation==FLAT){
			rotated[1] = new Cell(r+1, c+1);
			rotated[2] = new Cell(r, c+1);
			rotated[3] = new Cell(r-1, c);
		}
		else {
			rotated[1] = new Cell(r, c-1);
			rotated[2] = new Cell(r-1, c);
			rotated[3] = new Cell(r-1, c+1);
		}
		// Correction Code
		rotated[1].flip();
		rotated[2].flip();
		rotated[3].flip();
		return rotated;
	}

	@Override
	public int numRotations(){
		return 2;
	}
	@Override
	public Cell[] rotateLeft() {
		// TODO Auto-generated method stub
		return rotateRight();
	}

}
