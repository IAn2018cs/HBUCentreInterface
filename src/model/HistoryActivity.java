package model;

public class HistoryActivity {
	private int id;
	private String activityName;
	private String activityDes;
	private String time;
	private String endTime;
	private String location;
	private String studentNum;
	private String inTime;
	private String outTime;
	private int rule;

	public HistoryActivity() {
	}

	public HistoryActivity(int id, String activityName, String activityDes,
			String time, String endTime, String location, String studentNum,
			String inTime, String outTime, int rule) {
		this.id = id;
		this.activityName = activityName;
		this.activityDes = activityDes;
		this.time = time;
		this.endTime = endTime;
		this.location = location;
		this.studentNum = studentNum;
		this.inTime = inTime;
		this.outTime = outTime;
		this.rule = rule;
	}

	public int getRule() {
		return rule;
	}

	public void setRule(int rule) {
		this.rule = rule;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getStudentNum() {
		return studentNum;
	}

	public void setStudentNum(String studentNum) {
		this.studentNum = studentNum;
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

}
