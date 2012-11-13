package blocks;
import data.IWell;

public class S_Block extends BasicBlock {
	public final int FLAT = 0;
	public final int TALL = 1;
	
	public S_Block(IWell well){
		Cell[] cells = {
				new Cell(5,19),
				new Cell(6,19),
				new Cell(4,18),
				new Cell(5,18)
		};
		this.points = cells;
		this.wellReference = well;
		rotation = FLAT;
	}

	@Override
	public int numRotations(){
		return 2;
	}
	
	@Override
	public Cell[] rotateRight() {
		Cell origin = getOrigin();
		Cell[] newCells = new Cell[4];
		newCells[0] = origin;
		int r = origin.row;
		int c = origin.column;
		if(rotation==FLAT){
			newCells[1] = new Cell(r, c+1);
			newCells[2] = new Cell(r-1,c-1 );
			newCells[3] = new Cell(r-1, c);
		}
		else {
			newCells[1] = new Cell(r+1, c);
			newCells[2] = new Cell(r, c+1);
			newCells[3] = new Cell(r-1, c+1);
		}
		// Correction Code
		newCells[1].flip();
		newCells[2].flip();
		newCells[3].flip();
		return newCells;
	}

	@Override
	public Cell[] rotateLeft() {
		return rotateRight();
	}

}
