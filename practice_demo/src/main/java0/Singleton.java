package main.java0;

public class Singleton {
	private static class Holder{
		private static Singleton singleton = new Singleton();
	}
	private Singleton() {
		// TODO Auto-generated constructor stub
	}
	public static Singleton getSingleton(){
		return Holder.singleton;
	}
}
