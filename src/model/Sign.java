package model;

public class Sign {
	private int id;
	private String studentNum;
	private String inTime;
	private String outTime;
	private int activityId;
	private String inLocation;
	private String outLocation;

	public String getInLocation() {
		return inLocation;
	}

	public void setInLocation(String inLocation) {
		this.inLocation = inLocation;
	}

	public String getOutLocation() {
		return outLocation;
	}

	public void setOutLocation(String outLocation) {
		this.outLocation = outLocation;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStudentNum() {
		return studentNum;
	}

	public void setStudentNum(String num) {
		this.studentNum = num;
	}

	public String getInTime() {
		return inTime;
	}

	public void setInTime(String inTime) {
		this.inTime = inTime;
	}

	public String getOutTime() {
		return outTime;
	}

	public void setOutTime(String outTime) {
		this.outTime = outTime;
	}

	public int getActivityId() {
		return activityId;
	}

	public void setActivityId(int activityId) {
		this.activityId = activityId;
	}

}
