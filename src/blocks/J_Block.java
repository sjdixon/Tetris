package blocks;
import data.IWell;

public class J_Block extends BasicBlock {

	public J_Block(IWell well){
		this.points = new Cell[4];
		this.points[0] = new Cell(19,5);
		this.points[1] = new Cell(19,4);
		this.points[2] = new Cell(19,6);
		this.points[3] = new Cell(18,6);
		this.wellReference = well;
	}
	
	@Override
	public Cell[] rotateRight() {
		rotation = (rotation+3)%4;
		return getPosition();
	}
	
	@Override
	public Cell[] rotateLeft() {
		rotation = (rotation+1)%4;
		return getPosition();
	}
	
	private Cell[] getPosition(){
		Cell origin = getOrigin();
		int r = origin.row;
		int c = origin.column;
		Cell[] rotated = new Cell[4];
		rotated[0] = origin;
		
		switch(rotation){
		case 0:
			rotated[1] = new Cell(r, c-1);
			rotated[2] = new Cell(r,c+1);
			rotated[3] = new Cell(r-1, c+1);
			break;
		case 1:
			rotated[1] = new Cell(r+1, c+1);
			rotated[2] = new Cell(r+1, c);
			rotated[3] = new Cell(r-1, c);
			break;
		case 2:
			rotated[1] = new Cell(r+1, c-1);
			rotated[2] = new Cell(r, c-1);
			rotated[3] = new Cell(r, c+1);
			break;
		case 3:
			rotated[1] = new Cell(r+1, c);
			rotated[2] = new Cell(r-1, c-1);
			rotated[3] = new Cell(r-1, c);
			break;
		}
		
		return rotated;
	}


}
