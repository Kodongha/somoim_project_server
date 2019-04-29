package com.kh.somoim.server.process;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

import com.kh.somoim.home.model.vo.ClubVO;
import com.kh.somoim.login.model.vo.MemberVO;

public class RecommendProcess {

	private BufferedReader br;
	
	public Object getRecommendClubList(Object obj) {
		// TODO Auto-generated method stub
		
		MemberVO requestMemberVO = (MemberVO)obj;
		int userNumber = requestMemberVO.getUserNumber();
		ArrayList<ClubVO> recommendClubList = new ArrayList<ClubVO>();
		ArrayList<String> myFavoriteList = requestMemberVO.getFavorite();
		
		
		try {
			br = new BufferedReader(new FileReader("club.txt"));
			String[] tempStringArray;
			String[] temp2StringArray;

			int year = 0;
			int month = 0;
			int day = 0;
			Date meetingDay = null;
			boolean myClubFlag = false;
			boolean categoryFlag = false;

			String line = "";
			while((line = br.readLine()) != null) {
				ClubVO clubVO = new ClubVO();
				ArrayList<Integer> memberList = new ArrayList<Integer>();
				myClubFlag = false;
				categoryFlag = false;

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
				
				for(int i=0; i<myFavoriteList.size(); i++) {
					if(tempStringArray[6].equals(myFavoriteList.get(i))) {
						categoryFlag = true;
					}
				}
				
				temp2StringArray = tempStringArray[7].split(",");
				
				for(String memberNumber : temp2StringArray) {
					if(userNumber == Integer.parseInt(memberNumber)) {
						myClubFlag = true;
					}
					memberList.add(Integer.parseInt(memberNumber));
				}
				clubVO.setMembersNumber(memberList);
				if(!myClubFlag && categoryFlag) {
					System.out.println("추천 소모임 : " + clubVO);
					recommendClubList.add(clubVO);
				}
				
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

		System.out.println("recommendClubList:::"+recommendClubList);
		return recommendClubList;
		
		
	}

}
