package ex12;

public class Box implements Decoratable {
	private String str;

	public Box(String str) {
		this.str = str;
	}
	public void decorate() {
		printLine();
		System.out.println("|" + str + "|");
		printLine();
	}
	public static Boolean a(String value) {
	    if ( value == null || value.length() == 0 )
	        return true;
	    int len = value.length();
	    byte[] bytes = value.getBytes();
	    if ( len != bytes.length )
	        return false;
	    return true;
	}
	private void printLine() {
		System.out.print("+");
		for(int i = 0;i < str.length();i++) {
			System.out.print("-");
			if(a(str) != true){
				System.out.print("-");
			}
		}
		System.out.println("+");
	}
}
