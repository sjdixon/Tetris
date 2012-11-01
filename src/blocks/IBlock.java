package blocks;

import data.IWell;

public interface IBlock {
	public boolean move(int dx, int dy);
	public boolean moveLeft();
	public boolean moveRight();
	public boolean moveDown();
	public boolean moveUp();
	public Cell[] rotateCW();
	public Cell[] rotateCCW();
	public Cell[] getCells();
	public void setPoints(Cell[] points);
	public void setWellReference(IWell well);
}
