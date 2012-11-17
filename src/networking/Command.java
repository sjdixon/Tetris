package networking;

import org.json.JSONObject;
import org.json.JSONException;

final class Command
{
	class Key {
		private static final String TEAM_NAME = "team_name";
		public static final String CLIENT_TOKEN = "client_token";
		private static final String COMM_TYPE = "comm_type";
		private static final String MATCH_TOKEN = "match_token";
		private static final String PASSWORD = "password";
		private static final String MOVE = "move";
		private static final String PIECE_NUMBER = "piece_number";
		private static final String RESP = "resp";
		private static final String MESSAGE = "message";
		private static final String SERVER_IP = "server_ip";
		private static final String STATUS = "status";
		private static final String REASON = "reason";
		private static final String ERROR = "error";

		class CommType {
			private static final String MATCH_CONNECT = "MatchConnect";
			private static final String GAME_MOVE = "GameMove";
			private static final String GAME_MOVE_RESP = "GameMoveResp";
			
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