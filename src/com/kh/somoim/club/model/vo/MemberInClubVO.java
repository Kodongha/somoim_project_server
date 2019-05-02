package com.kh.somoim.club.model.vo;

import java.io.Serializable;

import com.kh.somoim.home.model.vo.ClubVO;
import com.kh.somoim.login.model.vo.MemberVO;

public class MemberInClubVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7178760394558956774L;
	private ClubVO clubVO;
	private MemberVO memberVO;
	private String selectedCategory;
	
	public MemberInClubVO() {
		// TODO Auto-generated constructor stub
	}

	public MemberInClubVO(ClubVO clubVO, MemberVO memberVO, String selectedCategory) {
		super();
		this.clubVO = clubVO;
		this.memberVO = memberVO;
		this.selectedCategory = selectedCategory;
	}

	/**
	 * @return the clubVO
	 */
	public ClubVO getClubVO() {
		return clubVO;
	}

	/**
	 * @param clubVO the clubVO to set
	 */
	public void setClubVO(ClubVO clubVO) {
		this.clubVO = clubVO;
	}

	/**
	 * @return the memberVO
	 */
	public MemberVO getMemberVO() {
		return memberVO;
	}

	/**
	 * @param memberVO the memberVO to set
	 */
	public void setMemberVO(MemberVO memberVO) {
		this.memberVO = memberVO;
	}

	/**
	 * @return the selectedCategory
	 */
	public String getSelectedCategory() {
		return selectedCategory;
	}

	/**
	 * @param selectedCategory the selectedCategory to set
	 */
	public void setSelectedCategory(String selectedCategory) {
		this.selectedCategory = selectedCategory;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
