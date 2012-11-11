package blocks;

import data.IWell;

public class Z_Block extends BasicBlock{
	public Z_Block(IWell well){
		this.points = new Cell[4];
		points[0] = new Cell(19,5);
		points[1] = new Cell(19,4);
		points[2] = new Cell(18,5);
		points[3] = new Cell(18,6);
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
			rotated[1] = new Cell(r, c+1);
			rotated[2] = new Cell(r+1, c+1);
			rotated[3] = new Cell(r-1, c);
			rotation = TALL;
		}
		else {
			rotated[1] = new Cell(r, c-1);
			rotated[2] = new Cell(r-1, c);
			rotated[3] = new Cell(r-1, c+1);
			rotation = FLAT;
		}
		return rotated;
	}

	@Override
	public Cell[] rotateLeft() {
		// TODO Auto-generated method stub
		return rotateRight();
	}

}
