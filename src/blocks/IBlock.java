package blocks;


public interface IBlock {
	public boolean moveLeft(int x);
	public boolean moveRight(int x);
	public boolean moveDown(int y);
	public Point[] rotateLeft();
	public Point[] rotateRight();
}
