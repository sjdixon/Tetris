package networking;

import org.json.JSONObject;
import org.json.JSONException;

public final class Command
{
	public class Key {
		public static final String TEAM_NAME = "Team 126";
		public static final String CLIENT_TOKEN = "client_token";
		public static final String COMM_TYPE = "comm_type";
		public static final String MATCH_TOKEN = "match_token";
		public static final String PASSWORD = "C11h22.o12";
		public static final String MOVE = "move";
		public static final String PIECE_NUMBER = "piece_number";
		public static final String RESP = "resp";
		public static final String MESSAGE = "message";
		public static final String SERVER_IP = "server_ip";
		public static final String STATUS = "status";
		public static final String REASON = "reason";
		public static final String ERROR = "error";

		public class CommType {
			public static final String MATCH_CONNECT = "MatchConnect";
			public static final String GAME_MOVE = "GameMove";
			public static final String GAME_MOVE_RESP = "GameMoveResp";
			public static final String MATCH_END = "MatchEnd";
			public static final String GAME_PIECE_STATE="GamePieceState";
			public static final String GAME_END = "GameEnd";
			public static final String GAME_BOARD_STATE = "GameBoardState";
		}
	}

	public String getMatchConnectCommand(String teamName, String matchToken, String teamPassword)
	{
		JSONObject json = new JSONObject();

		try {
			json.put(Key.COMM_TYPE, Key.CommType.MATCH_CONNECT);
			json.put(Key.TEAM_NAME, teamName);
			json.put(Key.MATCH_TOKEN, matchToken);
			json.put(Key.PASSWORD, teamPassword);
		} catch(JSONException e) {
			System.err.println("[Command connectCommand] couldn't create command");
			return null;
		}

		return json.toString();
	}
	
	protected static int sequenceNumber;
	
	public String createGameMoveCommand(String move){
		JSONObject json = new JSONObject();
		
		try{
			json.put(Key.COMM_TYPE, Key.CommType.GAME_MOVE);
			json.put(Key.CLIENT_TOKEN, Client.gameInfo.getClientToken());
			json.put(Key.MOVE, move);
			//json.put(Key.PIECE_NUMBER, sequenceNumber);
		}
		catch(JSONException e){
			
		}
		return json.toString();
	}
	
	public String[] createMovesForBlock(String path){
		String[] moves = path.split(" ");
		String[] moveCommands = new String[moves.length];
		for(int i=0; i < moves.length; i++){
			moveCommands[i] = createGameMoveCommand(moves[i]);
		}
		return moveCommands;
	}
	
	
}