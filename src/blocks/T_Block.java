package blocks;
import data.IWell;

public class T_Block extends BasicBlock {
	public T_Block(IWell well){
		this.points = new Cell[4];
		this.points[0] = new Cell(5,19);
		this.points[1] = new Cell(4,19);
		this.points[2] = new Cell(6,19);
		this.points[3] = new Cell(5,18);
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
	@Override
	public int numRotations(){
		return 4;
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
			rotated[2] = new Cell(r, c+1);
			rotated[3] = new Cell(r-1, c);
			break;
		case 1:
			rotated[1] = new Cell(r+1, c);
			rotated[2] = new Cell(r, c+1);
			rotated[3] = new Cell(r-1, c);
			break;
		case 2:
			rotated[1] = new Cell(r+1, c);
			rotated[2] = new Cell(r, c-1);
			rotated[3] = new Cell(r, c+1);
			break;
		case 3:
			rotated[1] = new Cell(r+1, c);
			rotated[2] = new Cell(r, c-1);
			rotated[3] = new Cell(r-1, c);
			break;
		}
		rotated[1].flip();
		rotated[2].flip();
		rotated[3].flip();
		return rotated;
	}

}
