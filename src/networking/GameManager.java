package networking;

public class GameManager implements IGameManager{

	/**
	 * @param args
	 */
	public static void main(String[] args){
		// Create connections to central game system
		// Make sure to use the envelope for pub/sub
		
		//Establish pub/sub ZeroMQ connection to central game system (State Channel)
		//Establish request/response ZeroMQ connection to the central game system (Command Channel)
		
		// Connect to the match and obtain client token
		// Note: The match token is provided outside of these channels
		
		//Frame connect message
		//Send connect message over command channel
		//Receive and store client token
		
		// Wait for game to start
		
		//Monitor state channel for game board state matching this game
		
		
		// Play the game (intentionally vague)
		//Monitor the state channel for information
		//Determine moves for each piece
		//Frame game move messages and send over command channel
	}

	@Override
	public void CreateConnections() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void createMatch() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void startGame() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void monitorStateChannel() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void determineMove() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sendMove() {
		// TODO Auto-generated method stub
		
	}

}
