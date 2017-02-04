package ex10;

public class Student {
	private String studentNumber;
	private String name;
	private String address;
	private String tel;

	public static void main(String[] args) {
		Student a = new Student("15FI057","Satoru","dendai.ac.jp","090");
		System.out.println("a " + a);
		a.setName("Saika");
		a.setAddress("dendai.com");
		a.setTel("222");
		System.out.println("a " + a);
	}
	public Student(String studentNumber, String name, String address, String tel){
		this.studentNumber = studentNumber;
		this.address = address;
		this.name = name;
		this.tel = tel;
	}
	public String getStudentNumber() {
		return studentNumber;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String toString() {
		return "Student(" + getStudentNumber() + "," + getName() + "," + getAddress() + "," + getTel() + ")";
	}
}
