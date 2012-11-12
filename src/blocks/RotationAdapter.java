package blocks;
import data.IWell;

public class RotationAdapter implements IRotation{
	protected IBlock rotatedPiece;
	protected IWell well;
	
	public RotationAdapter(IWell well){
		this.well = well;
	}
	
	public IBlock rotateLeft(IBlock b){
		Class<? extends IBlock> blockClass = b.getClass();
		IBlock block = createBlock(blockClass);
		int nextRotation = nextRotation(b, false);
		b.setRotation(nextRotation);
		Cell[] pieces = b.rotateLeft();
		block.setPoints(pieces);
		block.setRotation(nextRotation);
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
		Class<? extends IBlock> blockClass = b.getClass();
		IBlock block = createBlock(blockClass);
		int nextRotation = nextRotation(b, true);
		b.setRotation(nextRotation);
		Cell[] pieces = b.rotateRight();
		block.setPoints(pieces);
		block.setRotation(nextRotation);
		return block;
	}
	
	
	public boolean areBlocksEqual(IBlock b1, IBlock b2){
		boolean areEqual = true;
		if(b1.getClass()!=b2.getClass())
			areEqual = false;
		else if(b1.getCells().length != b2.getCells().length){
			areEqual = false;
		}
		else if(b1.getRotation()!=b2.getRotation())
			areEqual = false;
		else {
			for(int i=0; i < b1.getCells().length; i++){
				Cell c1 = b1.getCells()[i];
				Cell c2 = b2.getCells()[i];
				if(c1.equals(c2)==false){
					areEqual=false;
					break;
				}
			}
		}
		return areEqual;
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
