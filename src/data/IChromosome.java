package data;

public interface IChromosome{
	// Methods Related To its Role in Genetics
	public void setFitnessThreshold(double score);
	public boolean passesFitnessThreshold();
	public IChromosome produceOffspring(IChromosome mate);
	public IChromosome mutate();
	public double getScore();
	public void setScore(double score);
	
	// Coefficients For Managing the Array
	public static final int HEIGHT = 0;
	public static final int ROWCLEAR = 1;
	public static final int BLOCKADE = 2;
	public static final int HOLE = 3;
	public static final int FLOOR = 4;
	public static final int WALL = 5;
	public static final int NUM_COEFFICIENTS = 6;
	public static final double RANGE = 10;
	
	// Methods related to the data it must hold
	public double getHeightCoefficient();
	public double getBlockadeCoefficient();
	public double getHoleCoefficient();
	public double getRowClearanceCoefficient();
	public double getWallCoefficient();
	public double getFloorCoefficient();
}
