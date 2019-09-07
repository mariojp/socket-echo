package br.com.mariojp.project;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;



/**
 * Unit test for simple App.
 */
public class AppTest {
   
	Server server;

  
    @BeforeEach
	void init() {
		new Thread(new Runnable() {
			public void run() {
    			server = new Server();
				server.start(4444);
				server.stop();
			}
		}).start();
	}
	
	@AfterEach
	void tearDown() {
		server.stop();
	}
	

    @Test
    public void testClientAndServerRespondMessage(){
		Client client = new Client();
		client.startConnection("127.0.0.1", 4444);
		String response1 = client.sendMessage("Hello");
		String response2 = client.sendMessage("Java is Very Good!!!");
		String response3 = client.sendMessage("Amazing!!!");
		String response4 = client.sendMessage(".");
		
		client.stopConnection();
    	assertEquals("Hello", response1);
    	assertEquals("Java is Very Good!!!", response2);
    	assertEquals("Amazing!!!", response3);
    	assertEquals("bye!", response4);


    }
}
