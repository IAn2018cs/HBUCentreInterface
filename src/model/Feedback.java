package model;

public class Feedback {
	private int id;
	private String account;
	private String msg;
	private String PhoneBrand;
	private String PhoneBrandType;
	private String AndroidVersion;
	private int anonymous;
	private String time;
	private String name;
	private int gradeCode;
	private int groupCode;

	public Feedback(String account, String msg, String phoneBrand,
			String phoneBrandType, String androidVersion, int anonymous) {
		this.account = account;
		this.msg = msg;
		PhoneBrand = phoneBrand;
		PhoneBrandType = phoneBrandType;
		AndroidVersion = androidVersion;
		this.anonymous = anonymous;
	}

	public Feedback(int id, String account, String msg, String phoneBrand,
			String phoneBrandType, String androidVersion, int anonymous, String time) {
		this.id = id;
		this.account = account;
		this.msg = msg;
		PhoneBrand = phoneBrand;
		PhoneBrandType = phoneBrandType;
		AndroidVersion = androidVersion;
		this.anonymous = anonymous;
		this.time = time;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getGradeCode() {
		return gradeCode;
	}

	public void setGradeCode(int gradeCode) {
		this.gradeCode = gradeCode;
	}

	public int getGroupCode() {
		return groupCode;
	}

	public void setGroupCode(int groupCode) {
		this.groupCode = groupCode;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public int getAnonymous() {
		return anonymous;
	}

	public void setAnonymous(int anonymous) {
		this.anonymous = anonymous;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getPhoneBrand() {
		return PhoneBrand;
	}

	public void setPhoneBrand(String phoneBrand) {
		PhoneBrand = phoneBrand;
	}

	public String getPhoneBrandType() {
		return PhoneBrandType;
	}

	public void setPhoneBrandType(String phoneBrandType) {
		PhoneBrandType = phoneBrandType;
	}

	public String getAndroidVersion() {
		return AndroidVersion;
	}

	public void setAndroidVersion(String androidVersion) {
		AndroidVersion = androidVersion;
	}

}
