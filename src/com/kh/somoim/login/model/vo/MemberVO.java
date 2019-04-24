package com.kh.somoim.login.model.vo;

import java.io.Serializable;
import java.util.ArrayList;

public class MemberVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 273915039175507437L;
	private String id;          // id
	private String birth;       // birth(yyyyMMdd)
	private String phoneNumber; // phoneNumber(010-xxxx-xxxx) 
	private String email;       // email
	private String password;    // password
	private String name;        // name
	private String gender;      // gender(남/여)
	private String address;     // address(서울,경기도...)
	private ArrayList<String> favorite; // favorite(관심사)

	public MemberVO() {
		// TODO Auto-generated constructor stub
	}

	public MemberVO(String id, String birth, String phoneNumber, String email, String password, String name,
			String gender, String address, ArrayList<String> favorite) {
		super();
		this.id = id;
		this.birth = birth;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.password = password;
		this.name = name;
		this.gender = gender;
		this.address = address;
		this.favorite = favorite;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBirth() {
		return birth;
	}

	public void setBirth(String birth) {
		this.birth = birth;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public ArrayList<String> getFavorite() {
		return favorite;
	}

	public void setFavorite(ArrayList<String> favorite) {
		this.favorite = favorite;
	}

	@Override
	public String toString() {
		return "UserVO [id=" + id + ", birth=" + birth + ", phoneNumber=" + phoneNumber + ", email=" + email
				+ ", password=" + password + ", name=" + name + ", gender=" + gender + ", address=" + address
				+ ", favorite=" + favorite + "]";
	}

}
