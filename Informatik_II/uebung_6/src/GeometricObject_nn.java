package u6a3;

/**
 * abstract class of geometric objects
 */
public abstract class GeometricObject implements Comparable {
	public abstract int area();
	
	public boolean smallerThan(Comparable rhs){
		if(this.area()<((GeometricObject) rhs).area())
		       	return true;
		return false;
	}
}
