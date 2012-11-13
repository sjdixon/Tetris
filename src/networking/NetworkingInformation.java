package networking;

public class NetworkingInformation {
	
	static private NetworkingInformation instance;
	public static NetworkingInformation getInstance(){
		if(instance==null){
			synchronized(NetworkingInformation.class){
				if(instance==null){
					instance = new NetworkingInformation();
				}
			}
		}
		return instance;
	}
	
	private NetworkingInformation(){
		
	}

	private String team_name = "Team 126";
	private String password = "C11h22.o12";
	private String server = "";
	private String match_token;
	private String client_token;
	
	public String getClient_token() {
		return client_token;
	}

	public void setClient_token(String client_token) {
		this.client_token = client_token;
	}

	public String getTeam_name() {
		return team_name;
	}
	public String getPassword() {
		return password;
	}
	public String getServer() {
		return server;
	}
	public String getMatch_token() {
		return match_token;
	}
	public void setTeam_name(String team_name) {
		this.team_name = team_name;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setServer(String server) {
		this.server = server;
	}
	public void setMatch_token(String match_token) {
		this.match_token = match_token;
	}

	
}
