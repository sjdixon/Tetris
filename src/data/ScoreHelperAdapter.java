package data;

import blocks.Cell;

public class ScoreHelperAdapter implements IScoreHelperAdapter {
	protected IScoreHelper adaptedObject;
	
	public ScoreHelperAdapter(IWell well){
		adaptedObject = new LuckysScoringHelper(well);
	}

	@Override
	public int countHolesBelowCell(Cell c) {
		return adaptedObject.countHolesBelowCell(c.getRow(), c.getColumn());
	}

	@Override
	public int countBlockades(Cell c) {
		return adaptedObject.countBlockades(c.getRow(), c.getColumn());
	}

	@Override
	public int countAdjacentBlocks(Cell c) {
		return adaptedObject.countAdjacentBlocks(c.getRow(), c.getColumn());
	}

	@Override
	public int calculateHeightOfColumn(Cell c) {
		return adaptedObject.calculateColumnHeight(c.getColumn());
	}

	@Override
	public boolean isCellAdjacentToWall(Cell c) {
		return adaptedObject.isCellAdjacentToWall(c.getRow(), c.getColumn());
	}

	@Override
	public boolean isCellAdjacentToFloor(Cell c) {
		return adaptedObject.isCellAdjacentToFloor(c.getRow(), c.getColumn());
	}
	
	
}
