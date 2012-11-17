package data;

import java.util.Random;

public class Chromosome implements IChromosome{
	protected static double fitnessThreshold;
	protected double score;
	protected Random generator;
	public double mutationProbability;
	
	
	
	protected double[] coefficients;
	
	public Chromosome(double[] coefficients){
		generator = new Random();
		mutationProbability = 0.2;
		assert(coefficients.length==NUM_COEFFICIENTS);
		this.coefficients = new double[NUM_COEFFICIENTS];
		for(int i=0; i < NUM_COEFFICIENTS; i++){
			this.coefficients[i] = coefficients[i];
		}
		score = 0;
	}
	
	@Override
	public void setFitnessThreshold(double score) {
		synchronized(Chromosome.class)
		{
			fitnessThreshold = score;
		}
	}

	@Override
	public boolean passesFitnessThreshold() {
		synchronized(Chromosome.class)
		{
			if(this.score >= fitnessThreshold)
				return true;
			else return false;
		}
	}

	@Override
	public IChromosome produceOffspring(IChromosome mate) {
		double rand = generator.nextDouble();
		IChromosome offSpring;
		if(rand < mutationProbability)
			offSpring = mutate();
		else {
			double avgHeight = avg(mate.getHeightCoefficient(),this.getHeightCoefficient());
			double avgHoles = avg(mate.getHoleCoefficient(), this.getHoleCoefficient());
			double avgBlockades = avg(mate.getBlockadeCoefficient(), this.getBlockadeCoefficient());
			double avgWall = avg(mate.getWallCoefficient(), this.getWallCoefficient());
			double avgFloor = avg(mate.getFloorCoefficient(), this.getFloorCoefficient());
			double avgRowClear = avg(mate.getRowClearanceCoefficient(), this.getRowClearanceCoefficient());
			
			double[] newCoefficients = new double[NUM_COEFFICIENTS];
			newCoefficients[HEIGHT] = avgHeight;
			newCoefficients[HOLE] = avgHoles;
			newCoefficients[BLOCKADE] = avgBlockades;
			newCoefficients[WALL] = avgWall;
			newCoefficients[FLOOR] = avgFloor;
			newCoefficients[ROWCLEAR] = avgRowClear;
			
			offSpring = new Chromosome(newCoefficients);
		}
		return offSpring;
	}
	
	
	protected double avg(double num1, double num2){
		return (num1+num2)/2;
	}

	@Override
	public IChromosome mutate() {
		double[] newCoefficients = new double[NUM_COEFFICIENTS];
		newCoefficients[HEIGHT] = -1 * generator.nextDouble() * RANGE;
		newCoefficients[HOLE] = -1 * generator.nextDouble() * RANGE;
		newCoefficients[BLOCKADE] = -1 * generator.nextDouble() * RANGE;
		newCoefficients[WALL] = generator.nextGaussian() * RANGE;
		newCoefficients[FLOOR] = generator.nextGaussian() * RANGE;
		newCoefficients[ROWCLEAR] = generator.nextDouble() * RANGE;
		IChromosome offSpring = new Chromosome(newCoefficients);
		return offSpring;
	}

	@Override
	public double getScore() {
		// TODO Auto-generated method stub
		return score;
	}

	@Override
	public void setScore(double score) {
		this.score = score;
	}

	@Override
	public double getHeightCoefficient() {
		return coefficients[HEIGHT];
	}

	@Override
	public double getBlockadeCoefficient() {
		return coefficients[BLOCKADE];
	}

	@Override
	public double getHoleCoefficient() {
		return coefficients[HOLE];
	}

	@Override
	public double getRowClearanceCoefficient() {
		return coefficients[ROWCLEAR];
	}

	@Override
	public double getWallCoefficient() {
		return coefficients[WALL];
	}

	@Override
	public double getFloorCoefficient() {
		return coefficients[FLOOR];
	}

	@Override
	public void setCoefficient(int coef, double value){
		if(coef >= 0 && coef < NUM_COEFFICIENTS){
			coefficients[coef] = value;
		}
	}
}
