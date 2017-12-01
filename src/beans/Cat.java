package beans;

public class Cat {
	private String color;
	private String brand;
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public Cat() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Cat(String color, String brand) {
		super();
		this.color = color;
		this.brand = brand;
	}


}
