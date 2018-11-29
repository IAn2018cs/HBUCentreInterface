package model;

public class OnDutySign {
	private String name;
	private int groupCode;
	private int backTo;
	private String inTime;
	private String outTime;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getGroupCode() {
		return groupCode;
	}

	public void setGroupCode(int groupCode) {
		this.groupCode = groupCode;
	}

	public int getBackTo() {
		return backTo;
	}

	public void setBackTo(int backTo) {
		this.backTo = backTo;
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

}
