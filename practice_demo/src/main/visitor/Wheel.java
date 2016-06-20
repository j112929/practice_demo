package main.visitor;

public class Wheel {
	private String name;  
    public Wheel(String name) {  
        this.name = name;  
    }  
    String getName() {  
        return this.name;  
    }  
    public  void accept(IVisitor visitor) {  
        visitor.visit(this);  
    }  
}
