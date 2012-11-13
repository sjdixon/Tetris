package networking;

public interface IGameManager {
	/**
	 * Create connections to central game system
	 * Make sure to use the envelope for pub/sub
	 * 
	 * Establish pub/sub ZeroMQ connection to central game system (State Channel)
	 * Establish request/response ZeroMQ connection to the central game system (Command
Channel)
	 */
	public void CreateConnections();
	
	// Connect to the match and obtain client token
	public void createMatch();
	
	// Wait for Game to Start
	public void startGame();
	
	// Play the Game
	public void monitorStateChannel();
	public void determineMove();
	public void sendMove();
}
