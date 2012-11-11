package data;
import blocks.Cell;
public interface IScoreHelperAdapter {
	public int countHolesBelowCell(Cell c);
	public int countBlockades(Cell c);
	public int countAdjacentBlocks(Cell c);
	public int calculateHeightOfColumn(Cell c);
	public boolean isCellAdjacentToWall(Cell c);
	public boolean isCellAdjacentToFloor(Cell c);
}
