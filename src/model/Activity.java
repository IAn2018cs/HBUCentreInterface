package model;

public class Activity {
	private int id;
	private String account;
	private String activityName;
	private String activityDes;
	private String time;
	private String endTime;
	private String location;
	private int rule;
	private int display;
	private String studentNum;
	private int backTo;
	private int week;

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

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public int getDisplay() {
		return display;
	}

	public void setDisplay(int show) {
		this.display = show;
	}

	public String getActivityName() {
		return activityName;
	}

	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}

	public String getActivityDes() {
		return activityDes;
	}

	public void setActivityDes(String activityDes) {
		this.activityDes = activityDes;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public int getRule() {
		return rule;
	}

	public void setRule(int rule) {
		this.rule = rule;
	}

	public String getStudentNum() {
		return studentNum;
	}

	public void setStudentNum(String studentNum) {
		this.studentNum = studentNum;
	}

	public int getBackTo() {
		return backTo;
	}

	public void setBackTo(int backTo) {
		this.backTo = backTo;
	}

	public int getWeek() {
		return week;
	}

	public void setWeek(int week) {
		this.week = week;
	}

}
