package com.et.udp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★
★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★
★☆            @author： The One                  ☆★
★☆            @time：2014年6月6日 下午9:39:26      ☆★
★☆            @version：1.0                      ☆★
★☆            @lastMotifyTime：                                                      ☆★
★☆            @ClassAnnotation：                                                   ☆★
★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★
★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★
 */
public class Client {
	public static void main(String[] args) throws IOException {
		DatagramSocket ds = new DatagramSocket(9999);
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		new Thread(new Getmess(ds)).start();
		DatagramPacket sdp = new DatagramPacket(new byte[1024], 1024, InetAddress.getByName("192.168.1.68"), 8888);
		while (true) {
			System.out.println("请输入：");
			sdp.setData(br.readLine().getBytes());
			ds.send(sdp);
		}
	}
}

class Getmess implements Runnable {
	DatagramSocket ds;

	public Getmess(DatagramSocket ds) {
		this.ds = ds;
	}

	@Override
	public void run() {
		DatagramPacket rdp = new DatagramPacket(new byte[1024], 1024);
		try {
			while (true) {
				ds.receive(rdp);
				System.out.println(new String(rdp.getData(), 0, rdp.getLength()));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}