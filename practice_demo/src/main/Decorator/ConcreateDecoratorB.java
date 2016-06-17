package main.Decorator;

public class ConcreateDecoratorB extends Decorator {

	public ConcreateDecoratorB(Component component) {
		super(component);
		// TODO Auto-generated constructor stub
	}
	@Override
	public void functionA() {
		super.functionA();
		this.functionB();
	}
	private void functionB() {
		// TODO Auto-generated method stub
		System.out.println("Function B");
	}
	
}
