package model;

public class Article {
	private int nid;
	private String name;
	private int type;
	private int quanutity;
	private double price;
	private int status;
	private String description;
	private String location;
	private String imageUrl;

	public Article() {
	}

	public Article(int nid, String name, int type, int quanutity, double price,
			int status, String description,String location, String imageUrl) {
		this.nid = nid;
		this.name = name;
		this.type = type;
		this.quanutity = quanutity;
		this.price = price;
		this.status = status;
		this.description = description;
		this.location = location;
		this.imageUrl = imageUrl;
	}
	
	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public int getNid() {
		return nid;
	}

	public void setNid(int nid) {
		this.nid = nid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getQuanutity() {
		return quanutity;
	}

	public void setQuanutity(int quanutity) {
		this.quanutity = quanutity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
