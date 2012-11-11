package blocks;
import data.IWell;

public class L_Block extends BasicBlock {
	public L_Block(IWell well){
		this.points = new Cell[4];
		points[0] = new Cell(5, 19);
		points[1] = new Cell(4, 19);
		points[2] = new Cell(4, 18);
		points[3] = new Cell(6, 19);
		this.wellReference = well;
	}
	@Override
	public Cell[] rotateRight() {
		rotation = (rotation+3)%4;
		return getPosition();
	}
	
	@Override
	public Cell[] rotateLeft() {
		// TODO Auto-generated method stub
		rotation = (rotation+1)%4;
		return getPosition();
	}

	protected Cell[] getPosition(){
		Cell origin = getOrigin();
		int r = origin.row;
		int c = origin.column;
		Cell[] rotated = new Cell[4];
		rotated[0] = origin;
		
		switch(rotation){
		case 0:
			rotated[1] = new Cell(r, c-1);
			rotated[2] = new Cell(r-1, c-1);
			rotated[3] = new Cell(r, c+1);
			break;
		case 1:
			rotated[1] = new Cell(r+1, c);
			rotated[2] = new Cell(r-1, c);
			rotated[3] = new Cell(r-1, c+1);
			break;
		case 2: 
			rotated[1] = new Cell(r, c-1);
			rotated[2] = new Cell(r, c+1);
			rotated[3] = new Cell(r+1, c+1);
			break;
		case 3:
			rotated[1] = new Cell(r+1, c-1);
			rotated[2] = new Cell(r+1, c);
			rotated[3] = new Cell(r-1, c);
			break;
		}
		return rotated;
	}

}
