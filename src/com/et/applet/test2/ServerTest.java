package com.et.applet.test2;

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
★☆            @time：2014年5月29日 下午9:29:40      ☆★
★☆            @version：1.0                      ☆★
★☆            @lastMotifyTime：                                                      ☆★
★☆            @ClassAnnotation：                                                   ☆★
★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★
★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★
 */
public class ServerTest {
	public static void main(String[] args) throws IOException {
		DatagramSocket ds = new DatagramSocket(9999);

		DatagramPacket sdp = new DatagramPacket(new byte[1024], 1024, InetAddress.getByName("192.168.1.68"), 8888);
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		new Thread(new Send(ds)).start();
		while (true) {
			System.out.println("请输入：");
			sdp.setData(br.readLine().getBytes());
			ds.send(sdp);
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
		while (true) {
			try {
				DatagramPacket rdp = new DatagramPacket(new byte[1024], 0, 1024);
				ds.receive(rdp);
				System.out.println(new String(rdp.getData(), 0, rdp.getLength()));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}