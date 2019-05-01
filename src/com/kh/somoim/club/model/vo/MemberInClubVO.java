package com.kh.somoim.club.model.vo;

import java.io.Serializable;

import com.kh.somoim.home.model.vo.ClubVO;
import com.kh.somoim.login.model.vo.MemberVO;

public class MemberInClubVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -9086946483598873397L;
	private ClubVO clubVO;
	private MemberVO memberVO;
	
	public MemberInClubVO() {
		// TODO Auto-generated constructor stub
	}

	public MemberInClubVO(ClubVO clubVO, MemberVO memberVO) {
		super();
		this.clubVO = clubVO;
		this.memberVO = memberVO;
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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "MemberInClubVO [clubVO=" + clubVO + ", memberVO=" + memberVO + "]";
	}
	
}
