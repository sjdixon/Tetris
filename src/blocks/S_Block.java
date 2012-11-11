package blocks;
import data.IWell;

public class S_Block extends BasicBlock {
	public final int FLAT = 0;
	public final int TALL = 1;
	
	public S_Block(IWell well){
		Cell[] cells = {
				new Cell(19, 5),
				new Cell(19, 6),
				new Cell(18, 5),
				new Cell(18, 4)
		};
		this.points = cells;
		this.wellReference = well;
		rotation = FLAT;
	}
	
	@Override
	public Cell[] rotateRight() {
		Cell origin = getOrigin();
		Cell[] newCells = new Cell[4];
		newCells[0] = origin;
		int r = origin.row;
		int c = origin.column;
		if(rotation==FLAT){
			newCells[1] = new Cell(r+1, c);
			newCells[2] = new Cell(r, c+1);
			newCells[3] = new Cell(r-1, c+1);
			rotation = TALL;
		}
		else {
			newCells[1] = new Cell(r, c+1);
			newCells[2] = new Cell(r-1, c);
			newCells[3] = new Cell(r-1, c-1);
			rotation = FLAT;
		}
		return newCells;
	}

	@Override
	public Cell[] rotateLeft() {
		return rotateRight();
	}

}
