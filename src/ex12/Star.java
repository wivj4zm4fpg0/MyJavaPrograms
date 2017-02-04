package ex12;

public class Star implements Decoratable {
	private String str;

	public Star(String str){
		this.str = str;
	}
	public void decorate(){
		System.out.println("*" + str + "*");
	}
}
