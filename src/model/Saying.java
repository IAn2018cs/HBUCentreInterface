package model;

public class Saying {
	private int id;
	private String content;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Saying(int id, String content) {
		super();
		this.id = id;
		this.content = content;
	}

	public Saying(String content) {
		super();
		this.content = content;
	}
}
