package model;

public class OnDutyTime {
	private String allTime;
	private String studentName;
	private String studentNum;
	private int groupCode;
	private int gradeCode;
	private long allTimes;

	public OnDutyTime() {
	}

	public OnDutyTime(String allTime, String studentName, String studentNum,
			int groupCode, int gradeCode, long allTimes) {
		this.allTime = allTime;
		this.studentName = studentName;
		this.studentNum = studentNum;
		this.groupCode = groupCode;
		this.gradeCode = gradeCode;
		this.allTimes = allTimes;
	}

	public long getAllTimes() {
		return allTimes;
	}

	public void setAllTimes(long allTimes) {
		this.allTimes = allTimes;
	}

	public String getAllTime() {
		return allTime;
	}

	public void setAllTime(String allTime) {
		this.allTime = allTime;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getStudentNum() {
		return studentNum;
	}

	public void setStudentNum(String studentNum) {
		this.studentNum = studentNum;
	}

	public int getGroupCode() {
		return groupCode;
	}

	public void setGroupCode(int groupCode) {
		this.groupCode = groupCode;
	}

	public int getGradeCode() {
		return gradeCode;
	}

	public void setGradeCode(int gradeCode) {
		this.gradeCode = gradeCode;
	}

}
