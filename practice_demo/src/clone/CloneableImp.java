package clone;

public class CloneableImp implements Cloneable{
	public int count;
	public Child child;
	@Override
	public Object clone() throws CloneNotSupportedException {
		CloneableImp obj = (CloneableImp)super.clone();
	      obj.child = (Child) child.clone();
	      return obj;
	}
	public static void main(String[] args) {
		CloneableImp imp1 = new CloneableImp();
		imp1.child = new Child("Andy");
		try {
		  Object obj = imp1.clone();
		  CloneableImp imp2 = (CloneableImp)obj;
		  System.out.println("main imp2.child.name=" + imp2.child.name);
		} catch (CloneNotSupportedException e) {
		  e.printStackTrace();
		}
	}
}
