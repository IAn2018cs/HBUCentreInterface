package model;

public class InterestGroup {
	private int nid;
	private String interestGroup;
	private String description;

	public InterestGroup() {
	}

	public InterestGroup(int nid, String interestGroup, String description) {
		this.nid = nid;
		this.interestGroup = interestGroup;
		this.description = description;
	}

	public int getNid() {
		return nid;
	}

	public void setNid(int nid) {
		this.nid = nid;
	}

	public String getInterestGroup() {
		return interestGroup;
	}

	public void setInterestGroup(String interestGroup) {
		this.interestGroup = interestGroup;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
