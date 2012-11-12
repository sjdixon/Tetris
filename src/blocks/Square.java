package blocks;

import data.IWell;


public class Square extends BasicBlock{
	public Square(IWell well){
		Cell[] cells = {
				new Cell(5, 19),
				new Cell(4, 19),
				new Cell(4, 18),
				new Cell(5, 18)
		};
		this.points = cells;
		this.wellReference = well;
	}
	@Override
	public int numRotations(){
		return 1;
	}

	@Override
	public Cell[] rotateRight() {
		// TODO Auto-generated method stub
		return this.points;
	}

	@Override
	public Cell[] rotateLeft() {
		// TODO Auto-generated method stub
		return this.points;
	}
	
}
