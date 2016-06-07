package main.java.demo;


public class dsaadadsa {
	public static void main(String[]arges)  
    {  
        Onlie on=Onlie.retClass();  
        //Onlie.retClass():在Mvt类中的静态方法里,直接用retClass()的类调用retClass().整条语句的作用是接受  
        //Onlie类的实例对象.但该条语句一运行,就可以实现:在Mvt中调用Onlie类中的private构造方法.  
          
        String s=on.huifu();  
        System.out.println("s="+s);  
          
        Onlie on2=Onlie.retClass();  
          
        if(on.hashCode()==on2.hashCode())//判断on与on2是否相等  
        {  
            System.out.println("on和on2的内存位置没变,接受的值也没变化");  
        }  
        else  
        {  
            System.out.println("on和on2的内存位置有变化!");  
      
        }  
          
    }  
}
