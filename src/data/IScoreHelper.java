package data;

public interface IScoreHelper {
	public int countHolesBelowCell(int r, int c);
	public int countBlockadesBelowCell(int r, int c);
	public int countAdjacentBlocks(int r, int c);
	public boolean isCellAdjacentToWall(int r, int c);
	public boolean isCellAdjacentToFloor(int r, int c);
	
}
