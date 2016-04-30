package com.et.applet.test1;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★
★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★
★☆            @author： The One                  ☆★
★☆            @time：2014年5月27日 下午9:39:56      ☆★
★☆            @version：1.0                      ☆★
★☆            @lastMotifyTime：                                                      ☆★
★☆            @ClassAnnotation：                                                   ☆★
★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★
★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★
 */
public class TcpClient {
	public static void main(String[] args) throws UnknownHostException, IOException {
		Socket client = new Socket(InetAddress.getByName("127.0.0.1"), 8888);
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		DataOutputStream dataOutputStream = new DataOutputStream(client.getOutputStream());
		new Thread(new SendMess(client)).start();
		while (true) {
			System.out.println("请输入：");
			dataOutputStream.writeUTF(br.readLine());
		}
	}
}

class SendMess implements Runnable {
	Socket client;

	public SendMess(Socket client) {
		this.client = client;
	}

	@Override
	public void run() {
		DataInputStream dataInputStream;
		try {
			dataInputStream = new DataInputStream(client.getInputStream());
			while (true) {
				System.out.println(dataInputStream.readUTF());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}