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
		double score = 0;
		Cell[] cells = b.getCells();
		for(int i=0; i < cells.length; i++){
			score += cells[i].getRow() * chromosome.getHeightCoefficient();
		}
		return score;
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
		double score = 0;
		Cell[] cells = b.getCells();
		for(int i=0; i < cells.length; i++){
			int numBlockades = helper.countBlockades(cells[i]);
			score += chromosome.getBlockadeCoefficient() * numBlockades;
		}
		return score;
	}

	@Override
	public double calculateHoleDeduction(IBlock b) {
		double score = 0;
		Cell[] cells = b.getCells();
		// Count the number of holes in each column exactly once
		int[] uniqueColumns = new int[cells.length];
		uniqueColumns[0] = cells[0].getColumn();
		int numHoles = countHoles(b);
		score = numHoles * chromosome.getHoleCoefficient();
		return score;
	}
	
	public int countHoles(IBlock b){
		// First, calculate the lowest row of each unique column
		int[] uniqueColumns = new int[4];
		int[] lowestRow = new int[4];
		Cell[] cells = b.getCells();
		int k=1;
		uniqueColumns[0] = cells[0].getColumn();
		lowestRow[0] = cells[0].getRow();
		for(int i=1; i < uniqueColumns.length; i++){
			uniqueColumns[i] = -1;
			int current = cells[i].getColumn();
			boolean isUnique = true;
			for(int j=0; j < k; j++){
				if(current==uniqueColumns[j]){
					isUnique = false;
					if(lowestRow[j] > cells[i].getRow())
						lowestRow[j] = cells[i].getRow();
					break;
				}
			}
			if(isUnique==true){
				uniqueColumns[k] = current;
				lowestRow[k] = cells[i].getRow();
				k++;
			}
		}
		// Count the number of holes under the lowest cell within each unique column
		int numHoles = 0;
		for(int i=0; i < k; i++){
			Cell arg = new Cell(uniqueColumns[i], lowestRow[i]);
			numHoles += helper.countHolesBelowCell(arg);
		}
		return numHoles;
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
