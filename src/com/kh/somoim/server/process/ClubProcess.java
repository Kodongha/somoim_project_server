package com.kh.somoim.server.process;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import com.kh.somoim.home.model.vo.ClubVO;
import com.kh.somoim.login.model.vo.MemberVO;

public class ClubProcess {

	private BufferedReader br;
	
	public Object getClubMemberList(Object obj) {
		// TODO Auto-generated method stub
		ClubVO requestClubVO = (ClubVO)obj;
		
		ArrayList<Integer> requestMemberList = requestClubVO.getMembersNumber();
		ArrayList<MemberVO> resoponseMemberList = new ArrayList<MemberVO>();
		System.out.println("requestMemberList:::" + requestMemberList);
		try {
			br = new BufferedReader(new FileReader("member.txt"));
			String[] tempStringArray; 
			String[] temp2StringArray; 
			ArrayList<String> favoriteList = new ArrayList<String>();
			
			String line = "";
			while((line = br.readLine()) != null) {
				MemberVO memberVO = new MemberVO();
				
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
				memberVO.setProfilePhotoPath(tempStringArray[11]);
				
				for(Integer userNumber : requestMemberList) {
					if(userNumber.equals(Integer.valueOf(tempStringArray[0]))) {
						resoponseMemberList.add(memberVO);
					}
				}
				favoriteList.clear();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return resoponseMemberList;
	}

}
