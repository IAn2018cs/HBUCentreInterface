package model;

public class Login {
	private String account;
	private String password;
	private int type;
	private String name;
	private int gradeCode;
	private String classDes;
	private String newImage;
	private String oldImage;
	private int group;
	private String phone;
	private int interestGroupCode;

	public int getInterestGroupCode() {
		return interestGroupCode;
	}

	public void setInterestGroupCode(int interestGroupCode) {
		this.interestGroupCode = interestGroupCode;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getGroup() {
		return group;
	}

	public void setGroup(int group) {
		this.group = group;
	}

	public String getOldImage() {
		return oldImage;
	}

	public void setOldImage(String oldImage) {
		this.oldImage = oldImage;
	}

	public int getGradeCode() {
		return gradeCode;
	}

	public void setGradeCode(int gradeCode) {
		this.gradeCode = gradeCode;
	}

	public String getClassDes() {
		return classDes;
	}

	public void setClassDes(String classDes) {
		this.classDes = classDes;
	}

	public String getNewImage() {
		return newImage;
	}

	public void setNewImage(String image) {
		this.newImage = image;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public Login(String account, String password, int type, String name,
			int gradeCode, String classDes, String newImage, String oldImage) {
		super();
		this.account = account;
		this.password = password;
		this.type = type;
		this.name = name;
		this.gradeCode = gradeCode;
		this.classDes = classDes;
		this.newImage = newImage;
		this.oldImage = oldImage;
	}
}
