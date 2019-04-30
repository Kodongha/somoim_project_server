package com.kh.somoim.chattingServer;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;

import com.kh.somoim.home.model.vo.ClubVO;
import com.kh.somoim.login.model.vo.MemberVO;

public class ChattingServer {

	HashMap<MemberVO, ObjectOutputStream> clients;
	ServerSocket serverSocket = null;
	Socket socket = null;

	public ChattingServer() {
		// TODO Auto-generated constructor stub
		clients = new HashMap<MemberVO, ObjectOutputStream>();
		Collections.synchronizedMap(clients);
	}
	public void start() {

		try {
			serverSocket = new ServerSocket(8888);
			System.out.println("채팅 서버 시작!!");

			while(true){
				socket = serverSocket.accept();
				System.out.println(socket.getInetAddress()+ " 접속!!!");
				new ServerReceiver(socket).start();
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	class ServerReceiver extends Thread {
		Socket socket;
		ObjectInputStream ois;
		ObjectOutputStream oos;

		public ServerReceiver(Socket socket) {
			// TODO Auto-generated constructor stub
			this.socket = socket;
			try {
				ois = new ObjectInputStream(new DataInputStream(socket.getInputStream()));
				oos = new ObjectOutputStream(new DataOutputStream(socket.getOutputStream()));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			super.run();
			System.out.println("run!");
			try {
				MemberVO memberVO = (MemberVO)ois.readObject();
				clients.put(memberVO, oos);
				System.out.println("현재 접속자 수 : " + clients.size());

				while (ois != null) { 
					sendToAll((String)ois.readObject()); 
				}//while 


			} catch (ClassNotFoundException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	void sendToAll(String msg) {
		Iterator<MemberVO> it = clients.keySet().iterator();
		while (it.hasNext()) { 
			try { 

				MemberVO memberVO = it.next(); 
				System.out.println("name ::: " + memberVO);
				ObjectOutputStream oos = clients.get(memberVO);
				oos.writeObject(msg);
				System.out.println("msg ::: " + msg);
			} catch (IOException e) { 
			} 
		} // while 
	}

}
