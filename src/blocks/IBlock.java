package blocks;

import data.IWell;

public interface IBlock {
	public boolean move(int dx, int dy);
	public boolean moveLeft();
	public boolean moveRight();
	public boolean moveDown();
	public boolean moveUp();
	public Point[] rotateCW();
	public Point[] rotateCCW();
	public Point[] getPoints();
	public void setPoints(Point[] points);
	public void setWellReference(IWell well);
}
