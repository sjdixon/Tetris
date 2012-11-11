package data;

import blocks.IBlock;

public interface IWell {
	boolean fillCell(int r, int c);
	boolean emptyCell(int r, int c);
	boolean isCellEmpty(int r, int c);
	boolean isCellFull(int r, int c);
	boolean clearRow(int row);
	boolean isRowFull(int r);
	boolean isCellWithinBounds(int r, int c);
	boolean[][] getCells();
	int getWidth();
	void readHexadecimalBoard(String hexadecimal);
	int getHeight();
}
