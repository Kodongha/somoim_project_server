package com.kh.somoim.server.process;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.kh.somoim.login.model.vo.MemberVO;

public class ServerProcess {

	private BufferedReader br;

	public ServerProcess() {
		// TODO Auto-generated constructor stub
	}

	public MemberVO checkAccount(Object Obj) {

		MemberVO requestMemberVO = (MemberVO)Obj;
		String id = requestMemberVO.getId();
		String password = requestMemberVO.getPassword();

		System.out.println("id:" + id);
		System.out.println("password:" + password);

		MemberVO memberVO = new MemberVO();

		try {
			br = new BufferedReader(new FileReader("member.txt"));
			String[] tempStringArray; 
			String line = "";
			while((line = br.readLine()) != null) {
				System.out.println(line);
				tempStringArray = line.split("§§");
				memberVO.setId(tempStringArray[0]);
				memberVO.setBirth(tempStringArray[1]);
				memberVO.setPhoneNumber(tempStringArray[2]);
				memberVO.setEmail(tempStringArray[3]);
				memberVO.setPassword(tempStringArray[4]);
				memberVO.setName(tempStringArray[5]);
				memberVO.setGender(tempStringArray[6]);
				memberVO.setAddress(tempStringArray[7]);
				// ArrayList로 할건지 String 배열로 할건지 결정

				//userVO.setAddress(tempStringArray[8]);

				System.out.println(memberVO.toString());
				if(id.equals(memberVO.getId()) && password.equals(memberVO.getPassword())) {
					return memberVO;
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		memberVO = null;
		
		return memberVO;

	}

}
