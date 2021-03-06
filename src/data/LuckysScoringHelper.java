package data;

public class LuckysScoringHelper implements IScoreHelper{
	protected static IWell cellWell;
	
	public LuckysScoringHelper(IWell well){
		synchronized(LuckysScoringHelper.class){
			cellWell = well;
		}
	}

	@Override
	/** Preconditions:
	 *  1) The cell at (@param r, @param c) can be considered tentatively filled.
	 *  2) The cell at (@param r, @param c) is the lowermost cell of the current block for column @param c.
	 */
	public int countHolesBelowCell(int r, int c) {
		int holes = 0;
		int columnHeight = calculateColumnHeight(c);
		for(int i=columnHeight-1; i>=0; i--)
			if(cellWell.isCellEmpty(i, c)==true)
				holes++;
		return holes;
	}

	@Override
	/** Preconditions:
	 *  1) The cell at (@param r, @param c) can be considered tentatively filled.
	 *  2) The cell at (@param r, @param c) is the lowermost cell of the current block for column @param c.
	 *  
	 *  Expected Result:
	 *  
	 *  This function counts the number of filled cells starting from the lowermost hole in column @param c,
	 *  including the cell at row @param r.
	 */
	public int countBlockades(int r, int c) {
		int blockades = 0;
		boolean isHolePresent = false;
		for(int i=0; i < r; i++){
			// Detect the first hole
			if(cellWell.isCellEmpty(i, c)==true)
				isHolePresent = true;
			// Any filled cell above the first hole is a blockade, including (r,c)
			if(isHolePresent==true && cellWell.isCellFull(i, c)==true)
				blockades++;
		}
		return blockades;
	}

	//@Override
	public int countAdjacentBlocks(int r, int c) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isCellAdjacentToWall(int r, int c) {
		// TODO Auto-generated method stub
		if(c==0 || c == cellWell.getWidth()-1)
			return true;
		else return false;
	}

	@Override
	public boolean isCellAdjacentToFloor(int r, int c) {
		// TODO Auto-generated method stub
		int lowestEmptyRow = calculateColumnHeight(c);
		if(lowestEmptyRow==r)
			return true;
		else return false;
	}
	
	@Override
	/** 
	 * This function returns the row number above the highest occupied cell in a column.
	 * ie. if the highest cell is in row 2, this function returns 3.
	 */
	public int calculateColumnHeight(int column){
		int height = 0;
		for(int i=cellWell.getHeight()-1; i>=0; i--){
			if(cellWell.isCellFull(i, column)==true){
				height = i + 1;
				break;
			}
		}
		return height;
	}

}
