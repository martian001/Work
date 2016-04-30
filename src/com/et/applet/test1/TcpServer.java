package com.et.applet.test1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;

/**
★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★
★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★
★☆            @author： The One                  ☆★
★☆            @time：2014年5月27日 下午9:29:33      ☆★
★☆            @version：1.0                      ☆★
★☆            @lastMotifyTime：                                                      ☆★
★☆            @ClassAnnotation：                                                   ☆★
★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★
★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★
 */
public class TcpServer {
	public static void main(String[] args) throws IOException {
		ArrayList<Socket> clients = new ArrayList<>();
		ServerSocket server = new ServerSocket(8888);
		while (true) {
			Socket client = server.accept();
			new Thread(new ServerThread(clients, client)).start();
			clients.add(client);
		}
	}
}

class ServerThread implements Runnable {
	ArrayList<Socket> clients;
	Socket client;

	public ServerThread(ArrayList<Socket> clients, Socket client) {
		this.clients = clients;
		this.client = client;
	}

	@Override
	public void run() {
		try {
			while (true) {
				try {
					DataInputStream dataInputStream = new DataInputStream(client.getInputStream());
					String content = dataInputStream.readUTF();
					for (Socket s : clients) {
						DataOutputStream dataOutputStream = new DataOutputStream(s.getOutputStream());
						dataOutputStream.writeUTF(content);
					}
				} catch (SocketException e) {
					clients.remove(client);
					break;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
