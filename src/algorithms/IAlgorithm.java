package algorithms;

import blocks.IBlock;

public interface IAlgorithm {
	void setCurrentPiece(IBlock incomingPiece);
	void setNextPiece(IBlock nextPiece);
	void calculateImminentMove();
}
