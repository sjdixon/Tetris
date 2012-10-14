package networking;

public interface IGameManager {
	void initializeGameRules();
	void connectToServer();
	void beginNewGame();
	void sendMove();
	void checkAcknowledgement();
}
