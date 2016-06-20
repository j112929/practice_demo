package main.visitor;

public class Engine {
	public  void accept(IVisitor visitor) {  
        visitor.visit(this);  
	}  
}
