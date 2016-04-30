package com.et.udp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★
★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★
★☆            @author： The One                  ☆★
★☆            @time：2014年6月6日 下午9:31:50      ☆★
★☆            @version：1.0                      ☆★
★☆            @lastMotifyTime：                                                      ☆★
★☆            @ClassAnnotation：                                                   ☆★
★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★
★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★
 */
public class Server {
	public static void main(String[] args) throws IOException {
		DatagramSocket ds = new DatagramSocket(8888);
		DatagramPacket rdp = new DatagramPacket(new byte[1024], 1024);
		new Thread(new Send(ds)).start();
		while (true) {
			ds.receive(rdp);
			System.out.println(new String(rdp.getData(), 0, rdp.getLength()));
		}
	}
}

class Send implements Runnable {
	DatagramSocket ds;

	public Send(DatagramSocket ds) {
		this.ds = ds;
	}

	@Override
	public void run() {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			DatagramPacket sdp = new DatagramPacket(new byte[1024], 1024, InetAddress.getByName("192.168.1.68"), 9999);
			while (true) {
				System.out.println("请输入：");
				sdp.setData(br.readLine().getBytes());
				ds.send(sdp);
			}
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}