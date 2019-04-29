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

import com.kh.somoim.server.process.HomeProcess;
import com.kh.somoim.server.process.LoginrProcess;
import com.kh.somoim.server.process.RecommendProcess;
import com.kh.somoim.server.process.SearchProcess;
import com.kh.somoim.server.process.SettingProcess;

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
		} finally {
			try {
				socket.close();
				serverSocket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	class ServerManager implements Runnable {
		
		private Socket client;
		
		public ServerManager(Socket socket) {
			// TODO Auto-generated constructor stub
			this.client = socket;
		}
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			LoginrProcess loginrProcess = new LoginrProcess();
			HomeProcess homeProcess = new HomeProcess();
			RecommendProcess recommendProcess = new RecommendProcess();
			SettingProcess settingProcess = new SettingProcess();
			SearchProcess searchProcess = new SearchProcess();
			
			try {
				ois = new ObjectInputStream(new DataInputStream(client.getInputStream()));
				oos = new ObjectOutputStream(new DataOutputStream(client.getOutputStream()));
				System.out.println("서버 데이터 송수신 준비 완료");
				
				String mode = (String)ois.readObject();
				System.out.println("mode :" + mode);

				Object requestObject = ois.readObject();
				System.out.println("clientObejct :" + requestObject.toString());
				/*=======================================================*/
				/*=======================================================*/
				switch (mode) {
				case "LoginDAO.checkAccount":
					oos.writeObject(loginrProcess.checkAccount(requestObject));
					break;

				case "HomeDAO.getMyClubList":
					oos.writeObject(homeProcess.getMyClubList(requestObject));
					break;

				case "RecommendDAO.getRecommendClubList":
					oos.writeObject(recommendProcess.getRecommendClubList(requestObject));
					break;
					
				case "SettingDAO.setInformation":
					oos.writeObject(settingProcess.setInformation(requestObject));
					break;
					
				case "SettingDAO.removeMember":
					oos.writeObject(settingProcess.removeMember(requestObject));
					break;
					
				case "SearchDAO.getFavoriteSearchClubList":
					oos.writeObject(searchProcess.getFavoriteSearchClubList(requestObject));
					break;
					
				case "SearchDAO.getStringSearchClubList":
					oos.writeObject(searchProcess.getStringSearchClubList(requestObject));
					break;
					
				default:
					System.out.println("등록되지 않은 메소드 호출!");
					break;
				}
				/*=======================================================*/
				/*=======================================================*/
			} catch (IOException | ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				try {
					ois.close();
					oos.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		}
	}
}
