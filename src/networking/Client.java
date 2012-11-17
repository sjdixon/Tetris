package networking;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import networking.Communication.MessageOrigin;

import org.json.JSONException;
import org.json.JSONObject;

import algorithms.IAlgorithm;
import algorithms.LuckysAlgorithm;
import blocks.IBlock;
import data.Chromosome;
import data.IChromosome;
import data.IWell;
import data.Well;

final class Client
{
	public static GameInfo gameInfo;
	public static MessageManager manager = new MessageManager();
	public static boolean testMatch = true;
	public static boolean gameOver = false;

	public static IWell well;
	public static final int HEIGHT=20, WIDTH=10;
	public static IAlgorithm algorithm;
	public static IChromosome chromosome;
	public static IBlock currentPiece;
	
	public static void main(String[] args)
	{
		Client.run(args);
	}

	static String teamName = null;
	static String matchToken = null;
	static String teamPassword = null;
	static String hostName = null;

	public static void run(String[] args)
	{

		if(args.length < 4) {
			Client.printHelp();
			return;
		}

		teamName = args[0];
		teamPassword = args[1];
		matchToken = args[2];
		if(testMatch==true){
			createTestMatch();
			/*
			Scanner sc = new Scanner(System.in);
			System.out.println("Press enter");
			sc.next();*/
		}
		else hostName = args[3];
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
		gameInfo = new GameInfo(clientToken, teamName);

		///
		/// ADD ALGORITHM
		int numGames = 51;
		int numGamesWon = 0;
		int numGamesLost = 0;
		for(int game=0; game < numGames; game++){
			
			initializeAlgorithm();
			gameOver = false;
			while(!gameOver){
				// Receive messages, send moves
				// Listen for game state
				String message = comm.receive(MessageOrigin.PublishSocket);
				MessageManager.interpretMessage(message);
				System.out.println(message);
				
				message = comm.receive(MessageOrigin.PublishSocket);
				MessageManager.interpretMessage(message);
				System.out.println(message);
				
				algorithm.setCurrentPiece(currentPiece);
				IBlock bestMove = algorithm.calculateBestMove();
				String moves = bestMove.getPath();
				String[] gameMoveMessages = command.createMovesForBlock(moves);
				System.out.println(gameMoveMessages);
				for(String i : gameMoveMessages){
					//comm.send(i);
				}
				
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
	
	public static String getHTML(String urlToRead) {
	      URL url;
	      HttpURLConnection conn;
	      BufferedReader rd;
	      String line;
	      String result = "";
	      try {
	         url = new URL(urlToRead);
	         conn = (HttpURLConnection) url.openConnection();
	         conn.setRequestMethod("GET");
	         rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	         while ((line = rd.readLine()) != null) {
	            result += line;
	         }
	         rd.close();
	      } catch (Exception e) {
	    	 System.err.println(e.getMessage());
	         e.printStackTrace();
	      }
	      return result;
	   }
	
	public static void createTestMatch(){
		String url = "http://codingcontest.pason.com";
		String extra = "/scheduler/create_unauthenticated_test_match?team_name=team%20126&password=C11h22.o12";
		String result = getHTML(url+extra);
		System.out.println(result);
		
		try {
			JSONObject json = new JSONObject(result);
			matchToken = json.getString(Command.Key.CLIENT_TOKEN);
			hostName = json.getString(Command.Key.SERVER_IP);
			System.out.println(matchToken);
		} catch (JSONException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
		
	}
	
}
