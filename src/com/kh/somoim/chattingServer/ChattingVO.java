package com.kh.somoim.chattingServer;

import com.kh.somoim.home.model.vo.ClubVO;
import com.kh.somoim.login.model.vo.MemberVO;

public class ChattingVO {

	private MemberVO memberVO;
	private ClubVO clubVO;
	
	public ChattingVO() {
		// TODO Auto-generated constructor stub
	}

	public ChattingVO(MemberVO memberVO, ClubVO clubVO) {
		super();
		this.memberVO = memberVO;
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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ChattingVO [memberVO=" + memberVO + ", clubVO=" + clubVO + "]";
	}
	
}
