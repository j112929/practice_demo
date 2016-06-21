package main.string;

public class TestClass {
	private static void testMethod() {
		System.out.println("testMethod");
	}

	public static void main(String[] args) {
		((TestClass) null).testMethod();// 递归
	}

	abstract class InnerClass {
		public float func() {
			return 0;
		}
	}
}
