package algorithms;

import blocks.Cell;
import blocks.IBlock;
import data.IChromosome;
import data.IScoreHelper;
import data.IWell;
import data.LuckysScoringHelper;

// Inspired by http://luckytoilet.wordpress.com/2011/05/27/coding-a-tetris-ai-using-a-genetic-algorithm/

public class LuckysAlgorithm implements IAlgorithm {
	protected IBlock incomingPiece;
	protected IBlock nextPiece;
	protected IWell well;
	protected ICalculator calculator;
	
	public LuckysAlgorithm(IWell well, IChromosome chromosome){
		this.well = well;
		calculator = new ScoreCalculator(well, chromosome);
		incomingPiece = null;
		nextPiece = null;
	}
	
	@Override
	public void setCurrentPiece(IBlock incomingPiece) {
		this.incomingPiece = incomingPiece;
	}
	@Override
	public void setNextPiece(IBlock nextPiece) {
		this.nextPiece = nextPiece;
	}
	
	@Override
	public IBlock calculateBestMove() {
		IBlock move = null;
		IBlock bestMove = null;
		double bestScore = Double.NEGATIVE_INFINITY;
		
		return null;
	}

	@Override
	public void setChromosome(IChromosome chromosome) {
		calculator = new ScoreCalculator(well, chromosome);
	}

	@Override
	public IChromosome getChromosome() {
		return calculator.getChromosome();
	}
		

}
