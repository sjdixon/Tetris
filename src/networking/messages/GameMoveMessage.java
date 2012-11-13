package networking.messages;

import networking.NetworkingInformation;

import com.google.gson.Gson;

public class GameMoveMessage implements IOutboundMessage {
	private String comm_type;
	private String client_token;
	private String move;
	
	public String createMessage(){
		String message = new Gson().toJson(this);
		return message;
	}
	
	public GameMoveMessage(String move){
		NetworkingInformation instance = NetworkingInformation.getInstance();
		comm_type = "GameMove";
		this.client_token = instance.getClient_token();
		this.move = move;
	}
}
