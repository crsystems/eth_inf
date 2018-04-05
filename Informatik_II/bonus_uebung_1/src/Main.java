/**
 * Main class of the Java program. 
 * 
 */

public class Main {


	public static void main(String[] args) {
        
        
	/**
         * Lesen Sie "./Root/files/echo.csv" und "./Root/files/codeexpert.csv" ein
         * und geben Sie die entsprechenden Warnung auf System.out aus
         */


	  
	   //String arrays for the echo and codeexpert csv files
	   String[][] echo = new CSVReader().readCSV("../files/echo.csv");
	   String[][] codeexpert = new CSVReader().readCSV("../files/codeexpert.csv"); 

	   //Array of Student objects
	   Student[] students = new Student[echo.length + codeexpert.length];

	   //Sorting the echo array from small to big LEGI
	   for(int i = 0; i < echo.length; i++){

		  //generating dash free LEGI string the ugly way...
		  String source_legi = "" + echo[i][0].charAt(0) + echo[i][0].charAt(1) + echo[i][0].charAt(3) + echo[i][0].charAt(4) + echo[i][0].charAt(5) + echo[i][0].charAt(7) + echo[i][0].charAt(8) + echo[i][0].charAt(9);

		  //... and generating an integer from that
		  int src = Integer.parseInt(source_legi);

		  //best found legi is a priori the current one
		  int best = src;
		  int best_index = i;

		  //cycling through the unsorted part of the array to find a student with smaller LEGI
		  for(int k = i+1; k < echo.length; k++){

			//the ugly thing again
			String new_legi = "" + echo[k][0].charAt(0) + echo[k][0].charAt(1) + echo[k][0].charAt(3) + echo[k][0].charAt(4) + echo[k][0].charAt(5) + echo[k][0].charAt(7) + echo[k][0].charAt(8) + echo[k][0].charAt(9);
			
			int n_legi = Integer.parseInt(new_legi);

			if(n_legi < src && n_legi < best){
				best = n_legi;
				best_index = k;
			}
		  }

		  //only swap the entrys if necessary (might save some time... Probrably not)
		  if(best != src){
			  String[] swap = echo[best_index];
			  echo[best_index] = echo[i];
			  echo[i] = swap;
		  }

	   }
	   
	   //Sorting the codeexpert array
	   //Exactly the same loop as above, just echo was replaced by codeexpert
	   for(int i = 0; i < codeexpert.length; i++){

		  String source_legi = "" + codeexpert[i][0].charAt(0) + codeexpert[i][0].charAt(1) + codeexpert[i][0].charAt(3) + codeexpert[i][0].charAt(4) + codeexpert[i][0].charAt(5) + codeexpert[i][0].charAt(7) + codeexpert[i][0].charAt(8) + codeexpert[i][0].charAt(9);

		  int src = Integer.parseInt(source_legi);
		  int best = src;
		  int best_index = i;

		  for(int k = i+1; k < codeexpert.length; k++){
			String new_legi = "" + codeexpert[k][0].charAt(0) + codeexpert[k][0].charAt(1) + codeexpert[k][0].charAt(3) + codeexpert[k][0].charAt(4) + codeexpert[k][0].charAt(5) + codeexpert[k][0].charAt(7) + codeexpert[k][0].charAt(8) + codeexpert[k][0].charAt(9);
			
			int n_legi = Integer.parseInt(new_legi);

			if(n_legi < src && n_legi < best){
				best = n_legi;
				best_index = k;
			}
		  }

		  if(best != src){
			  String[] swap = codeexpert[best_index];
			  codeexpert[best_index] = codeexpert[i];
			  codeexpert[i] = swap;
		  }

	   }


	   //Filling the upper part of the student array with the students from echo...
	   for(int i = 0; i < echo.length; i++){
		   students[i] = new Student(echo[i][2], echo[i][1], echo[i][0], echo[i][3]);
	   }

	   //... and the remaining part with the students from codeexpert
	   for(int i = echo.length; i < (echo.length + codeexpert.length); i++){
		   students[i] = new Student(codeexpert[i - echo.length][2], codeexpert[i - echo.length][1], codeexpert[i - echo.length][0], codeexpert[i - echo.length][3]);
	   }


	   //Searching for errors and setting the corresponding errorcodes in the student objects
	   for(int i = 0; i < students.length; i++){
		  
		  //Splitting the search between the two sections of the student array
		  if(i < echo.length){

			//helper variable to find out if this student exists in the codeexpert array
			boolean found = false;
			for(int k = 0; k < codeexpert.length; k++){
				//matching LEGIs => good sign, but lets check the first- and lastname to
				if(students[i].getLEGI().equals(codeexpert[k][0])){

					if(students[i].getFirstname().equals(codeexpert[k][2]) && students[i].getLastname().equals(codeexpert[k][1])){
						//set helper variable
						found = true;


						//Decide which error is present
						if(students[i].getTutor().equals(codeexpert[k][3])){
							students[i].setError(0);
						}else{
							students[i].setError(1);
						}
						//Don't waste time. We already found one. That has to do the trick
						break;
					}
				}
			}

			//if no equal student was found set the corresponding error
			if(!found){
				students[i].setError(3);
			}

		  //Same algorithm as above
		  }else{
			
			boolean found = false;
			for(int k = 0; k < echo.length; k++){
				if(students[i].getLEGI().equals(echo[k][0])){
					if(students[i].getFirstname().equals(echo[k][2]) && students[i].getLastname().equals(echo[k][1])){

						found = true;

						if(students[i].getTutor().equals(echo[k][3])){
							students[i].setError(0);
						}else{
							students[i].setError(1);
						}
						break;
					}
				}
			}

			if(!found){
				students[i].setError(2);
			}

		  }
	   }
	

	   //Sorting the student array to be able to print the errors with ascending LEGI numbers later
	   //Again it's the same sorting algorithm. But the ugly LEGI generation is in the Student class
	   for(int i = 0; i < students.length; i++){
		  int src = students[i].getLEGINum();
		  int best = src;
		  int best_index = i;

		  for(int k = i+1; k < students.length; k++){
			int n_legi = students[k].getLEGINum(); 

			if(n_legi < src && n_legi < best){
				best = n_legi;
				best_index = k;
			}
		  }

		  if(best != src){
			  Student swap = students[best_index];
			  students[best_index] = students[i];
			  students[i] = swap;
		  }

	   }


	  //Iterating through the student arra to print the earlier found errors
	  for(int i = 0; i < students.length; i++){
		
		  //If an error exists and this is not something, that already was printed
		  if(students[i].getError() != 0 && students[i].getPrinted() == false){

			  //Different tutors
			  if(students[i].getError() == 1){
				String echo_string = students[i].getTutor();
				
				int code_index = i;
				
				//Find tutor of codeexpert
				//We can be sure to search in the codeexpert part, because we have the printed variable
				//If errorcode 1 is correct this if is executed in an echo index space
				for(int k = i+1 ; k < students.length; k++){
					if(students[i].getLEGI().equals(students[k].getLEGI())){
						code_index = k;
						students[k].setPrinted();
						break;
					}
				}

				System.out.println(students[i].getFirstname() + " " + students[i].getLastname() + " is enrolled to " + students[i].getTutor() + " in Echo, but registered with " + students[code_index].getTutor() + " in CodeExpert.");

				students[i].setPrinted();

			  //Not in Echo
			  }else if(students[i].getError() == 2){
				  System.out.println(students[i].getFirstname() + " " + students[i].getLastname() + " is in CodeExpert but not in Echo.");
				  students[i].setPrinted();

		  	  //Not in CodeExpert
			  }else if(students[i].getError() == 3){
				  System.out.println(students[i].getFirstname() + " " + students[i].getLastname() + " is in Echo but not in CodeExpert.");
				  students[i].setPrinted();
			  }
		  }
	  }
    }
}
