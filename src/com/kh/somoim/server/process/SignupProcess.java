package com.kh.somoim.server.process;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.kh.somoim.signup.model.vo.SignupRequestVO;

public class SignupProcess {

	private BufferedReader br;
	private BufferedWriter bw;

	public Object setSignup(Object requestObject) {
		// TODO Auto-generated method stub
		SignupRequestVO signupRequestVO = (SignupRequestVO)requestObject;
		
		try {
			
			/* userNumber 室特 */
			br = new BufferedReader(new FileReader("member.txt"));
			String[] tempStringArray; 
			String line = "";
			int maxNumber = 0;
			while((line = br.readLine()) != null) {
				tempStringArray = line.split("」」");
				if (maxNumber < Integer.parseInt(tempStringArray[0])) {
					maxNumber = Integer.parseInt(tempStringArray[0]);
				}
			}
			
			maxNumber += 1;
			
			bw = new BufferedWriter(new FileWriter("member.txt", true));
			
			String memberInformationStr = "";
			memberInformationStr += maxNumber + "」」";
			memberInformationStr += signupRequestVO.getId() + "」」";
			memberInformationStr += signupRequestVO.getBirth() + "」」";
			memberInformationStr += signupRequestVO.getPhoneNumber() + "」」";
			memberInformationStr += signupRequestVO.getEmail() + "」」";
			memberInformationStr += signupRequestVO.getPassword() + "」」";
			memberInformationStr += signupRequestVO.getName() + "」」";
			memberInformationStr += signupRequestVO.getGender() + "」」";
			memberInformationStr += signupRequestVO.getAddress() + "」」";
			memberInformationStr += 0 + "」」";
			
			for(int i=0; i<signupRequestVO.getFavorite().size(); i++) {
				if(i < signupRequestVO.getFavorite().size() - 1) {
					memberInformationStr += signupRequestVO.getFavorite().get(i) + ",";
				} else {
					memberInformationStr += signupRequestVO.getFavorite().get(i);
				}
			}
			memberInformationStr += "」」";
			memberInformationStr += "images/member/userprofile.png";
			
			System.out.println("memberInformationStr:::"+memberInformationStr);
			bw.newLine();
			bw.write(memberInformationStr);
			
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
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return "Ok";
	}
	
	
}
