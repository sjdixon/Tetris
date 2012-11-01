package algorithms;

import data.IChromosome;
import blocks.IBlock;

public interface ICalculator {
	public double calculateHeightDeduction(IBlock b);
	public double calculateRowClearanceBonus(IBlock b);
	public double calculateFloorBonus(IBlock b);
	public double calculateWallBonus(IBlock b);
	public double calculateBlockadeDeduction(IBlock b);
	public double calculateHoleDeduction(IBlock b);
	public double calculateScore();
	public IChromosome getChromosome();
}
