package com.et.applet.test2;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Socket1 {
	public static void main(String[] args) throws IOException {
		DatagramSocket socket = new DatagramSocket(13928);
		byte[] info = new byte[1024];
		DatagramPacket packet = new DatagramPacket(info, info.length, InetAddress.getLocalHost(), 13927);
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String message = null;

		new Receive(socket).start();
		while (!"bye".equals(message)) {
			message = reader.readLine();
			packet.setData(message.getBytes());
			socket.send(packet);
		}
		System.out.println("退出聊天！");
		System.exit(0);
	}
}

class Receive extends Thread {
	private DatagramSocket socket;

	public Receive(DatagramSocket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {
		byte[] info = new byte[1024];
		DatagramPacket packet = new DatagramPacket(info, info.length);
		String message;

		while (true) {
			try {
				socket.receive(packet);
				message = new String(info, 0, packet.getLength());
				System.out.println(message);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}