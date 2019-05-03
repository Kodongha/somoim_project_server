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

public class HomeProcess {

	private BufferedReader br;
	
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
				clubVO.setCategory(tempStringArray[6]);
				
				if(tempStringArray[7].contains(",")) {
					temp2StringArray = tempStringArray[7].split(",");
				} else {
					temp2StringArray = new String[1];
					temp2StringArray[0] = tempStringArray[7];
				}
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
				
			} // end while
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("myClubList:::"+myClubList);
		return myClubList;
	}
	
}
