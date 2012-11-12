package algorithms;

import blocks.Cell;
import blocks.IBlock;
import data.IScoreHelperAdapter;
import data.IWell;
import data.LuckysScoringHelper;
import data.IChromosome;
import data.IScoreHelper;
import data.ScoreHelperAdapter;

public class ScoreCalculator implements ICalculator{

	protected IScoreHelperAdapter helper;
	protected IWell well;
	protected IChromosome chromosome;
	
	public ScoreCalculator(IWell well, IChromosome chrosome){
		this.well = well;
		this.chromosome = chrosome;
		helper = new ScoreHelperAdapter(well);
	}
	
	@Override
	public double calculateHeightDeduction(IBlock b) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double calculateRowClearanceBonus(IBlock b) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double calculateFloorBonus(IBlock b) {
		double score = 0;
		Cell[] cells = b.getCells();
		for(int i=0; i < cells.length; i++){
			if(helper.isCellAdjacentToFloor(cells[i])==true)
				score += chromosome.getFloorCoefficient();
		}
		return score;
	}

	@Override
	public double calculateWallBonus(IBlock b) {
		double score = 0;
		Cell[] cells = b.getCells();
		for(int i=0; i < cells.length; i++){
			if(helper.isCellAdjacentToWall(cells[i])==true)
				score += chromosome.getWallCoefficient();
		}
		return score;
	}

	@Override
	public double calculateBlockadeDeduction(IBlock b) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double calculateHoleDeduction(IBlock b) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double calculateScore(IBlock b) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public IChromosome getChromosome() {
		return chromosome;
	}

}
