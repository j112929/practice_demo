package main.java.demo;

public class demo {
public static void main(String[] args) {
	Thread t1 = new Thread();
	   t1.setName( "t1");
	   Thread t2 = new Thread();
	   t2.setName( "t2");
	   
	   t1.start();
	   t2.start();
	
}
static void NEW() {
    Thread t = new Thread ();
   System. out.println(t.getState());
}

private static void RUNNABLE() {
    Thread t = new Thread(){
        
         public void run(){
             for(int i=0; i<Integer.MAX_VALUE; i++){
                 System. out.println(i);
            }
        }
        
    };
    
    t.start();
}

private static void BLOCKED() {
    
    final Object lock = new Object();
   
   Runnable run = new Runnable() {
       
        @Override
        public void run() {
            for(int i=0; i<Integer.MAX_VALUE; i++){
                
                 synchronized (lock) {
                    System. out.println(i);
                }
                
           }
       }
   };
}
   
}
