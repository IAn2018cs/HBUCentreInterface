package model;

public class User {
	private String account;
	private String name;
	private int grade;
	private String clas;
	private int groupCode;
	private String phone;
	private int type;

	public User() {
	}

	public User(String account, String name, int grade, String clas,
			int groupCode, String phone) {
		this.account = account;
		this.name = name;
		this.grade = grade;
		this.clas = clas;
		this.groupCode = groupCode;
		this.phone = phone;
	}
	
	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public int getGroupCode() {
		return groupCode;
	}

	public void setGroupCode(int groupCode) {
		this.groupCode = groupCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	public String getClas() {
		return clas;
	}

	public void setClas(String clas) {
		this.clas = clas;
	}

}
