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
		private static int piece_number = 0;

		class CommType {
			private static final String MATCH_CONNECT = "MatchConnect";
			private static final String GAME_MOVE = "GameMove";
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
	
	public String createGameMoveCommand(String move){
		JSONObject json = new JSONObject();
		
		try{
			json.put(Key.COMM_TYPE, Key.CommType.GAME_MOVE);
			json.put(Key.CLIENT_TOKEN, Client.gameInfo.getClientToken());
			json.put(Key.MOVE, move);
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