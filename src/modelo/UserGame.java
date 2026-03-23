package modelo;

import java.time.LocalDate;

public class UserGame {
	private String userName;
	private String passwordUser;
	private int birthDate;
	
	public UserGame(String userName,String passwordUser,int birthDate) {
		this.userName=userName;
		this.passwordUser=passwordUser;
		this.birthDate=birthDate;
	}
	public UserGame(String userName,String passwordUser) {
		this.userName=userName;
		this.passwordUser=passwordUser;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPasswordUser() {
		return passwordUser;
	}

	public void setPasswordUser(String passwordUser) {
		this.passwordUser = passwordUser;
	}

	public int getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(int birthDate) {
		this.birthDate = birthDate;
	}

	@Override
	public String toString() {
		return "UserGame [userName=" + userName + ", passwordUser=" + passwordUser + ", birthDate=" + birthDate + "]";
	}
	
	
}