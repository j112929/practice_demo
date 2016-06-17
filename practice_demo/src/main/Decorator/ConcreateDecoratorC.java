package main.Decorator;

public class ConcreateDecoratorC extends Decorator {

	public ConcreateDecoratorC(Component component) {
		super(component);
		// TODO Auto-generated constructor stub
	}
	@Override
	public void functionA() {
		// TODO Auto-generated method stub
		super.functionA();
		this.functionC();
	}
	private void functionC() {
		// TODO Auto-generated method stub
		System.out.println("Function C");
	}
}
