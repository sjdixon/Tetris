package blocks;
import data.IWell;

public class J_Block extends BasicBlock {

	public J_Block(IWell well){
		this.points = new Cell[4];
		this.points[0] = new Cell(5,19);
		this.points[1] = new Cell(4,19);
		this.points[2] = new Cell(6,19);
		this.points[3] = new Cell(6,18);
		this.wellReference = well;
	}

	@Override
	public Cell[] rotateRight() {
		return getPosition();
	}

	@Override
	public Cell[] rotateLeft() {
		// TODO Auto-generated method stub
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
			rotated[1] = new Cell(c-1,r);
			rotated[2] = new Cell(c+1,r);
			rotated[3] = new Cell(c+1,r-1);
			break;
		case 1:
			rotated[1] = new Cell(c,r+1);
			rotated[2] = new Cell(c+1,r+1 );
			rotated[3] = new Cell(c,r-1);
			break;
		case 2:
			rotated[1] = new Cell(c-1,r+1);
			rotated[2] = new Cell(c-1, r);
			rotated[3] = new Cell(c+1,r);
			break;
		case 3:
			rotated[1] = new Cell(c,r+1);
			rotated[2] = new Cell(c-1,r-1);
			rotated[3] = new Cell(c,r-1);
			break;
		}
		
		return rotated;
	}
	@Override
	public int numRotations(){
		return 4;
	}


}
