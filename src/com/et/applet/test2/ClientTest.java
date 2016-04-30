package com.et.applet.test2;

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
★☆            @time：2014年5月29日 下午9:29:58      ☆★
★☆            @version：1.0                      ☆★
★☆            @lastMotifyTime：                                                      ☆★
★☆            @ClassAnnotation：                                                   ☆★
★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★
★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★
 */
public class ClientTest {
	public static void main(String[] args) throws IOException {
		DatagramSocket ds = new DatagramSocket(8888);
		DatagramPacket sdp = new DatagramPacket(new byte[1024], 1024, InetAddress.getByName("192.168.1.68"), 9999);
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		new Thread(new get(ds)).start();
		while (true) {
			System.out.println("请输入：");
			sdp.setData(br.readLine().getBytes());
			ds.send(sdp);
		}
	}
}

class get implements Runnable {
	DatagramSocket ds;

	public get(DatagramSocket ds) {
		this.ds = ds;
	}

	@Override
	public void run() {
		try {
			DatagramPacket rdp = new DatagramPacket(new byte[1024], 1024);
			while (true) {
				ds.receive(rdp);
				System.out.println(new String(rdp.getData(), 0, rdp.getLength()));
			}
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
