package com.kh.somoim.server.process;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

import com.kh.somoim.home.model.vo.ClubVO;

public class SearchProcess {

	private BufferedReader br;
	
	public Object getFavoriteSearchClubList(Object obj) {
		// TODO Auto-generated method stub
		String favorite = (String)obj;
		ArrayList<ClubVO> favoriteSearchClubList = new ArrayList<ClubVO>();
		
		try {
			br = new BufferedReader(new FileReader("club.txt"));
			String[] tempStringArray;
			String[] temp2StringArray;

			int year = 0;
			int month = 0;
			int day = 0;
			Date meetingDay = null;
			boolean categoryFlag = false;

			String line = "";
			while((line = br.readLine()) != null) {
				ClubVO clubVO = new ClubVO();
				ArrayList<Integer> memberList = new ArrayList<Integer>();
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
				if(tempStringArray[6].equals(favorite)) {
					categoryFlag = true;
				}
				
				temp2StringArray = tempStringArray[7].split(",");
				for(String memberNumber : temp2StringArray) {
					memberList.add(Integer.parseInt(memberNumber));
				}
				clubVO.setMembersNumber(memberList);
				if(categoryFlag) {
					System.out.println("검색 소모임 : " + clubVO);
					favoriteSearchClubList.add(clubVO);
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
				if(br != null) {
					br.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("recommendClubList:::"+favoriteSearchClubList);
		return favoriteSearchClubList;
	}

	public Object getStringSearchClubList(Object obj) {
		// TODO Auto-generated method stub
		String SearchString = (String)obj;
		ArrayList<ClubVO> favoriteSearchClubList = new ArrayList<ClubVO>();
		
		try {
			br = new BufferedReader(new FileReader("club.txt"));
			String[] tempStringArray;
			String[] temp2StringArray;

			int year = 0;
			int month = 0;
			int day = 0;
			Date meetingDay = null;
			boolean nameFlage = false;

			String line = "";
			while((line = br.readLine()) != null) {
				ClubVO clubVO = new ClubVO();
				ArrayList<Integer> memberList = new ArrayList<Integer>();
				nameFlage = false;

				tempStringArray = line.split("§§");

				clubVO.setClubNumber(Integer.parseInt(tempStringArray[0]));
				clubVO.setName(tempStringArray[1]);
				if(tempStringArray[1].contains(SearchString)) {
					nameFlage = true;
				}
				
				clubVO.setClupMasterNumber(Integer.parseInt(tempStringArray[2]));
				clubVO.setInformation(tempStringArray[3]);

				year = Integer.parseInt(tempStringArray[4].substring(0, 4));
				month = Integer.parseInt(tempStringArray[4].substring(4, 6)) - 1;
				day = Integer.parseInt(tempStringArray[4].substring(6, 8));
				meetingDay = new Date(new GregorianCalendar(year, month, day).getTimeInMillis());
				clubVO.setMeetingDay(meetingDay);

				clubVO.setTitleImagePath(tempStringArray[5]);
				clubVO.setCategory(tempStringArray[6]);
				
				temp2StringArray = tempStringArray[7].split(",");
				for(String memberNumber : temp2StringArray) {
					memberList.add(Integer.parseInt(memberNumber));
				}
				clubVO.setMembersNumber(memberList);
				if(nameFlage) {
					System.out.println("검색 소모임 : " + clubVO);
					favoriteSearchClubList.add(clubVO);
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
				if(br != null) {
					br.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		System.out.println("recommendClubList:::"+favoriteSearchClubList);
		return favoriteSearchClubList;
		
	}
	
}
