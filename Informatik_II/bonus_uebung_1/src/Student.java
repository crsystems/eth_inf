public class Student{

	String firstname, lastname, legi, tutor;

	int legi_1, legi_2, legi_3;

	public Student(){}

	public Student(String firstname, String lastname, String legi, String tutor){
		this.set(firstname, lastname, legi, tutor);
	}

	public void set(String firstname, String lastname, String legi, String tutor){
		this.firstname = firstname;
		this.lastname = lastname;
		this.legi = legi;
		this.tutor = tutor;
	}

	public String toString(){
		if(tutor == "none"){
			return this.firstname + " " + this.lastname + " with LEGI: " + this.legi + " is enrolled in the course of no one.";
		}else{
			return this.firstname + " " + this.lastname + " with LEGI: " + this.legi + " is enrolled in the course of " + this.tutor;
		}
	}	
}
