package com.dxc.bookshelf.user.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author sgangal2
 *
 */
@Entity
//@Entity annotation defines that a class can be mapped to a table.
//we are using mysql database here.
//and the table name is user_info.

@Table(name = "user_info")
public class User {

	 @Id
	   private String userId;
	   private String userName;
	   private String password;
	   private String gender;
	   private String country;
	   
	 //constructors with the parameters and without parameters are being implemented.

	/**
	 * no argument constructor
	 */
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}


	/**
	 * @param userId
	 * @param userName
	 * @param password
	 * @param gender
	 * @param country
	 */
	public User(String userId, String userName, String password, String gender, String country) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.password = password;
		this.gender = gender;
		this.country = country;
	}

	//setters and getters are being implemented.

	/**
	 * @return userId
	 */
	public String getUserId() {
		return userId;
	}


	/**
	 * @param userId
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}


	/**
	 * @return userName
	 */
	public String getUserName() {
		return userName;
	}


	/**
	 * @param userName
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}


	/**
	 * @return password
	 */
	public String getPassword() {
		return password;
	}


	/**
	 * @param password
	 */
	public void setPassword(String password) {
		this.password = password;
	}


	/**
	 * @return gender
	 */
	public String getGender() {
		return gender;
	}


	/**
	 * @param gender
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}


	/**
	 * @return country
	 */
	public String getCountry() {
		return country;
	}


	/**
	 * @param country
	 */
	public void setCountry(String country) {
		this.country = country;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((country == null) ? 0 : country.hashCode());
		result = prime * result + ((gender == null) ? 0 : gender.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		result = prime * result + ((userName == null) ? 0 : userName.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (country == null) {
			if (other.country != null)
				return false;
		} else if (!country.equals(other.country))
			return false;
		if (gender == null) {
			if (other.gender != null)
				return false;
		} else if (!gender.equals(other.gender))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		if (userName == null) {
			if (other.userName != null)
				return false;
		} else if (!userName.equals(other.userName))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName + ", password=" + password + ", gender=" + gender
				+ ", country=" + country + "]";
	}
	   
	   
}
