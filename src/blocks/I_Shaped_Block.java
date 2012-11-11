package blocks;

import data.IWell;

public class I_Shaped_Block extends BasicBlock {

	public I_Shaped_Block(IWell well){
		Cell[] cells = {
				new Cell(5, 19),
				new Cell(4, 19),
				new Cell(3, 19),
				new Cell(6, 19)
		};
		this.points = cells;
		this.wellReference = well;
		rotation = FLAT;
	}
	
	@Override
	public Cell[] rotateRight() {
		// TODO Auto-generated method stub
		Cell[] newCells = new Cell[4];
		newCells[0] = getOrigin();
		int column = newCells[0].column;
		int row = newCells[0].row;
		if(rotation==FLAT){
			newCells[1] = new Cell(row+1, column);
			newCells[2] = new Cell(row-1, column);
			newCells[3] = new Cell(row-2, column);
			rotation = TALL;
		}
		else {
			newCells[1] = new Cell(row, column-1);
			newCells[2] = new Cell(row, column-2);
			newCells[3] = new Cell(row, column+1);
			rotation = FLAT;
		}
		return newCells;
	}

	@Override
	public Cell[] rotateLeft() {
		return rotateRight();
	}

}
