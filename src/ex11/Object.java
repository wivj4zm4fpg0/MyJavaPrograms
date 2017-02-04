package ex11;

class Object {
	public static void main(String[] args) {
		One example1 = new One();
		Two example2 = new Two();
		Three example3 = new Three();
		Four example4 = new Four();
		System.out.println(example1.test());    // 1.
		System.out.println(example1.result1()); // 2.
		System.out.println(example2.test());    // 3.
		System.out.println(example2.result1()); // 4.
		System.out.println(example3.test());    // 5.
		System.out.println(example4.result1()); // 6.
		System.out.println(example3.result2()); // 7.
		System.out.println(example4.result2()); // 8.
		System.out.println(example3.result3()); // 9.
		System.out.println(example4.result3()); // 10.
	}
}
