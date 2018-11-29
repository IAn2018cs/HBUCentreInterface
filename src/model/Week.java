package model;

public class Week {
	private int nid;
	private String startDate;
	private String endDate;
	private int weekCode;

	public Week() {
	}

	public Week(int nid, String startDate, String endDate, int weekCode) {
		this.nid = nid;
		this.startDate = startDate;
		this.endDate = endDate;
		this.weekCode = weekCode;
	}

	public int getNid() {
		return nid;
	}

	public void setNid(int nid) {
		this.nid = nid;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public int getWeekCode() {
		return weekCode;
	}

	public void setWeekCode(int weekCode) {
		this.weekCode = weekCode;
	}

}
