package tetris.client.stub;

import org.zeromq.ZMQ;

import algorithms.IAlgorithm;
import algorithms.LuckysAlgorithm;

import data.Chromosome;
import data.IChromosome;
import data.IWell;
import data.Well;

final class Client
{
	public static void main(String[] args)
	{
		Client.run(args);
	}

	public static void run(String[] args)
	{
		String teamName = null;
		String matchToken = null;
		String teamPassword = null;
		String hostName = null;

		if(args.length < 4) {
			Client.printHelp();
			return;
		}

		teamName = args[0];
		teamPassword = args[1];
		matchToken = args[2];
		hostName = args[3];
		Communication.setHostName(hostName);

		System.out.println("Starting Battle Tetris Client...");

		Command command = new Command();

		// retrieve the command to connect to the server
		String connectCommand = command.getMatchConnectCommand(teamName, matchToken, teamPassword);

		// retrieve the communication singleton
		Communication comm = Communication.getInstance();
		comm.setSubscription(matchToken);

		// send the command to connect to the server
		System.out.println("Connecting to server...");
		String clientToken = comm.send(connectCommand, Command.Key.CLIENT_TOKEN);
		System.out.println("Received client token... " + clientToken);

		// the GameInfo object will hold the client's name, token, game type, etc.
		GameInfo gameInfo = new GameInfo(clientToken, teamName);

		///
		/// ADD ALGORITHM
		int numGames = 19;
		int numGamesWon = 0;
		int numGamesLost = 0;
		for(int game=0; game < numGames; game++){
			initializeAlgorithm();
			boolean gameOver = false;
			while(!gameOver){
				// Receive messages, send moves
				
			}
		}
		///

		System.out.println("Exiting...");
		System.out.println("Games Won: " + numGamesWon);
		System.out.println("Games Lost: " + numGamesLost);
	}

	public static void printHelp()
	{
			System.out.println("usage: Client <team-name> <team-password> <match-token> <host-name>");
	}
	
	protected static IWell well;
	protected static final int HEIGHT=20, WIDTH=10;
	protected static IAlgorithm algorithm;
	protected static IChromosome chromosome;
	
	public static void initializeAlgorithm(){
		well = new Well(HEIGHT, WIDTH);
		chromosome = createChromosome();
		algorithm = new LuckysAlgorithm(well, chromosome);
		
	}
	
	public static IChromosome createChromosome(){
		double[] coeffs = new double[6];
		coeffs[IChromosome.HEIGHT] = -8;
		coeffs[IChromosome.HOLE] = -1;
		coeffs[IChromosome.BLOCKADE] = -0.5;
		coeffs[IChromosome.FLOOR] = -0.3;
		coeffs[IChromosome.ROWCLEAR] = 8;
		coeffs[IChromosome.WALL] = 0;
		IChromosome chro = new Chromosome(coeffs);
		return chro;
	}
	
}
