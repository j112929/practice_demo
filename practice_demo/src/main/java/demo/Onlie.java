package main.java.demo;

public class Onlie {
	static Onlie o = new Onlie();// 创建实例对象并把它赋给静态类型的对象变量o.

	private Onlie() {
		System.out.println("这是一个私有构造方法!");// 私有构造方法
	}

	public static Onlie retClass() {
		return o;
	}
	/*
	 * Onlie是该方法的返回类型.创建这个方法的目的:让Mvt类中用Onlie类名来访问,以此让Mvt类得到Onlie类的实例对象,
	 * 在Mvt类中虽然不能创建Onlie类的实例对象,原因是:Onlie对象一运行,Onlie类的构造方法也会随之运行,而a类的构造方法是私有的(
	 * private),不能被Mvt类访问,所以我们把Onlie类定义成静态的,静态之间调用可以直接用类名.总之,
	 * 只要在一个类里声明有private构造方法,在别的类中无法对这个类进行实例.这也叫单态模式.retClass方法必须为static型,
	 * 因为它要被main方法访问,而main方法考虑到Onlie类中有私有构造方法,不能对Onlie类进行实例化.
	 * 所以main方法只能通过retClass方法的类名调用retClass方法.但,这前提必须是gettffa是静态型的.
	 */

	String huifu() {
		return "收到了!";
	}
}
	  
	/* 
	    打印结果: 
	     
	这是一个私有构造方法! 
	s=收到了! 
	on和on2的内存位置没变,接受的值也没变化 
	 
	*/  
