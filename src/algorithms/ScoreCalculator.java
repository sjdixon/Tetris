package algorithms;

import blocks.IBlock;
import data.IWell;
import data.LuckysScoringHelper;
import data.IChromosome;
import data.IScoreHelper;

public class ScoreCalculator implements ICalculator{

	protected IScoreHelper helper;
	protected IWell well;
	protected IChromosome chromosome;
	
	public ScoreCalculator(IWell well, IChromosome chrosome){
		this.well = well;
		this.chromosome = chrosome;
		helper = new LuckysScoringHelper(well);
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
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double calculateWallBonus(IBlock b) {
		// TODO Auto-generated method stub
		return 0;
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
	public double calculateScore() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public IChromosome getChromosome() {
		return chromosome;
	}

}
