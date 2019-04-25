package com.kh.somoim.server.process;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

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
		ArrayList<String> favoriteList = new ArrayList<String>();

		System.out.println("id:" + id);
		System.out.println("password:" + password);

		MemberVO memberVO = new MemberVO();

		try {
			br = new BufferedReader(new FileReader("member.txt"));
			String[] tempStringArray; 
			String[] temp2StringArray; 
			String line = "";
			while((line = br.readLine()) != null) {
				System.out.println(line);
				tempStringArray = line.split("¡×¡×");
				memberVO.setUserNumber(Integer.parseInt(tempStringArray[0]));
				memberVO.setId(tempStringArray[1]);
				memberVO.setBirth(tempStringArray[2]);
				memberVO.setPhoneNumber(tempStringArray[3]);
				memberVO.setEmail(tempStringArray[4]);
				memberVO.setPassword(tempStringArray[5]);
				memberVO.setName(tempStringArray[6]);
				memberVO.setGender(tempStringArray[7]);
				memberVO.setAddress(tempStringArray[8]);
				memberVO.setPoint(Integer.parseInt(tempStringArray[9]));
				temp2StringArray = tempStringArray[10].split(",");
				for(String favorite : temp2StringArray) {
					favoriteList.add(favorite);
				}
				memberVO.setFavorite(favoriteList);

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

	public Object getMyClubList(Object clientObejct) {
		// TODO Auto-generated method stub
		return null;
	}

}
