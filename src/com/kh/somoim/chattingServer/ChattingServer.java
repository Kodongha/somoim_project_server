package com.kh.somoim.chattingServer;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;

import com.kh.somoim.home.model.vo.ClubVO;
import com.kh.somoim.login.model.vo.MemberVO;

public class ChattingServer {

	HashMap<ChattingVO, ObjectOutputStream> clients;
	ServerSocket serverSocket = null;
	Socket socket = null;

	public ChattingServer() {
		// TODO Auto-generated constructor stub
		clients = new HashMap<ChattingVO, ObjectOutputStream>();
		Collections.synchronizedMap(clients);
	}
	public void serverStart() {

		try {
			serverSocket = new ServerSocket(8888);
			System.out.println("==========================================================");
			System.out.println("========================채팅 서버 시작========================");
			System.out.println("==========================================================");
			System.out.println("Server IP : " + InetAddress.getLocalHost());
			System.out.println("서버 시작!(port : " + serverSocket.getLocalPort() + ")");

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
				ClubVO clubVO = (ClubVO)ois.readObject();
				
				ChattingVO chattingVO = new ChattingVO();
				chattingVO.setClubVO(clubVO);
				chattingVO.setMemberVO(memberVO);
				clients.put(chattingVO, oos);
				System.out.println("현재 접속자 수 : " + clients.size());

				while (ois != null) { 
					sendToAll((String)ois.readObject(), clubVO, memberVO);
				}//while 
			} catch (ClassNotFoundException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	void sendToAll(String msg, ClubVO sendClubVO, MemberVO sendMemberVO) {
		Iterator<ChattingVO> it = clients.keySet().iterator();
		String sendName = sendMemberVO.getName();
		while (it.hasNext()) { 
			try {
				System.out.println("clients.size()::::"+clients.size());
				ChattingVO ChattingVO = it.next();
				ClubVO clubVO = ChattingVO.getClubVO();
				
				if(clubVO.getClubNumber() == sendClubVO.getClubNumber()) {
					String resultMessage = sendName + " : " + msg;
					ObjectOutputStream oos2 = clients.get(ChattingVO);
					oos2.writeObject(resultMessage);
					System.out.println("msg ::: " + resultMessage);
				}
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} // while 
	}
}
