package algorithms;

import blocks.IBlock;

// Inspired by http://luckytoilet.wordpress.com/2011/05/27/coding-a-tetris-ai-using-a-genetic-algorithm/

public class LuckysAlgorithm implements IAlgorithm {
	protected double[] coefficients;
	protected IBlock incomingPiece;
	protected IBlock nextPiece;
	
	
	public LuckysAlgorithm(double[] coeffecients){
		this.coefficients = coefficients;
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
	public void calculateImminentMove() {
		
	}
	
	public double calculateCellScore(int r, int c){
		double score = 0;
		return score;
	}

}
