package com.kh.somoim.server.process;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

import com.kh.somoim.club.model.vo.BoardResponseVO;
import com.kh.somoim.club.model.vo.BoardVO;
import com.kh.somoim.club.model.vo.MemberInClubVO;
import com.kh.somoim.home.model.vo.ClubVO;
import com.kh.somoim.login.model.vo.MemberVO;

public class ClubProcess {

	private BufferedReader br;
	private BufferedWriter bw;
	private BufferedReader tempBr;
	
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

	/**
	 * 글 쓰기
	 * @param obj
	 * @return
	 */
	public Object insertBoard(Object obj) {
		// TODO Auto-generated method stub
		BoardVO boardVO = (BoardVO)obj;
		
		try {
			br = new BufferedReader(new FileReader("board.txt"));
			String[] tempStringArray; 
			String line = "";
			int maxNumber = 0;
			while((line = br.readLine()) != null) {
				tempStringArray = line.split("§§");
				if (maxNumber < Integer.parseInt(tempStringArray[0])) {
					maxNumber = Integer.parseInt(tempStringArray[0]);
				}
			}
			
			maxNumber += 1;
			
			String boardStr = "";
			boardStr += maxNumber + "§§";
			boardStr += boardVO.getClubNumber() + "§§";
			boardStr += boardVO.getTitle() + "§§";
			boardStr += boardVO.getContent() + "§§";
			boardStr += boardVO.getWriter() + "§§";
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd hh:mm:ss");
			String date = sdf.format(boardVO.getWriteDay());
			boardStr += date + "§§";
			boardStr += boardVO.getBoardSelect() + "§§";
			boardStr += boardVO.getImagePath();
			
			bw = new BufferedWriter(new FileWriter("board.txt", true));
			bw.newLine();
			bw.write(boardStr);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				br.close();
				bw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		return null;
	}

	/**
	 * 가입 인사 리스트 출력
	 * @param requestObject
	 * @return
	 */
	public Object getFirstGreeting(Object obj) {
		// TODO Auto-generated method stub
		MemberInClubVO memberInClubVO = (MemberInClubVO)obj;
		
		
		ArrayList<MemberVO> memberList = new ArrayList<MemberVO>();
		try {
			tempBr = new BufferedReader(new FileReader("member.txt"));
			String[] tempStringArray; 
			String[] temp2StringArray; 
			ArrayList<String> favoriteList = new ArrayList<String>();
			
			String line = "";
			while((line = tempBr.readLine()) != null) {
				MemberVO memberVO = new MemberVO();
				
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
				
				memberList.add(memberVO);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				tempBr.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		System.out.println("memberList::::::"+memberList);
		System.out.println("memberList.size()::::::"+memberList.size());
		
		ClubVO clubVO = memberInClubVO.getClubVO();
		ArrayList<BoardResponseVO> boardResponseVOList = new ArrayList<BoardResponseVO>(); 
		try {
			br = new BufferedReader(new FileReader("board.txt"));
			String[] tempStringArray; 
			String line = "";
			while((line = br.readLine()) != null) {
				BoardResponseVO boardResponseVO = new BoardResponseVO();
				tempStringArray = line.split("§§");
				boardResponseVO.setWritingNumber(Integer.parseInt(tempStringArray[0]));
				boardResponseVO.setClubNumber(Integer.parseInt(tempStringArray[1]));
				boardResponseVO.setTitle(tempStringArray[2]);
				boardResponseVO.setContent(tempStringArray[3]);
				
				int writeNumber = Integer.parseInt(tempStringArray[4]);
				for(MemberVO tempMemberVO : memberList) {
					if(writeNumber == tempMemberVO.getUserNumber()) {
						boardResponseVO.setWriter(tempMemberVO.getName());
					}
				}
				/* Date */
				String [] tempDate = tempStringArray[5].split(" ");
				String [] tempDay = tempDate[0].split("[.]");
				String [] tempTime = tempDate[1].split("[:]");
				Date writeDate = new Date(new GregorianCalendar(Integer.parseInt(tempDay[0])
						, Integer.parseInt(tempDay[1])
						, Integer.parseInt(tempDay[2])
						, Integer.parseInt(tempTime[0])
						, Integer.parseInt(tempTime[1])
						, Integer.parseInt(tempTime[2])).getTimeInMillis());
				boardResponseVO.setWriteDay(writeDate);
				boardResponseVO.setBoardSelect(tempStringArray[6]);
				if(tempStringArray.length == 7) {
					boardResponseVO.setImagePath(null);
				} else if(tempStringArray.length == 8) {
					boardResponseVO.setImagePath(tempStringArray[7]);
				}
				
				if(clubVO.getClubNumber() == boardResponseVO.getClubNumber()) {
					boardResponseVOList.add(boardResponseVO);
				}
			} // end while 
			
			System.out.println("boardResponseVOList::::"+boardResponseVOList);
			System.out.println("boardResponseVOList.size()::::"+boardResponseVOList.size());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return boardResponseVOList;
	}
}
