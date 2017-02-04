package ex10;

public class TA extends Student {
	private String subject;
	private String extension;

	public static void main(String[] args) {
		TA a = new TA("15FI057","Satoru","dendai.ac.jp","090","math","111");
		System.out.println("a " + a);
		a.setSubject("history");
		a.setExtension("777");
		System.out.println("a " + a);
	}
	public TA(String studentNumber, String name, String address, String tel, String subject, String extension) {
		super(studentNumber, name, address, tel);
		this.subject = subject;
		this.extension = extension;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getExtension() {
		return extension;
	}
	public void setExtension(String extension) {
		this.extension = extension;
	}
	public String toString() {
		return "TA(" + getStudentNumber() + "," + getName() + "," + getAddress() + "," + getTel() + "," + getSubject() + "," + getExtension() + ")";
	}
}
