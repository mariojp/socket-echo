package br.com.mariojp.project;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {

	private Socket clientSocket;
	private PrintWriter out;
	private BufferedReader in;

	public void startConnection(String ip, int port) {
		try {
			clientSocket = new Socket(ip, port);
			out = new PrintWriter(clientSocket.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public String sendMessage(String message) {
		out.println(message);
		String response = null;
		try {
			response = in.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return response;
	}

	public void stopConnection() {
		try {
			in.close();
			out.close();
			clientSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public static void main(String[] args) {
		Client client = new Client();
		client.startConnection("localhost", 4444);
		System.out.println(client.sendMessage("Hello"));
		System.out.println(client.sendMessage("Java is Very Good!!!"));
		System.out.println(client.sendMessage("Amazing!!!"));
		System.out.println(client.sendMessage("."));
		client.stopConnection();
	}

}
