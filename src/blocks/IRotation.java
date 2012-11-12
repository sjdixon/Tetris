package blocks;

public interface IRotation {
	public IBlock rotateLeft(IBlock rotated);
	public IBlock rotateRight(IBlock rotated);
	public IBlock createBlock(Class<? extends IBlock> className);
	//public boolean areBlocksEqual(IBlock b1, IBlock b2);
	public int nextRotation(IBlock b, boolean rotateRight);
}
