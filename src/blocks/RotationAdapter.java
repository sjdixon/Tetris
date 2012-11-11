package blocks;
import data.IWell;

public class RotationAdapter implements IRotation{
	protected IBlock rotatedPiece;
	protected IWell well;
	
	public RotationAdapter(IWell well){
		this.well = well;
	}
	
	public IBlock rotateLeft(IBlock b){
		Cell[] pieces = b.rotateLeft();
		Class<? extends IBlock> blockClass = b.getClass();
		IBlock block = createBlock(blockClass, pieces);
		return block;
	}
	
	public IBlock rotateRight(IBlock b){
		Cell[] pieces = b.rotateRight();
		Class<? extends IBlock> blockClass = b.getClass();
		IBlock block = createBlock(blockClass, pieces);
		return block;
		
	}
	
	public IBlock createBlock(Class<? extends IBlock> blockClass, Cell[] cells){
		String name = blockClass.getName();
		IBlock creation = null;
		switch(name){
		case "J_Block":
			creation = new J_Block(well);
			break;
		case "L_Block":
			creation = new L_Block(well);
			break;
		case "S_Block":
			creation = new S_Block(well);
			break;
		case "T_Block":
			creation = new T_Block(well);
			break;
		case "Z_Block":
			creation = new Z_Block(well);
			break;
		case "I_Shaped_Block":
			creation = new I_Shaped_Block(well);
			break;
		case "Square":
			creation = new Square(well);
			break;
		}
		creation.setPoints(cells);
		return null;
	}

}
