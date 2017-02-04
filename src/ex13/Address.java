package ex13;

public class Address {
	private String name;
	private String address;
	private String tel;
	private String email;
	public Address(String name,String address,String tel,String email) {
		this.name = name;
		this.address = address;
		this.tel = tel;
		this.email = email;
	}
	public void setName(String name){
		this.name = name;
	}
	public String getName(){
		return name;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getAddress() {
		return address;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getTel() {
		return tel;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getEmail() {
		return email;
	}
	public String toString() {
		return getName() + "," + getAddress() + "," + getTel() + "," + getEmail();
	}
}
