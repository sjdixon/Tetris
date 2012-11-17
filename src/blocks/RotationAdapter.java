package blocks;
import data.IWell;

public class RotationAdapter implements IRotation{
	protected IBlock rotatedPiece;
	protected IWell well;
	
	public RotationAdapter(IWell well){
		this.well = well;
	}
	
	public IBlock rotateLeft(IBlock b){
		// Create the block to rotate
		Class<? extends IBlock> blockClass = b.getClass();
		IBlock block = createBlock(blockClass);
		int nextRotation = nextRotation(b, false);
		
		//Rotate the block
		block.setPoints(b.getCells());
		block.setRotation(nextRotation);
		Cell[] newCells = block.rotateLeft();
		block.setPoints(newCells);
		block.setPath(b.getPath());
		block.appendPath("lrotate");
		return block;
	}
	
	public int nextRotation(IBlock b, boolean rotateRight){
		int initialRotation = b.getRotation();
		int nextRotation;
		if(rotateRight==true){
			nextRotation = (initialRotation+3)%b.numRotations();
		}
		else {
			nextRotation = (initialRotation+1)%b.numRotations();
		}
		return nextRotation;
	}
	
	public IBlock rotateRight(IBlock b){
		// Create the block to rotate
		Class<? extends IBlock> blockClass = b.getClass();
		IBlock block = createBlock(blockClass);
		int nextRotation = nextRotation(b, true);
		
		//Rotate the block
		block.setPoints(b.getCells());
		block.setRotation(nextRotation);
		Cell[] newCells = block.rotateRight();
		block.setPoints(newCells);
		block.setPath(b.getPath());
		block.appendPath("rrotate");
		
		return block;
	}
	
	public IBlock createBlock(Class<? extends IBlock> blockClass){
		String name = blockClass.getName();
		IBlock creation = null;
		switch(name){
		case "blocks.J_Block":
			creation = new J_Block(well);
			break;
		case "blocks.L_Block":
			creation = new L_Block(well);
			break;
		case "blocks.S_Block":
			creation = new S_Block(well);
			break;
		case "blocks.T_Block":
			creation = new T_Block(well);
			break;
		case "blocks.Z_Block":
			creation = new Z_Block(well);
			break;
		case "blocks.I_Shaped_Block":
			creation = new I_Shaped_Block(well);
			break;
		case "blocks.Square":
			creation = new Square(well);
			break;
		default:
			creation = null;
			break;
		}
		return creation;
	}

}
