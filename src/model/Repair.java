package model;

public class Repair {
	private int nid;
	private String account;
	private String accountName;
	private String article;
	private String handle;
	private String handleName;
	private String time;
	private String description;

	public Repair() {
	}

	public Repair(String account, String article, String handle, String time,String description) {
		this.account = account;
		this.article = article;
		this.handle = handle;
		this.time = time;
		this.description = description;
	}

	public Repair(int nid, String account, String article, String handle,
			String time,String description) {
		this.nid = nid;
		this.account = account;
		this.article = article;
		this.handle = handle;
		this.time = time;
		this.description = description;
	}
	
	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getHandleName() {
		return handleName;
	}

	public void setHandleName(String handleName) {
		this.handleName = handleName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public String getArticle() {
		return article;
	}

	public void setArticle(String article) {
		this.article = article;
	}

	public String getHandle() {
		return handle;
	}

	public void setHandle(String handle) {
		this.handle = handle;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

}
