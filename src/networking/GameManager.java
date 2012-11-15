package networking;
import networking.messages.IOutboundMessage;
import networking.messages.MatchConnect;

import org.zeromq.ZMQ;
import org.zeromq.ZMQ.*;

public class GameManager implements IGameManager{
	Socket commandChannel = null;
	Socket stateChannel = null;

	public String CreateConnections(String server) {
		Context context = ZMQ.context(1);
		Socket socket = context.socket(ZMQ.REQ);
		String serverAddress = "tcp://" + server;
		System.out.println("Connecting to target server, " + serverAddress);
		socket.connect(serverAddress);
		
		IOutboundMessage matchConnect = new MatchConnect();
		String message = matchConnect.createMessage() + " ";
		byte[] byteMessage = message.getBytes();
		byteMessage[byteMessage.length-1] = 0;
		socket.send(byteMessage, 0);
		
		byte[] reply = socket.recv(0);
		String replyMessage = new String(reply, 0, reply.length-1);
		// Teardown connection
		socket.close();
		context.term();
		return replyMessage;
	}

	
	public void createMatch() {
		
	}

//	@Override
	public void startGame() {
	
	}

	//@Override
	public void monitorStateChannel() {
		// TODO Auto-generated method stub
		
	}

	//@Override
	public void determineMove() {
		// TODO Auto-generated method stub
		
	}

	//@Override
	public void sendMove() {
		// TODO Auto-generated method stub
		
	}

}
