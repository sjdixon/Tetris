package data;

public interface IScoreHelper {
	public int countHolesBelowCell(int r, int c);
	public int countBlockades(int r, int c);
	//public int countAdjacentBlocks(int r, int c);
	public int calculateHeight(int column);
	public boolean isCellAdjacentToWall(int r, int c);
	public boolean isCellAdjacentToFloor(int r, int c);
	
}
