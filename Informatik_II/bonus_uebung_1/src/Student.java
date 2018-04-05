public class Student{

	String firstname, lastname, legi, tutor;

	/**
	 *Error Variable
	 *
	 *Stores the kind of error, if any:
	 *
	 * 0 = No error
	 * 1 = Different assistants in CodeExpert and Echo
	 * 2 = Not in Echo
	 * 3 = Not in CodeExpert
	 * -1 = Default unclassified
	 *
	 */
	int error;

	int legi_numeric;

	boolean printed = false;

	/**
	 * Standard constructor without arguments
	 */
	public Student(){
		this.error = -1;
	}
	
	/**
	 * Constructor that sets the atrributes of the object corresponding to the arguments
	 */
	public Student(String firstname, String lastname, String legi, String tutor){
		this.set(firstname, lastname, legi, tutor);
	}

	/**
	 * Setter method to setup the attributes of the object
	 */
	public void set(String firstname, String lastname, String legi, String tutor){
		this.firstname = firstname;
		this.lastname = lastname;
		this.legi = legi;
		this.tutor = tutor;
		this.error = -1;

		String source = "" + legi.charAt(0) + legi.charAt(1) + legi.charAt(3) + legi.charAt(4) + legi.charAt(5) + legi.charAt(7) + legi.charAt(8) + legi.charAt(9);

                this.legi_numeric = Integer.parseInt(source);
	}


	/**
	 * Method that returns a meaningful sentence including the attributes of the given object
	 */
	public String toString(){
		return this.firstname + " " + this.lastname + " with LEGI: " + this.legi + " is enrolled in the course of " + this.tutor;
	}

	/**
	 * Method to set the errorcode of the object
	 */
	public void setError(int err){
		this.error = err;
	}

	/**
	 * Returns the errorcode of the object
	 */
	public int getError(){
		return this.error;
	}	
	
	/**
	 * Returns the LEGI as string
	 */
	public String getLEGI(){
		return this.legi;
	}
	
	/**
	 * Returns the LEGI in integer representation
	 */
	public int getLEGINum(){
		return legi_numeric;
	}

	/**
	 * Returns the Tutor
	 */
	public String getTutor(){
		return this.tutor;
	}

	public void setPrinted(){
		printed = true;
	}

	public boolean getPrinted(){
		return printed;
	}

	/**
	 * Returns the firstname
	 */
	public String getFirstname(){
		return firstname;
	}

	/**
	 * Returns the lastname
	 */
	public String getLastname(){
		return lastname;
	}
}
