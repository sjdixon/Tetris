package data;

public class LuckysWell extends Well implements IScoreHelper{

	public LuckysWell(int height, int width) {
		super(height, width);
	}

	@Override
	public int countHolesBelowCell(int r, int c) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int countBlockadesBelowCell(int r, int c) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int countAdjacentBlocks(int r, int c) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isCellAdjacentToWall(int r, int c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isCellAdjacentToFloor(int r, int c) {
		// TODO Auto-generated method stub
		return false;
	}
	

}
