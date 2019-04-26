package com.februy.toycat.web;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

import com.februy.toycat.operate.Dispatcher;
import com.februy.toycat.operate.LoginServlet;
import com.februy.toycat.operate.RegServlet;

public class Server3 {
	public static void main(String[] args) {
		Server3 server=new Server3();
		server.start();
	}
	private ServerSocket serverSocket;
	
	public Server3() {
		
	}
	public void start() {
		try {
			serverSocket=new ServerSocket(8899);
			reveive();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void stop() {
		try {
			serverSocket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void reveive() {
		while(true) {
			try {
					Socket client=serverSocket.accept();
					System.out.println("与客户端建立了连接");
					new Thread(new Dispatcher(client)).start();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
}
