package com.kh.somoim.server.connector;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import com.kh.somoim.server.process.ServerProcess;

public class ServerConnector { 

	ServerSocket serverSocket;
	private Socket socket;
	ExecutorService executorService = Executors.newFixedThreadPool(5);
	ThreadPoolExecutor threadPoolExecutor;
	private ObjectOutputStream oos;
	private ObjectInputStream ois;
	
	public ServerConnector() {
		// TODO Auto-generated constructor stub
		threadPoolExecutor = (ThreadPoolExecutor) executorService;
	}
	
	public void serverStart() {
		
		try {
			
			serverSocket = new ServerSocket(7777);
			System.out.println("Server IP : " + InetAddress.getLocalHost());
			System.out.println("서버 시작!(port : " + serverSocket.getLocalPort() + ")");
			
			while(true) {
				socket = serverSocket.accept();
				System.out.println(socket.getInetAddress()+ " 접속!");
				new ServerManager(socket);
				threadPoolExecutor.execute(new ServerManager(socket));
			}
//			threadPoolExecutor.shutdown();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	class ServerManager implements Runnable {
		
		private Socket client;
		private String userId;
		
		public ServerManager(Socket socket) {
			// TODO Auto-generated constructor stub
			this.client = socket;
		}
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			ServerProcess process = new ServerProcess();
			
			try {
				ois = new ObjectInputStream(new DataInputStream(client.getInputStream()));
				oos = new ObjectOutputStream(new DataOutputStream(client.getOutputStream()));
				System.out.println("서버 데이터 송수신 준비 완료");
				
				String mode = (String)ois.readObject();
				System.out.println("mode :" + mode);

				Object clientObejct = ois.readObject();
				System.out.println("clientObejct :" + clientObejct.toString());
				
				switch (mode) {
				case "LoginDAO.checkAccount":
					oos.writeObject(process.checkAccount(clientObejct));
					break;

				case "HomeDAO.getMyClubList":
					oos.writeObject(process.getMyClubList(clientObejct));
				default:
					break;
				}
				
			} catch (IOException | ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
