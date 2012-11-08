package networking;

import org.zeromq.*;

public class Main {
	
	public static void main(String[] args){
		ZMQ.Context context = ZMQ.context(1);
		ZMQ.Socket publisher = context.socket(ZMQ.PUB);

		// Subscriber tells us when it's ready here
		ZMQ.Socket sync = context.socket(ZMQ.PULL);

		sync.bind("tcp://*:5564");

		// We send updates via this socket
		publisher.bind("tcp://*:5565");

		// Wait for synchronization request
		sync.recv(0);

		// Now broadcast exactly 10 updates with pause
		for (int i = 0; i < 10; i++) {
			String msg = String.format("Update %d", i);
			publisher.send(msg.getBytes(), 0);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				System.err.println("Point A"+i);
				e.printStackTrace();
			}
		}
		publisher.send("END".getBytes(), 0);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			System.err.println("Final Interrupted Exception");
			e.printStackTrace();
		} // Give 0MQ/2.0.x to flush output
	}
}
