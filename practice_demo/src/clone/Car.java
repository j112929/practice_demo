package clone;

public class Car {
	Wheel wheel;
	  String manufacturer;
	  
	  public Car(Wheel wheel, String manufacturer) {
	      this.wheel = wheel;
	      this.manufacturer = manufacturer;
	  }
	  
	  //copy constructor
//	  public Car(Car car) {
//	      this(car.wheel, car.manufacturer);
//	  }
	  
	  public static class Wheel {
	      String brand;
	  }
	  public Car(Car car) {
		  Wheel wheel = new Wheel();
		  wheel.brand = car.wheel.brand;
		      
		  this.wheel = wheel;
		  this.manufacturer = car.manufacturer;
	}
	public static Car newInstance(Car car) {
		  return new Car(car);
		}
}
