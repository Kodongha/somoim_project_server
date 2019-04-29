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

	HashMap<String,DataOutputStream> clients; //������ �ؽ����� �����ϴ� �ؽø�
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
			System.out.println("���� ����!!");
			while(true){

				socket = serverSocket.accept();
				System.out.println(socket.getInetAddress()+ " ����!!!");

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
				// Ŭ���̾�Ʈ ���Ͽ��� �����͸� ���Źޱ� ���� InputStream ����
				dis = new DataInputStream(socket.getInputStream());
				
				// Ŭ���̾�Ʈ ���Ͽ��� �����͸� �����ϱ� ���� OutputStream ����
				dos = new DataOutputStream(socket.getOutputStream());
			} catch (IOException e) { 
				e.printStackTrace();
			} 
		} 

		public void run() {
			String name = ""; 
			try { 
				// ���������� ���ʿ� Ŭ���̾�Ʈ�� ���� ��ȭ���� �޾ƾ� �Ѵ�. 
				System.out.println("run in!!!");
				name = dis.readUTF();
				System.out.println("name ::" + name);
				// ��ȭ���� �޾�, ���� Ŭ���̾�Ʈ���� ��ȭ�� ���� �޽����� ������. 
				sendToAll("#" + name + "���� �����̽��ϴ�.");

				// ��ȭ��, Ŭ���̾�� �޽����� ���� �� �ִ� OutputStream ��ü��
				// ��ȭ�� Map�� �����Ѵ�.  
				clients.put(name, dos); 
				System.out.println("���� ���������� ���� " + clients.size() + "�Դϴ�.");

				// Ŭ���̾�Ʈ�� ������ �޽����� �޾�, ���� Ŭ���̾�Ʈ���� �޽����� ������. 
				while (dis != null) { 
					sendToAll(dis.readUTF()); 
				}//while  

			} catch (IOException e) { 
				// ignore 
			} finally { 
				// finally���� ����ȴٴ� ���� Ŭ���̾�Ʈ�� �������� ���� �ǹ��Ѵ�. 
				sendToAll("#" + name + "���� �����̽��ϴ�.");

				// ��ȭ�濡�� ��ü ���� 
				clients.remove(name); 
				System.out.println("[" + socket.getInetAddress() //
				+ ":" + socket.getPort() + "]" + "���� ������ �����Ͽ����ϴ�.");
				System.out.println("���� ���������� ���� " + clients.size() + "�Դϴ�.");
			} // try 
		} // run 
	} // ReceiverThread 
	
	void sendToAll(String msg) { 
		// ��ȭ�濡 ������ ������ ��ȭ�� ����Ʈ ���� 
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
