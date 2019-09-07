package br.com.mariojp.project;

/**
 * Hello world!
 *
 */
public class App {
	
	public static void main(String args[]) {

		//Start Server
		new Thread(new Runnable() {
			public void run() {
				System.out.println("Start Server");
				Server server = new Server();
				server.start(4444);
				server.stop();
			}
		}).start();

		
		//Start Client
		Client client = new Client();
		client.startConnection("localhost", 4444);
		System.out.println(client.sendMessage("Hello"));
		System.out.println(client.sendMessage("Java is Very Good!!!"));
		System.out.println(client.sendMessage("Amazing!!!"));
		System.out.println(client.sendMessage("."));
		client.stopConnection();
		
	}
	
}
