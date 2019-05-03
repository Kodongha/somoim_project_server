package com.kh.somoim.server.process;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

import com.kh.somoim.home.model.vo.ClubVO;
import com.kh.somoim.login.model.vo.MemberVO;

public class LoginrProcess {

	private BufferedReader br;
	private BufferedWriter bw;
	private BufferedReader tempBr;
	private BufferedWriter tempBw;

	public LoginrProcess() {
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
				memberVO.setProfilePhotoPath(tempStringArray[11]);


				if(id.equals(memberVO.getId()) && password.equals(memberVO.getPassword())) {
					System.out.println("로그인 인증 완료 : " + memberVO.toString());
					return memberVO;
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

		System.out.println("로그인 인증 실패");
		memberVO = null;
		return memberVO;

	}

	public Object getMyId(Object obj) {
		// TODO Auto-generated method stub
		MemberVO requestMemberVO = (MemberVO)obj;
		String name = requestMemberVO.getName();
		String phoneNumber = requestMemberVO.getPhoneNumber();

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
				memberVO.setProfilePhotoPath(tempStringArray[11]);

				if(name.equals(memberVO.getName()) && phoneNumber.equals(memberVO.getPhoneNumber())) {
					System.out.println("아이디 찾기 완료 : " + memberVO.toString());
					return memberVO.getId();
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

		System.out.println("아이디 찾기 실패");
		return "";
	}

	public Object getMyidForPassword(Object obj) {
		// TODO Auto-generated method stub
		MemberVO requestMemberVO = (MemberVO)obj;
		String id = requestMemberVO.getId();

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
				memberVO.setProfilePhotoPath(tempStringArray[11]);

				if(id.equals(memberVO.getId())) {
					System.out.println("아이디 찾기 완료 : " + memberVO.toString());
					return true;
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
		System.out.println("아이디 찾기 실패");
		return false;
	}

	public Object updatePassword(Object obj) {
		// TODO Auto-generated method stub

		MemberVO requestMemberVO = (MemberVO)obj;
		String id = requestMemberVO.getId();

		try {
			bw = new BufferedWriter(new FileWriter("member.txt",true));
			br = new BufferedReader(new FileReader("member.txt"));
			tempBw = new BufferedWriter(new FileWriter("temp_member.txt"));
			tempBr = new BufferedReader(new FileReader("temp_member.txt"));

			/* remove */
			String[] tempStringArray; 
			String[] temp2StringArray; 
			ArrayList<String> favoriteList = new ArrayList<String>();
			String line = "";
			MemberVO memberVO = new MemberVO();
			while((line = br.readLine()) != null) {

				tempStringArray = line.split("§§");
				if(id.equals(tempStringArray[1])) {
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
					continue;
				} 
				tempBw.write(line + System.getProperty("line.separator"));
			}

			/* write */
			String memberInformationStr = "";
			memberInformationStr += memberVO.getUserNumber() + "§§";
			memberInformationStr += memberVO.getId() + "§§";
			memberInformationStr += memberVO.getBirth() + "§§";
			memberInformationStr += memberVO.getPhoneNumber() + "§§";
			memberInformationStr += memberVO.getEmail() + "§§";
			memberInformationStr += requestMemberVO.getPassword() + "§§";
			memberInformationStr += memberVO.getName() + "§§";
			memberInformationStr += memberVO.getGender() + "§§";
			memberInformationStr += memberVO.getAddress() + "§§";
			memberInformationStr += memberVO.getPoint() + "§§";

			for(int i=0; i<memberVO.getFavorite().size(); i++) {
				if(i < memberVO.getFavorite().size() - 1) {
					memberInformationStr += memberVO.getFavorite().get(i) + ",";
				} else {
					memberInformationStr += memberVO.getFavorite().get(i);
				}
			}

			memberInformationStr += "§§" + memberVO.getProfilePhotoPath();

			tempBw.write(memberInformationStr);
			tempBw.flush();

			bw = new BufferedWriter(new FileWriter("member.txt"));
			while((line = tempBr.readLine()) != null) {
				bw.write(line + System.getProperty("line.separator"));
			}
			bw.flush();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				br.close();
				bw.close();
				tempBr.close();
				tempBw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return null;
	}

}
