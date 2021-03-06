package com.kh.somoim.server.process;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.kh.somoim.login.model.vo.MemberVO;

public class SettingProcess {

	private BufferedReader br;
	private BufferedWriter bw;
	private BufferedReader tempBr;
	private BufferedWriter tempBw;

	/**
	 * 鯵昔舛左 呪舛
	 * @param obj
	 * @return
	 */
	public Object setInformation(Object obj) {
		// TODO Auto-generated method stub
		MemberVO requestMemberVO = (MemberVO)obj;
		int userNumber = requestMemberVO.getUserNumber();

		try {
			bw = new BufferedWriter(new FileWriter("member.txt",true));
			br = new BufferedReader(new FileReader("member.txt"));
			tempBw = new BufferedWriter(new FileWriter("temp_member.txt"));
			tempBr = new BufferedReader(new FileReader("temp_member.txt"));

			/* remove */
			String[] tempStringArray; 
			String line = "";
			while((line = br.readLine()) != null) {

				tempStringArray = line.split("」」");

				if(userNumber == Integer.parseInt(tempStringArray[0])) {
					continue;
				} 
				tempBw.write(line + System.getProperty("line.separator"));
			}

			/* write */
			String memberInformationStr = "";
			memberInformationStr += requestMemberVO.getUserNumber() + "」」";
			memberInformationStr += requestMemberVO.getId() + "」」";
			memberInformationStr += requestMemberVO.getBirth() + "」」";
			memberInformationStr += requestMemberVO.getPhoneNumber() + "」」";
			memberInformationStr += requestMemberVO.getEmail() + "」」";
			memberInformationStr += requestMemberVO.getPassword() + "」」";
			memberInformationStr += requestMemberVO.getName() + "」」";
			memberInformationStr += requestMemberVO.getGender() + "」」";
			memberInformationStr += requestMemberVO.getAddress() + "」」";
			memberInformationStr += requestMemberVO.getPoint() + "」」";

			for(int i=0; i<requestMemberVO.getFavorite().size(); i++) {
				if(i < requestMemberVO.getFavorite().size() - 1) {
					memberInformationStr += requestMemberVO.getFavorite().get(i) + ",";
				} else {
					memberInformationStr += requestMemberVO.getFavorite().get(i);
				}
			}
			
			memberInformationStr += "」」" + requestMemberVO.getProfilePhotoPath();
			
			tempBw.flush();

			bw = new BufferedWriter(new FileWriter("member.txt"));
			while((line = tempBr.readLine()) != null) {
				bw.write(line + System.getProperty("line.separator"));
			}
			bw.flush();
			
			bw = new BufferedWriter(new FileWriter("member.txt", true));
			
			bw.write(memberInformationStr);
			bw.flush();
			
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

		return null;
	}

	public Object removeMember(Object obj) {
		// TODO Auto-generated method stub

		MemberVO requestMemberVO = (MemberVO)obj;
		int userNumber = requestMemberVO.getUserNumber();

		try {

			bw = new BufferedWriter(new FileWriter("member.txt",true));
			br = new BufferedReader(new FileReader("member.txt"));
			tempBw = new BufferedWriter(new FileWriter("temp_member.txt"));
			tempBr = new BufferedReader(new FileReader("temp_member.txt"));

			/* remove */
			String[] tempStringArray; 
			String line = "";
			while((line = br.readLine()) != null) {

				tempStringArray = line.split("」」");

				if(userNumber == Integer.parseInt(tempStringArray[0])) {
					continue;
				} 
				tempBw.write(line + System.getProperty("line.separator"));
			}
			tempBw.flush();

			bw = new BufferedWriter(new FileWriter("member.txt"));
			while((line = tempBr.readLine()) != null) {
				bw.write(line + System.getProperty("line.separator"));
			}
			bw.flush();

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
		return null;
	}

	public Object setProfilePhoto(Object obj) {
		// TODO Auto-generated method stub
		MemberVO requestMemberVO = (MemberVO)obj;
		int userNumber = requestMemberVO.getUserNumber();

		try {
			bw = new BufferedWriter(new FileWriter("member.txt",true));
			br = new BufferedReader(new FileReader("member.txt"));
			tempBw = new BufferedWriter(new FileWriter("temp_member.txt"));
			tempBr = new BufferedReader(new FileReader("temp_member.txt"));

			/* remove */
			String[] tempStringArray; 
			String line = "";
			while((line = br.readLine()) != null) {

				tempStringArray = line.split("」」");

				if(userNumber == Integer.parseInt(tempStringArray[0])) {
					continue;
				} 
				tempBw.write(line + System.getProperty("line.separator"));
			}

			/* write */
			String memberInformationStr = "";
			memberInformationStr += requestMemberVO.getUserNumber() + "」」";
			memberInformationStr += requestMemberVO.getId() + "」」";
			memberInformationStr += requestMemberVO.getBirth() + "」」";
			memberInformationStr += requestMemberVO.getPhoneNumber() + "」」";
			memberInformationStr += requestMemberVO.getEmail() + "」」";
			memberInformationStr += requestMemberVO.getPassword() + "」」";
			memberInformationStr += requestMemberVO.getName() + "」」";
			memberInformationStr += requestMemberVO.getGender() + "」」";
			memberInformationStr += requestMemberVO.getAddress() + "」」";
			memberInformationStr += requestMemberVO.getPoint() + "」」";

			for(int i=0; i<requestMemberVO.getFavorite().size(); i++) {
				if(i < requestMemberVO.getFavorite().size() - 1) {
					memberInformationStr += requestMemberVO.getFavorite().get(i) + ",";
				} else {
					memberInformationStr += requestMemberVO.getFavorite().get(i);
				}
			}
			
			memberInformationStr += "」」" + requestMemberVO.getProfilePhotoPath();
			
			System.out.println("memberInformationStr ::: " + memberInformationStr);
			tempBw.flush();

			bw = new BufferedWriter(new FileWriter("member.txt"));
			while((line = tempBr.readLine()) != null) {
				bw.write(line + System.getProperty("line.separator"));
			}
			
			bw.flush();

			bw = new BufferedWriter(new FileWriter("member.txt", true));
			bw.write(memberInformationStr);
			bw.flush();
			
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

		return null;
	}

}
