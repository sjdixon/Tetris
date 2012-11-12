package blocks;

import data.IWell;

public interface IBlock {

	public final int FLAT = 0;
	public final int TALL = 1;
	public Cell getOrigin();
	public void setRotation(int value);
	public int getRotation();
	public int numRotations();
	public void drop();
	
	public boolean move(int dx, int dy);
	public boolean moveLeft();
	public boolean moveRight();
	public boolean moveDown();
	public boolean moveUp();
	public Cell[] rotateRight();
	public Cell[] rotateLeft();
	public Cell[] getCells();
	public void setPoints(Cell[] points);
	public void setWellReference(IWell well);
}
