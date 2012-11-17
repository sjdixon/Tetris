package networking;

import org.json.JSONException;
import org.json.JSONObject;

import blocks.I_Shaped_Block;
import blocks.J_Block;
import blocks.L_Block;
import blocks.S_Block;
import blocks.Square;
import blocks.T_Block;
import blocks.Z_Block;

public class MessageManager {
	

	public MessageManager(){
		
	}
	
	public static void interpretMessage(String message){
		try {
			JSONObject json = new JSONObject(message);
			String comm;
			comm = json.getString("comm_type");
			switch(comm){
			case Command.Key.CommType.GAME_PIECE_STATE:
				interpretGamePiece(message);
				break;
			case Command.Key.CommType.MATCH_END:
				interpretMatchEnd(message);
				break;
			case Command.Key.CommType.GAME_END:
				interpretGameEnd(message);
				break;
			case Command.Key.CommType.GAME_BOARD_STATE:
				interpretBoard(message);
				break;
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void interpretBoard(String message){
		
	}

	public static void interpretGameEnd(String message){
		Client.gameOver = true;
	}
	
	public static void interpretGamePiece(String message){
		JSONObject json;
		try {
			json = new JSONObject(message);
			JSONObject myPiece = json.getJSONObject("Team 126");
			String piece = myPiece.getString("Team 126");
			char letter = piece.charAt(0);
			switch(letter){
			case 'T':
				Client.currentPiece = new T_Block(Client.well);
				break;
			case 'S':
				Client.currentPiece = new S_Block(Client.well);
				break;
			case 'Z':
				Client.currentPiece = new Z_Block(Client.well);
				break;
			case 'I':
				Client.currentPiece = new I_Shaped_Block(Client.well);
				break;
			case 'L':
				Client.currentPiece = new L_Block(Client.well);
				break;
			case 'J':
				Client.currentPiece = new J_Block(Client.well);
				break;
			case 'O':
				Client.currentPiece = new Square(Client.well);
				break;
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public static void interpretMatchEnd(String message){
		Client.gameOver = true;
		return;
	}
}
