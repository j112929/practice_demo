package main.Decorator;

public class Client {
	public static void main(String[] args) {
		Component component = new ConcreateDecoratorC(new ConcreateDecoratorB(new ConcreateComponent()));
//		Component component = new ConcreateDecoratorB(new ConcreateDecoratorC(new ConcreateComponent()));
		component.functionA();
	}
}
