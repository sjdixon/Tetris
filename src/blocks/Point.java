package blocks;

public class Point {
	protected int x;
	protected int y;
	
	public Point(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	public int getX()
	{ 
		return x;
	}
	
	public int getY()
	{ 
		return y;
	}
	
	public void setX(int x){
		this.x = x;
	}
	
	public void setY(int y){
		this.y = y;
	}
	
	public void setCoords(int x, int y){
		setX(x);
		setY(y);
	}
}
