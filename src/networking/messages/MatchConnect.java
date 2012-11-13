package networking.messages;

import networking.NetworkingInformation;

import com.google.gson.Gson;

public class MatchConnect implements IOutboundMessage{
	private String comm_type = "MatchConnect";
	private String match_token;
	private String team_name;
	private String password;
	
	public String createMessage(){
		String message = new Gson().toJson(this);
		return message;
	}
	
	public MatchConnect(){
		NetworkingInformation instance = NetworkingInformation.getInstance();
		match_token = instance.getMatch_token();
		team_name = instance.getTeam_name();
		password = instance.getPassword();
	}
}
