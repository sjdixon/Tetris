package networking.responses;

import com.google.gson.Gson;

public class ServerErrorResponse implements IServerResponse {
	private String fullMessage;
	
	public ServerErrorResponse(String response){
		fullMessage = response;
	}

	@Override
	public String getMessage() {
		return fullMessage;
	}

	@Override
	public String getType() {
		return "Error";
	}

}
