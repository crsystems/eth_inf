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

	   String[][] echo = new CSVReader().readCSV("../files/echo.csv");
	   String[][] codeexpert = new CSVReader().readCSV("../files/codeexpert.csv"); 

	   Student[] students = new Student[echo.length + codeexpert.length];

	   

	   for(int i = 0; i < echo.length; i++){

		  String source_legi = "" + echo[i][0].charAt(0) + echo[i][0].charAt(1) + echo[i][0].charAt(3) + echo[i][0].charAt(4) + echo[i][0].charAt(5) + echo[i][0].charAt(7) + echo[i][0].charAt(8) + echo[i][0].charAt(9);

		  int src = Integer.parseInt(source_legi);
		  int best = src;
		  int best_index = i;

		  for(int k = i+1; k < echo.length; k++){
			String new_legi = "" + echo[k][0].charAt(0) + echo[k][0].charAt(1) + echo[k][0].charAt(3) + echo[k][0].charAt(4) + echo[k][0].charAt(5) + echo[k][0].charAt(7) + echo[k][0].charAt(8) + echo[k][0].charAt(9);
			
			int n_legi = Integer.parseInt(new_legi);

			if(n_legi < src && n_legi < best){
				best = n_legi;
				best_index = k;
			}
		  }

		  if(best != src){
			  String[] swap = echo[best_index];
			  echo[best_index] = echo[i];
			  echo[i] = swap;
		  }

	   }
	   
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

	   for(int i = 0; i < echo.length; i++){
		   students[i] = new Student(echo[i][2], echo[i][1], echo[i][0], echo[i][3]);
	   }

	   for(int i = echo.length; i < (echo.length + codeexpert.length); i++){
		   students[i] = new Student(codeexpert[i - echo.length][2], codeexpert[i - echo.length][1], codeexpert[i - echo.length][0], codeexpert[i - echo.length][3]);
	   }


	   int err_size = codeexpert.length;

	   if(echo.length > codeexpert.length){
		   err_size = echo.length;
	   }

	   Student errors = new Student[err_size];

	   for(int i = 0; i < echo.length; i++){
		   for(int k = 0; k < codeexpert.length; k++){
			   if(echo[i][0].equals(codeexpert[k][0])){
				if(echo[i][3].equals(codeexpert[k][3]) == False){
					errors[err_index].set
				
			   }









    }
}
