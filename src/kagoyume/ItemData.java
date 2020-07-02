package kagoyume;

public class ItemData {
private String hitNum;
private String name;
private String price;
private String imageURL;
private String itemCode;
private String description;
private String score;
private String type;
	public ItemData(){

	}
	public void setHitNum(String hitNum) {
		this.hitNum=hitNum;
	}
	public String getHitNum() {
		return hitNum;
	}
	public void setName(String name) {
		this.name=name;
	}
	public String getName() {
		return name;
	}
	public void setPrice(String price) {
		this.price=price;
	}
	public String getPrice() {
		return price;
	}
	public void setImageURL(String imageURL) {
		this.imageURL=imageURL;
	}
	public String getImageURL() {
		return imageURL;
	}
	public void setItemCode(String itemCode) {
		this.itemCode=itemCode;
	}
	public String getItemCode() {
		return itemCode;
	}
	public void setDescription(String description) {
		this.description=description;
	}
	public String getDescription() {
		return description;
	}
	public void setScore(String score) {
		this.score=score;
	}
	public String getScore() {
		return score;
	}
	public void setType(int type) {
		switch(type) {
		case 1:
			this.type="徒歩";
			break;
		case 2:
			this.type="自転車";
			break;
		case 3:
			this.type="車";
			break;
		}
	}
	public String getType() {
		return this.type;
	}

}
