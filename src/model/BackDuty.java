package model;

public class BackDuty {
	private String account;
	private String leaveTime;
	private int leaveActiveID;
	private String backTime;
	private int backActiveID;

	public BackDuty() {
	}

	public BackDuty(String account, String leaveTime, int leaveActiveID,
			String backTime, int backActiveID) {
		this.account = account;
		this.leaveTime = leaveTime;
		this.leaveActiveID = leaveActiveID;
		this.backTime = backTime;
		this.backActiveID = backActiveID;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getLeaveTime() {
		return leaveTime;
	}

	public void setLeaveTime(String leaveTime) {
		this.leaveTime = leaveTime;
	}

	public int getLeaveActiveID() {
		return leaveActiveID;
	}

	public void setLeaveActiveID(int leaveActiveID) {
		this.leaveActiveID = leaveActiveID;
	}

	public String getBackTime() {
		return backTime;
	}

	public void setBackTime(String backTime) {
		this.backTime = backTime;
	}

	public int getBackActiveID() {
		return backActiveID;
	}

	public void setBackActiveID(int backActiveID) {
		this.backActiveID = backActiveID;
	}

}
