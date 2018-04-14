package u6a3;

public class Triangle extends GeometricObject {
	private int base;
	private int height;
	
	public String toString()
	{
		return String.format("Triangle(%d,%d)", base, height);
	}
	
	public int area()
	{
		return (int) 0.5*(this.base*this.height);
	}
	
	public Triangle(int base, int height)
	{
		this.base = base;
		this.height = height;
	}
}
