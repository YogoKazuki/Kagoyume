package kagoyume;

public class UserData {
private int userID;
private String name;
private String password;
private String mail;
private String zipcode;
private String address1;
private String address2;
private int total;
private String newDate;

public UserData(){
setName("");
setPassword("");
setMail("");
setZipcode("");
setAddress1("");
setAddress2("");
}
public void setUserID(int userID) {this.userID=userID;}
public int getUserID() {return this.userID;}
public void setName(String name) {this.name=name;}
public	String getName(){return this.name;}

public void setPassword(String password) {this.password=password;}
public	String getPassword(){return this.password;}

public void setMail(String mail) {this.mail=mail;}
public	String getMail(){return this.mail;}

public void setZipcode(String zipcode) {this.zipcode=zipcode;}
public	String getZipcode(){return this.zipcode;}

public void setAddress1(String address1) {this.address1=address1;}
public	String getAddress1(){return this.address1;}

public void setAddress2(String address2) {this.address2=address2;}
public	String getAddress2(){return this.address2;}

public void setTotal(int total) {this.total=total;}
public int getTotal() {return this.total;}

public void setNewDate(String newDate) {this.newDate=newDate;}
public String getNewDate() {
		return this.newDate;}

//UserDataをUserDataDTOへマッピング
public UserDataDTO userDataMapping(UserData ud) {
	UserDataDTO uddto = new UserDataDTO();
	uddto.setUserID(ud.getUserID());
	uddto.setName(ud.getName());
	uddto.setPassword(ud.getPassword());
	uddto.setMail(ud.getMail());
	String address = ud.getZipcode()+","+ud.getAddress1()+","+ud.getAddress2();
	uddto.setAddress(address);
	return uddto;
}
//UserDataDTOをUserDataへマッピング
public UserData userDataMapping(UserDataDTO uddto) {
	UserData ud = new UserData();
	ud.setUserID(uddto.getUserID());
	ud.setName(uddto.getName());
	ud.setPassword(uddto.getPassword());
	ud.setMail(uddto.getMail());
	String[] address = uddto.getAddress().split(",",3);
	ud.setZipcode(address[0]);
	ud.setAddress1(address[1]);
	ud.setAddress2(address[2]);
	ud.setTotal(uddto.getTotal());
	ud.setNewDate(uddto.getNewDate());
	return ud;
}
}
