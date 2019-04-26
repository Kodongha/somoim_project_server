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

public class ServerProcess {

	private BufferedReader br;

	public ServerProcess() {
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

				if(id.equals(memberVO.getId()) && password.equals(memberVO.getPassword())) {
					System.out.println("로그인 인증 완료 : " + memberVO.toString());
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


		System.out.println("로그인 인증 실패");
		memberVO = null;
		return memberVO;

	}

	public Object getMyClubList(Object obj) {
		// TODO Auto-generated method stub

		MemberVO requestMemberVO = (MemberVO)obj;
		int userNumber = requestMemberVO.getUserNumber();
		ArrayList<ClubVO> myClubList = new ArrayList<ClubVO>();


		try {
			br = new BufferedReader(new FileReader("club.txt"));
			String[] tempStringArray;
			String[] temp2StringArray;

			int year = 0;
			int month = 0;
			int day = 0;
			Date meetingDay = null;
			boolean myClubFlag = false;

			String line = "";
			while((line = br.readLine()) != null) {
				ClubVO clubVO = new ClubVO();
				ArrayList<Integer> memberList = new ArrayList<Integer>();
				myClubFlag = false;

				tempStringArray = line.split("§§");

				clubVO.setClubNumber(Integer.parseInt(tempStringArray[0]));
				clubVO.setName(tempStringArray[1]);
				clubVO.setClupMasterNumber(Integer.parseInt(tempStringArray[2]));
				clubVO.setInformation(tempStringArray[3]);

				year = Integer.parseInt(tempStringArray[4].substring(0, 4));
				month = Integer.parseInt(tempStringArray[4].substring(4, 6)) - 1;
				day = Integer.parseInt(tempStringArray[4].substring(6, 8));
				meetingDay = new Date(new GregorianCalendar(year, month, day).getTimeInMillis());
				clubVO.setMeetingDay(meetingDay);

				clubVO.setTitleImagePath(tempStringArray[5]);

				temp2StringArray = tempStringArray[6].split(",");
				for(String memberNumber : temp2StringArray) {
					if(userNumber == Integer.parseInt(memberNumber)) {
						myClubFlag = true;
					}
					memberList.add(Integer.parseInt(memberNumber));
				}
				clubVO.setMembersNumber(memberList);
				if(myClubFlag) {
					System.out.println("가입된 소모임 : " + clubVO);
					myClubList.add(clubVO);
				}
				
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println(myClubList);
		return myClubList;
	}

}
