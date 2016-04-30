package com.et.applet.test2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * @作者: Faker
 * @时间:2014年5月27日 下午9:44:20
 * @版本:1.0
 */
public class Server {
	public static List<Socket> list = new ArrayList<Socket>();

	public static void main(String[] args) {
		try {
			ServerSocket ss = new ServerSocket(777);
			while (true) {
				Socket s = ss.accept();
				list.add(s);
				new Thread(new ServeThread(s)).start();
				System.out.println("连接成功");
			}
		} catch (IOException e) {
			System.out.println("多次启动");
		}
	}
}

class ServeThread implements Runnable {
	private Socket s;
	private BufferedReader br;

	public ServeThread(Socket s) {
		this.s = s;
		try {
			br = new BufferedReader(new InputStreamReader(s.getInputStream()));
		} catch (IOException e) {
			System.out.println("该线程已经退出");
		}
	}

	@Override
	public void run() {
		String content = null;
		try {
		while (true) {
				content = br.readLine();
				for (Socket sc : Server.list) {
					PrintStream ps = new PrintStream(sc.getOutputStream());
					ps.println(Thread.currentThread().getName()+"说: "+content);
				}
		}
		} catch (IOException e) {
			Server.list.remove(s);
			System.out.println("线程退出");
		}
	}
}
