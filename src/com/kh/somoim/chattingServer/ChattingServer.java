package com.kh.somoim.chattingServer;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;

public class ChattingServer {

	HashMap<String,DataOutputStream> clients; //지역별 해쉬맵을 관리하는 해시맵
	ServerSocket serverSocket = null;
	Socket socket = null;
	
	public ChattingServer() {
		// TODO Auto-generated constructor stub
		clients = new HashMap<String, DataOutputStream>();
		Collections.synchronizedMap(clients);
	}
	
	public void start() {
		try {
			serverSocket = new ServerSocket(8888);
			System.out.println("서버 시작!!");
			while(true){

				socket = serverSocket.accept();
				System.out.println(socket.getInetAddress()+ " 접속!!!");

				new ServerReceiver(socket).start();;
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	class ServerReceiver extends Thread {
		Socket socket;
		DataInputStream dis;
		DataOutputStream dos;

		ServerReceiver(Socket socket) { 
			this.socket = socket; 
			try { 
				// 클라이언트 소켓에서 데이터를 수신받기 위한 InputStream 생성
				dis = new DataInputStream(socket.getInputStream());
				
				// 클라이언트 소켓에서 데이터를 전송하기 위한 OutputStream 생성
				dos = new DataOutputStream(socket.getOutputStream());
			} catch (IOException e) { 
				e.printStackTrace();
			} 
		} 

		public void run() {
			String name = ""; 
			try { 
				// 서버에서는 최초에 클라이언트가 보낸 대화명을 받아야 한다. 
				System.out.println("run in!!!");
				name = dis.readUTF();
				System.out.println("name ::" + name);
				// 대화명을 받아, 전에 클라이언트에게 대화방 참여 메시지를 보낸다. 
				sendToAll("#" + name + "님이 들어오셨습니다.");

				// 대화명, 클라이언로 메시지를 보낼 수 있는 OutputStream 객체를
				// 대화방 Map에 저장한다.  
				clients.put(name, dos); 
				System.out.println("현재 서버접속자 수는 " + clients.size() + "입니다.");

				// 클라이언트가 전송한 메시지를 받아, 전에 클라이언트에게 메시지를 보낸다. 
				while (dis != null) { 
					sendToAll(dis.readUTF()); 
				}//while  

			} catch (IOException e) { 
				// ignore 
			} finally { 
				// finally절이 실행된다는 것은 클라이언트가 빠져나간 것을 의미한다. 
				sendToAll("#" + name + "님이 나가셨습니다.");

				// 대화방에서 객체 삭제 
				clients.remove(name); 
				System.out.println("[" + socket.getInetAddress() //
				+ ":" + socket.getPort() + "]" + "에서 접속을 종료하였습니다.");
				System.out.println("현재 서버접속자 수는 " + clients.size() + "입니다.");
			} // try 
		} // run 
	} // ReceiverThread 
	
	void sendToAll(String msg) { 
		// 대화방에 접속한 유저의 대화명 리스트 추출 
		Iterator<String> it = clients.keySet().iterator(); 

		while (it.hasNext()) { 
			try { 
				String name = it.next(); 
				System.out.println("name ::: " + name);
				DataOutputStream out = clients.get(name);
				out.writeUTF(msg);
				System.out.println("msg ::: " + msg);
				
			} catch (IOException e) { 
			} 
		} // while 
	} // sendToAll 
	
}
