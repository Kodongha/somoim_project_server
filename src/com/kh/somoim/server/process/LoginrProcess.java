package com.kh.somoim.server.process;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

import com.kh.somoim.home.model.vo.ClubVO;
import com.kh.somoim.login.model.vo.MemberVO;

public class LoginrProcess {

	private BufferedReader br;

	public LoginrProcess() {
		// TODO Auto-generated constructor stub
	}

	public MemberVO checkAccount(Object obj) {

		MemberVO requestMemberVO = (MemberVO)obj;
		String id = requestMemberVO.getId();
		String password = requestMemberVO.getPassword();

		System.out.println("Input id:" + id);
		System.out.println("Input password:" + password);

		MemberVO memberVO = new MemberVO();

		try {
			br = new BufferedReader(new FileReader("member.txt"));
			String[] tempStringArray; 
			String[] temp2StringArray; 
			ArrayList<String> favoriteList = new ArrayList<String>();

			String line = "";
			while((line = br.readLine()) != null) {
				tempStringArray = line.split("§§");
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
				memberVO.setProfilePhotoPath(tempStringArray[11]);
				
				
				if(id.equals(memberVO.getId()) && password.equals(memberVO.getPassword())) {
					System.out.println("로그인 인증 완료 : " + memberVO.toString());
					return memberVO;
				}
				favoriteList.clear();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		System.out.println("로그인 인증 실패");
		memberVO = null;
		return memberVO;

	}

	


}
