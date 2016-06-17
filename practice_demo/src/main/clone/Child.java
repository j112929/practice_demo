package main.clone;

import java.io.Serializable;

public class Child implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8714372880429314463L;
	public String name = "";

	public Child(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "child [name="+name+"]";
	}
	@Override
	protected Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
	}
	public static void main(String[] args) {
		DeepCopyExample example = new DeepCopyExample();
		example.child = new Child("Example");
		      
		DeepCopyExample copy = example.copy();
		if (copy != null) {
		  copy.child.name = "Copied";
		  System.out.println("example.child=" + example.child + ";copy.child=" + copy.child);
		}
	}
	
}
