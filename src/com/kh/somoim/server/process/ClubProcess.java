package com.kh.somoim.server.process;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.EOFException;
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
	private BufferedWriter tempBw;

	/**
	 * �Ҹ��� ����Ʈ
	 * @param obj
	 * @return
	 */
	public Object getClubMemberList(Object obj) {
		// TODO Auto-generated method stub
		ClubVO requestClubVO = (ClubVO)obj;

		ArrayList<Integer> requestMemberList = requestClubVO.getMembersNumber();
		System.out.println("requestMemberList::::"+requestMemberList);
		int masterNumber = requestClubVO.getClupMasterNumber();
		ArrayList<MemberVO> resoponseMemberList = new ArrayList<MemberVO>();
		try {
			br = new BufferedReader(new FileReader("member.txt"));
			String[] tempStringArray; 
			String[] temp2StringArray; 
			ArrayList<String> favoriteList = new ArrayList<String>();

			String line = "";
			
			while((line = br.readLine()) != null) {
				MemberVO memberVO = new MemberVO();

				tempStringArray = line.split("�ס�");

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
			
			System.out.println("resoponseMemberList::::"+resoponseMemberList);
			
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
		return resoponseMemberList;
	}

	/**
	 * �� ����
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
				tempStringArray = line.split("�ס�");
				if (maxNumber < Integer.parseInt(tempStringArray[0])) {
					maxNumber = Integer.parseInt(tempStringArray[0]);
				}
			}

			maxNumber += 1;

			String boardStr = "";
			boardStr += maxNumber + "�ס�";
			boardStr += boardVO.getClubNumber() + "�ס�";
			boardStr += boardVO.getTitle() + "�ס�";
			boardStr += boardVO.getContent() + "�ס�";
			boardStr += boardVO.getWriter() + "�ס�";

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd hh:mm:ss");
			String date = sdf.format(boardVO.getWriteDay());
			boardStr += date + "�ס�";
			boardStr += boardVO.getBoardSelect() + "�ס�";
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
				if(br != null) {
					br.close();
				}
				
				if(bw != null) {
					bw.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}


		return null;
	}

	/**
	 * ���� �λ� ����Ʈ ���
	 * @param requestObject
	 * @return
	 */
	public Object getFirstGreeting(Object obj) {
		// TODO Auto-generated method stub
		MemberInClubVO memberInClubVO = (MemberInClubVO)obj;


		ArrayList<MemberVO> memberList = new ArrayList<MemberVO>();
		try {
			br = new BufferedReader(new FileReader("member.txt"));
			String[] tempStringArray;
			String[] temp2StringArray;
			ArrayList<String> favoriteList = new ArrayList<String>();

			String line = "";
			while((line = br.readLine()) != null) {
				MemberVO memberVO = new MemberVO();

				tempStringArray = line.split("�ס�");

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
				if(br != null) {
					br.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		ClubVO clubVO = memberInClubVO.getClubVO();
		String category = memberInClubVO.getSelectedCategory();
		ArrayList<BoardResponseVO> boardResponseVOList = new ArrayList<BoardResponseVO>(); 
		try {
			br = new BufferedReader(new FileReader("board.txt"));
			String[] tempStringArray; 
			String line = "";
			while((line = br.readLine()) != null) {
				BoardResponseVO boardResponseVO = new BoardResponseVO();
				tempStringArray = line.split("�ס�");
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
						, Integer.parseInt(tempDay[1]) - 1
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

				if(clubVO.getClubNumber() == boardResponseVO.getClubNumber() && boardResponseVO.getBoardSelect().equals(category)) {
					boardResponseVOList.add(boardResponseVO);
				}
			} // end while 

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
				
				if(tempBr != null) {
					tempBr.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return boardResponseVOList;
	}

	/**
	 * �Ҹ��� ����
	 * @param obj
	 * @return
	 */
	public Object createClub(Object obj) {
		// TODO Auto-generated method stub
		ClubVO clubVO = (ClubVO)obj;

		try {
			br = new BufferedReader(new FileReader("club.txt"));
			String[] tempStringArray; 
			String line = "";
			int maxNumber = 0;
			while((line = br.readLine()) != null) {
				tempStringArray = line.split("�ס�");
				if (maxNumber < Integer.parseInt(tempStringArray[0])) {
					maxNumber = Integer.parseInt(tempStringArray[0]);
				}
			}

			maxNumber += 1;

			String clubInserString = "";
			clubInserString += clubVO.getClubNumber() + "�ס�";
			clubInserString += clubVO.getName()  + "�ס�";
			clubInserString += clubVO.getClupMasterNumber() + "�ס�";
			clubInserString += clubVO.getInformation() + "�ס�";

			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			String meetingDay = sdf.format(clubVO.getMeetingDay());
			clubInserString += meetingDay + "�ס�";
			
			if(clubVO.getTitleImagePath().equals("images/default.png")) {
				clubInserString += clubVO.getTitleImagePath() + "�ס�";
			} else {
				clubInserString += "images/club/" + maxNumber + "." +clubVO.getTitleImagePath().split("[.]")[1] + "�ס�";
			}
			clubInserString += clubVO.getCategory() + "�ס�";
			clubInserString += clubVO.getClupMasterNumber();

			bw = new BufferedWriter(new FileWriter("club.txt", true));
			bw.newLine();
			bw.write(clubInserString);
			bw.flush();

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
				if(br != null) {
					br.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return "";
	}

	public Object getMaxNumber(Object obj) {
		// TODO Auto-generated method stub
		ClubVO clubVO = (ClubVO)obj;
		Integer maxNumber = 0;

		try {
			br = new BufferedReader(new FileReader("club.txt"));
			String[] tempStringArray; 
			String line = "";
			while((line = br.readLine()) != null) {
				tempStringArray = line.split("�ס�");
				if (maxNumber < Integer.parseInt(tempStringArray[0])) {
					maxNumber = Integer.parseInt(tempStringArray[0]);
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return maxNumber;
	}

	public Object leaveClub(Object obj) {
		// TODO Auto-generated method stub
		MemberInClubVO memberInClubVO = (MemberInClubVO)obj;
		
		ClubVO requestClubVO = memberInClubVO.getClubVO();
		MemberVO memberVO = memberInClubVO.getMemberVO();
				
		int requestClubNumber = requestClubVO.getClubNumber();
		int requestMemberNumber = memberVO.getUserNumber();
		ClubVO resultClub = null;
		
		try {
			
			br = new BufferedReader(new FileReader("club.txt"));
			tempBw = new BufferedWriter(new FileWriter("temp_club.txt"));
			String[] tempStringArray;
			String[] temp2StringArray;
			
			int year = 0;
			int month = 0;
			int day = 0;
			Date resultMeetingDay = null;
			
			String line = "";
			while((line = br.readLine()) != null) {
				
				ClubVO clubVO = new ClubVO();
				ArrayList<Integer> memberList = new ArrayList<Integer>();
				tempStringArray = line.split("�ס�");
				
				clubVO.setClubNumber(Integer.parseInt(tempStringArray[0]));
				clubVO.setName(tempStringArray[1]);
				clubVO.setClupMasterNumber(Integer.parseInt(tempStringArray[2]));
				clubVO.setInformation(tempStringArray[3]);

				year = Integer.parseInt(tempStringArray[4].substring(0, 4));
				month = Integer.parseInt(tempStringArray[4].substring(4, 6)) - 1;
				day = Integer.parseInt(tempStringArray[4].substring(6, 8));
				resultMeetingDay = new Date(new GregorianCalendar(year, month, day).getTimeInMillis());
				clubVO.setMeetingDay(resultMeetingDay);

				clubVO.setTitleImagePath(tempStringArray[5]);
				clubVO.setCategory(tempStringArray[6]);
				
				if(tempStringArray[7].contains(",")) {
					temp2StringArray = tempStringArray[7].split(",");
				} else {
					temp2StringArray = new String[1];
					temp2StringArray[0] = tempStringArray[7];
				}
				for(String signupMemberNumber : temp2StringArray) {
					memberList.add(Integer.parseInt(signupMemberNumber));
				}
				clubVO.setMembersNumber(memberList);
				
				if(clubVO.getClubNumber() == requestClubNumber) {
					resultClub = clubVO;
					continue;
				}
				
				tempBw.write(line + System.getProperty("line.separator"));
			}
			
			tempBw.flush();
			
			ArrayList<Integer> list = resultClub.getMembersNumber();
			for(int i=0; i<list.size(); i++) {
				if(list.get(i) == requestMemberNumber) {
					list.remove(i);
				}
			}
			
			resultClub.setMembersNumber(list);
			
			System.out.println(resultClub);
			
			String clubInserString = "";
			clubInserString += resultClub.getClubNumber() + "�ס�";
			clubInserString += resultClub.getName()  + "�ס�";
			clubInserString += resultClub.getClupMasterNumber() + "�ס�";
			clubInserString += resultClub.getInformation() + "�ס�";

			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			String resultMeetingDay1 = sdf.format(resultClub.getMeetingDay());
			clubInserString += resultMeetingDay1 + "�ס�";
			clubInserString += resultClub.getTitleImagePath() + "�ס�";
			clubInserString += resultClub.getCategory() + "�ס�";
			
			for(int i=0; i<list.size(); i++) {
				if(i < list.size() - 1) {
					clubInserString += list.get(i) + ",";
				} else {
					clubInserString += list.get(i) + "";
				}
			}
			
			System.out.println("clubInserString::::"+clubInserString);
			
			tempBw.flush();
			
			tempBr = new BufferedReader(new FileReader("temp_club.txt"));
			
			bw = new BufferedWriter(new FileWriter("club.txt"));
			while((line = tempBr.readLine()) != null) {
				
				bw.write(line);
				bw.newLine();
			}
			bw.flush();
			
			bw = new BufferedWriter(new FileWriter("club.txt", true));
			bw.write(clubInserString);
			bw.flush();
			
		} catch (FileNotFoundException | EOFException e) {
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
				
				if(bw != null) {
					bw.close();
				}
				
				if(tempBr != null) {
					tempBr.close();
				}
				
				if(tempBw != null) {
					tempBw.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return "";
	}

	public Object signupClub(Object obj) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
				MemberInClubVO memberInClubVO = (MemberInClubVO)obj;
				
				ClubVO requestClubVO = memberInClubVO.getClubVO();
				MemberVO memberVO = memberInClubVO.getMemberVO();
						
				int requestClubNumber = requestClubVO.getClubNumber();
				int requestMemberNumber = memberVO.getUserNumber();
				ClubVO resultClub = null;
				
				System.out.println("requestClubNumber::" + requestClubNumber);
				System.out.println("requestMemberNumber::" + requestMemberNumber);
				
				try {
					
					br = new BufferedReader(new FileReader("club.txt"));
					tempBw = new BufferedWriter(new FileWriter("temp_club.txt"));
					String[] tempStringArray;
					String[] temp2StringArray;
					
					int year = 0;
					int month = 0;
					int day = 0;
					Date resultMeetingDay = null;
					
					String line = "";
					while((line = br.readLine()) != null) {
						
						ClubVO clubVO = new ClubVO();
						ArrayList<Integer> memberList = new ArrayList<Integer>();
						tempStringArray = line.split("�ס�");
						
						clubVO.setClubNumber(Integer.parseInt(tempStringArray[0]));
						clubVO.setName(tempStringArray[1]);
						clubVO.setClupMasterNumber(Integer.parseInt(tempStringArray[2]));
						clubVO.setInformation(tempStringArray[3]);

						year = Integer.parseInt(tempStringArray[4].substring(0, 4));
						month = Integer.parseInt(tempStringArray[4].substring(4, 6)) - 1;
						day = Integer.parseInt(tempStringArray[4].substring(6, 8));
						resultMeetingDay = new Date(new GregorianCalendar(year, month, day).getTimeInMillis());
						clubVO.setMeetingDay(resultMeetingDay);

						clubVO.setTitleImagePath(tempStringArray[5]);
						clubVO.setCategory(tempStringArray[6]);
						
						if(tempStringArray[7].contains(",")) {
							temp2StringArray = tempStringArray[7].split(",");
						} else {
							temp2StringArray = new String[1];
							temp2StringArray[0] = tempStringArray[7];
						}
						for(String signupMemberNumber : temp2StringArray) {
							memberList.add(Integer.parseInt(signupMemberNumber));
						}
						clubVO.setMembersNumber(memberList);
						
						System.out.println("clubVO.getClubNumber():::"+clubVO.getClubNumber());
						if(clubVO.getClubNumber() == requestClubNumber) {
							
							System.out.println("in�̴�!!!!");
							
							resultClub = clubVO;
							continue;
						}
						
						tempBw.write(line + System.getProperty("line.separator"));
					}
					
					tempBw.flush();
					
					ArrayList<Integer> list = resultClub.getMembersNumber();
					list.add(requestMemberNumber);
					
					resultClub.setMembersNumber(list);
					
					System.out.println(resultClub);
					
					String clubInserString = "";
					clubInserString += resultClub.getClubNumber() + "�ס�";
					clubInserString += resultClub.getName()  + "�ס�";
					clubInserString += resultClub.getClupMasterNumber() + "�ס�";
					clubInserString += resultClub.getInformation() + "�ס�";

					SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
					String resultMeetingDay1 = sdf.format(resultClub.getMeetingDay());
					clubInserString += resultMeetingDay1 + "�ס�";
					clubInserString += resultClub.getTitleImagePath() + "�ס�";
					clubInserString += resultClub.getCategory() + "�ס�";
					
					for(int i=0; i<list.size(); i++) {
						if(i < list.size() - 1) {
							clubInserString += list.get(i) + ",";
						} else {
							clubInserString += list.get(i) + "";
						}
					}
					
					System.out.println("clubInserString::::"+clubInserString);
					
					tempBw = new BufferedWriter(new FileWriter("temp_club.txt", true));
					tempBw.flush();
					
					tempBr = new BufferedReader(new FileReader("temp_club.txt"));
					
					bw = new BufferedWriter(new FileWriter("club.txt"));
					while((line = tempBr.readLine()) != null) {
						if(line.equals("")) {
							break;
						}
						bw.write(line + System.getProperty("line.separator"));
					}
					bw.flush();
					
					bw = new BufferedWriter(new FileWriter("club.txt", true));
					bw.write(clubInserString);
					bw.flush();
					
				} catch (FileNotFoundException | EOFException e) {
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
						
						if(bw != null) {
							bw.close();
						}
						
						if(tempBr != null) {
							tempBr.close();
						}
						
						if(tempBw != null) {
							tempBw.close();
						}
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				return "";
	}
}
