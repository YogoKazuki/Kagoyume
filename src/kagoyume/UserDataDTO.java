package kagoyume;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
public class UserDataDTO {
private int userID;
private int buyID;
private String name ;
private String password;
private String mail;
private String address;
private int total;
private Timestamp newDate;
private Timestamp buyDate;
private int deleteFlg;
private String itemCode;
private int type;

public UserDataDTO() {

}

public void setUserID(int userID) {this.userID=userID;}
public int getUserID() {return this.userID;}

public void setBuyID(int buyID) {this.buyID=buyID;}
public int getBuyID() {return this.buyID;}

public void setName(String name) {this.name=name;}
public String getName() {return this.name;}

public void setPassword(String password) {this.password=password;}
public String getPassword() {return this.password;}

public void setMail(String mail) {this.mail=mail;}
public String getMail() {return this.mail;}

public void setAddress(String address) {this.address=address;}
public String getAddress() {return this.address;}

public void setTotal(int total) {this.total=total;}
public int getTotal() {return this.total;}

public void setNewDate(Timestamp newDate) {this.newDate=newDate;}
public String getNewDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日HH時mm分ss秒");
		String formatNewDate = sdf.format(this.newDate);
		return formatNewDate;}

public void setBuyDate(Timestamp buyDate) {this.buyDate=buyDate;}
public String getBuyDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日HH時mm分ss秒");
		String formatBuyDate = sdf.format(this.buyDate);
		return formatBuyDate;
}

public void setDeleteFlg(int deleteFlg) {this.deleteFlg=deleteFlg;}
public int getDeleteFlg() {return this.deleteFlg;}

public void setItemCode(String itemCode) {this.itemCode=itemCode;}
public String getItemCode() {return this.itemCode;}

public void setType(int type) {this.type=type;}
public int getType() {return this.type;}

public UserDataDTO itemDataMapping(String itemCode,int userID,int type) {
	UserDataDTO uddto = new UserDataDTO();
	uddto.setUserID(userID);
	uddto.setItemCode(itemCode);
	uddto.setType(type);
	return uddto;
}
}
