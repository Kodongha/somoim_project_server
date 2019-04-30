package com.kh.somoim.server.run;

import com.kh.somoim.server.connector.ServerConnector;

public class DataServerRun {

	public static void main(String[] args) {
		
		new ServerConnector().serverStart();
	}
	
}
