package model;

public class Loan {
	private String name;
	private int nid;
	private String account;
	private int articleID;
	private String time;
	private String backtime;
	private String handle;
	private String actualBackTime;
	private int status;

	public Loan() {
	}

	public Loan(String account, int articleID, String time, String backtime,
			String handle) {
		this.account = account;
		this.articleID = articleID;
		this.time = time;
		this.backtime = backtime;
		this.handle = handle;
	}
	
	public Loan(String name,String account, int articleID, String time, String backtime,
			String handle, String actualBackTime, int status) {
		this.name = name;
		this.account = account;
		this.articleID = articleID;
		this.time = time;
		this.backtime = backtime;
		this.handle = handle;
		this.actualBackTime = actualBackTime;
		this.status = status;
	}

	public Loan(String name,int nid, String account, int articleID, String time,
			String backtime, String handle, String actualBackTime, int status) {
		this.name = name;
		this.nid = nid;
		this.account = account;
		this.articleID = articleID;
		this.time = time;
		this.backtime = backtime;
		this.handle = handle;
		this.actualBackTime = actualBackTime;
		this.status = status;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNid() {
		return nid;
	}

	public void setNid(int nid) {
		this.nid = nid;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public int getArticleID() {
		return articleID;
	}

	public void setArticleID(int articleID) {
		this.articleID = articleID;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getBacktime() {
		return backtime;
	}

	public void setBacktime(String backtime) {
		this.backtime = backtime;
	}

	public String getHandle() {
		return handle;
	}

	public void setHandle(String handle) {
		this.handle = handle;
	}

	public String getActualBackTime() {
		return actualBackTime;
	}

	public void setActualBackTime(String actualBackTime) {
		this.actualBackTime = actualBackTime;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}
