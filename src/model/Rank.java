package model;

public class Rank {
	private String name;
	private int rank;

	public Rank() {
	}

	public Rank(String name, int rank) {
		this.name = name;
		this.rank = rank;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

}
