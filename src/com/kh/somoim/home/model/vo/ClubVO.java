package com.kh.somoim.home.model.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class ClubVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6712211978072546539L;
	private int clubNumber;			// 소모임 번호
	private String name;			// 소모임 이름(중복 X)
	private int clupMasterNumber ;	// 모임장
	private String information;		// 소모임 정보
	private Date meetingDay;		// 다음 정모 일자
	private String titleImagePath;	// 타이틀 사진 경로
	private ArrayList<String> membersNumber;		// 맴버 목록
	
	public ClubVO() {
		// TODO Auto-generated constructor stub
	}

	public ClubVO(int clubNumber, String name, int clupMasterNumber, String information, Date meetingDay,
			String titleImagePath, ArrayList<String> membersNumber) {
		super();
		this.clubNumber = clubNumber;
		this.name = name;
		this.clupMasterNumber = clupMasterNumber;
		this.information = information;
		this.meetingDay = meetingDay;
		this.titleImagePath = titleImagePath;
		this.membersNumber = membersNumber;
	}

	/**
	 * @return the clubNumber
	 */
	public int getClubNumber() {
		return clubNumber;
	}

	/**
	 * @param clubNumber the clubNumber to set
	 */
	public void setClubNumber(int clubNumber) {
		this.clubNumber = clubNumber;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the clupMasterNumber
	 */
	public int getClupMasterNumber() {
		return clupMasterNumber;
	}

	/**
	 * @param clupMasterNumber the clupMasterNumber to set
	 */
	public void setClupMasterNumber(int clupMasterNumber) {
		this.clupMasterNumber = clupMasterNumber;
	}

	/**
	 * @return the information
	 */
	public String getInformation() {
		return information;
	}

	/**
	 * @param information the information to set
	 */
	public void setInformation(String information) {
		this.information = information;
	}

	/**
	 * @return the meetingDay
	 */
	public Date getMeetingDay() {
		return meetingDay;
	}

	/**
	 * @param meetingDay the meetingDay to set
	 */
	public void setMeetingDay(Date meetingDay) {
		this.meetingDay = meetingDay;
	}

	/**
	 * @return the titleImagePath
	 */
	public String getTitleImagePath() {
		return titleImagePath;
	}

	/**
	 * @param titleImagePath the titleImagePath to set
	 */
	public void setTitleImagePath(String titleImagePath) {
		this.titleImagePath = titleImagePath;
	}

	/**
	 * @return the membersNumber
	 */
	public ArrayList<String> getMembersNumber() {
		return membersNumber;
	}

	/**
	 * @param membersNumber the membersNumber to set
	 */
	public void setMembersNumber(ArrayList<String> membersNumber) {
		this.membersNumber = membersNumber;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ClubVO [clubNumber=" + clubNumber + ", name=" + name + ", clupMasterNumber=" + clupMasterNumber
				+ ", information=" + information + ", meetingDay=" + meetingDay + ", titleImagePath=" + titleImagePath
				+ ", membersNumber=" + membersNumber + "]";
	}

	
	
}
