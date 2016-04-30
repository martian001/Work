package com.et.applet.test2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 * @author: SpringXian
 * 2014-5-29  下午9:31:19
 */
public class UdpServer {
	public static void main(String[] args) {
		byte[] b = new byte[1024];
		DatagramPacket dp = new DatagramPacket(b, b.length);
		try {
			DatagramSocket ds = new DatagramSocket(30000);
			String content = "";
			new Thread(new UdpServerThread()).start();
			while (true) {
				ds.receive(dp);
				content = new String(b, 0, dp.getLength());
				System.out.println(dp.getAddress().getHostName() + "发送了：" + content);
			}
		} catch (SocketException e) {
			System.out.println("连接服务器失败");
		} catch (IOException e) {
			System.out.println("IO异常");
		}
	}
}

class UdpServerThread implements Runnable {

	@Override
	public void run() {

		byte[] b = new byte[1024];
		try {
			DatagramPacket dp = new DatagramPacket(b, b.length, InetAddress.getByName("localhost"), 40000);
			DatagramSocket ds = new DatagramSocket();
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String content = "";
			while (true) {
				content = br.readLine();
				dp.setData(content.getBytes());
				ds.send(dp);
			}
		} catch (UnknownHostException e) {
			System.out.println("服务器异常");
		} catch (SocketException e) {
			System.out.println("找不到端口");
		} catch (IOException e) {
			System.out.println("IO异常");
		}
	}

}
