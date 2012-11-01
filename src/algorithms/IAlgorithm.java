package algorithms;

import blocks.IBlock;
import data.IChromosome;

public interface IAlgorithm {
	void setCurrentPiece(IBlock incomingPiece);
	void setNextPiece(IBlock nextPiece);
	IBlock calculateBestMove();
	void setChromosome(IChromosome chromosome);
	IChromosome getChromosome();
}
