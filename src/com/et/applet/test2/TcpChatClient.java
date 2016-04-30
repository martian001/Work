package com.et.applet.test2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * @author: SpringXian 2014-5-27 下午9:35:05
 */
public class TcpChatClient {
	public static void main(String[] args) throws UnknownHostException, IOException {
		Socket s = new Socket("localhost", 30000);
		new Thread(new TcpChatClientThread(s)).start();
		PrintStream ps = new PrintStream(s.getOutputStream());
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String content = "";
		while ((content = br.readLine()) != null) {
			ps.println(content);
		}
	}
}

class TcpChatClientThread implements Runnable {
	private Socket s;
	private BufferedReader br;

	public TcpChatClientThread(Socket s) {
		this.s = s;
		try {
			br = new BufferedReader(new InputStreamReader(s.getInputStream()));
		} catch (IOException e) {
			System.out.println("退出聊天");
		}
	}

	@Override
	public void run() {
		String content = "";
		try {
			while ((content = br.readLine()) != null) {
				System.out.println(content);
			}
		} catch (IOException e) {
			System.out.println("退出线程");
		}
	}

}
