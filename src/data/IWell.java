package data;

public interface IWell {
	boolean fillCell(int r, int c);
	boolean emptyCell(int r, int c);
	boolean isCellEmpty(int r, int c);
	boolean isCellFull(int r, int c);
	boolean deleteRow(int row);
	boolean isRowFull(int r);
	boolean doesBlockFit(IBlock b);
	boolean[][] getCells();

}
