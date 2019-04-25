package com.kh.somoim.login.model.vo;

import java.io.Serializable;
import java.util.ArrayList;

public class MemberVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3805317309781810549L;
	private int userNumber;		// user number		
	private String id;          // id
	private String birth;       // birth(yyyyMMdd)
	private String phoneNumber; // phoneNumber(010-xxxx-xxxx) 
	private String email;       // email
	private String password;    // password
	private String name;        // name
	private String gender;      // gender(남/여)
	private String address;     // address(서울,경기도...)
	private int point;     		// address(서울,경기도...)
	private ArrayList<String> favorite; // favorite(관심사)

	public MemberVO() {
		// TODO Auto-generated constructor stub
	}

	public MemberVO(int userNumber, String id, String birth, String phoneNumber, String email, String password,
			String name, String gender, String address, int point, ArrayList<String> favorite) {
		super();
		this.userNumber = userNumber;
		this.id = id;
		this.birth = birth;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.password = password;
		this.name = name;
		this.gender = gender;
		this.address = address;
		this.point = point;
		this.favorite = favorite;
	}

	/**
	 * @return the userNumber
	 */
	public int getUserNumber() {
		return userNumber;
	}

	/**
	 * @param userNumber the userNumber to set
	 */
	public void setUserNumber(int userNumber) {
		this.userNumber = userNumber;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the birth
	 */
	public String getBirth() {
		return birth;
	}

	/**
	 * @param birth the birth to set
	 */
	public void setBirth(String birth) {
		this.birth = birth;
	}

	/**
	 * @return the phoneNumber
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * @param phoneNumber the phoneNumber to set
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
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
	 * @return the gender
	 */
	public String getGender() {
		return gender;
	}

	/**
	 * @param gender the gender to set
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the point
	 */
	public int getPoint() {
		return point;
	}

	/**
	 * @param point the point to set
	 */
	public void setPoint(int point) {
		this.point = point;
	}

	/**
	 * @return the favorite
	 */
	public ArrayList<String> getFavorite() {
		return favorite;
	}

	/**
	 * @param favorite the favorite to set
	 */
	public void setFavorite(ArrayList<String> favorite) {
		this.favorite = favorite;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "MemberVO [userNumber=" + userNumber + ", id=" + id + ", birth=" + birth + ", phoneNumber=" + phoneNumber
				+ ", email=" + email + ", password=" + password + ", name=" + name + ", gender=" + gender + ", address="
				+ address + ", point=" + point + ", favorite=" + favorite + "]";
	}
}
