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
        CSVReader reader = new CSVReader();
        String[][] echo = reader.readCSV("./Root/files/echo.csv");
        String[][] code = reader.readCSV("./Root/files/codeexpert.csv");
         
        Student[] students  = new Student[echo.length + code.length];
         
        //sorts String[][] echo
        for(int i=0;i<echo.length; i++){
            StringBuffer str = new StringBuffer();
            str.append(echo[i][0].charAt(0)).append(echo[i][0].charAt(1)).append(echo[i][0].charAt(3)).append(echo[i][0].charAt(4)).append(echo[i][0].charAt(5)).append(echo[i][0].charAt(7)).append(echo[i][0].charAt(8)).append(echo[i][0].charAt(9));
            int min = Integer.parseInt(str.toString());
            int minst = i;
            
            for(int t=i+1; t<echo.length; t++){
                StringBuffer buf = new StringBuffer();
                buf.append(echo[t][0].charAt(0)).append(echo[t][0].charAt(1)).append(echo[t][0].charAt(3)).append(echo[t][0].charAt(4)).append(echo[t][0].charAt(5)).append(echo[t][0].charAt(7)).append(echo[t][0].charAt(8)).append(echo[t][0].charAt(9));
                int cur = Integer.parseInt(buf.toString());
                
                if(min>cur){
                    min=cur;
                    minst=t;
                }
            }
            String[] swap=echo[i];
            echo[i]=echo[minst];
            echo[minst]=swap;
             
        }
        
         
        //just the same sorting algorithm as directly above
        //sorts String[][] code
        for(int i=0;i<code.length; i++){
            StringBuffer str = new StringBuffer();
            str.append(code[i][0].charAt(0)).append(code[i][0].charAt(1)).append(code[i][0].charAt(3)).append(code[i][0].charAt(4)).append(code[i][0].charAt(5)).append(code[i][0].charAt(7)).append(code[i][0].charAt(8)).append(code[i][0].charAt(9));
            int min = Integer.parseInt(str.toString());
            int minst = i;
            
            for(int t=i+1; t<code.length; t++){
                StringBuffer buf = new StringBuffer();
                buf.append(code[t][0].charAt(0)).append(code[t][0].charAt(1)).append(code[t][0].charAt(3)).append(code[t][0].charAt(4)).append(code[t][0].charAt(5)).append(code[t][0].charAt(7)).append(code[t][0].charAt(8)).append(code[t][0].charAt(9));
                int cur = Integer.parseInt(buf.toString());
                
                if(min>cur){
                    min=cur;
                    minst=t;
                }
            }
            String[] swap=code[i];
            code[i]=code[minst];
            code[minst]=swap;
             
        }
         
         
        //students array
        for (int i=0; i<echo.length; i++){
            students[i] = new Student(echo[i][2],echo[i][1],echo[i][0],echo[i][3]);
            //System.out.println(echo[i][0]);
        }
         
        for (int i=0; i<code.length; i++){
            students[echo.length+i] = new Student(code[i][2],code[i][1],code[i][0],code[i][3]);
            //System.out.println(code[i][0]);
        }
         
        //finds the "problem students" in the code array  and puts them together with the appropriate warning in the array problems
        String[][] problems = new String[students.length][2];
        int probcur=0; //number of current elements in problems
        
        for(int i=0; i<code.length; i++){
            
            boolean inecho = false;
            
            for(int t=0; t<echo.length; t++){
                if(code[i][0].equals(echo[t][0]) && code[i][1].equals(echo[t][1]) && code[i][2].equals(echo[t][2])){
                    inecho = true;
                    
                    if(!code[i][3].equals(echo[t][3])){
                        String str = code[i][2] + ' ' + code[i][1] + " is enrolled to " + echo[t][3] + " in Echo, but registered with " + code[i][3] + " in CodeExpert.";
                        problems[probcur][1] = str;
                        problems[probcur][0] = code[i][0];
                        probcur++;
                    }
                    break;
                }
            }
            if(inecho==false){
                String str = code[i][2] + ' ' + code[i][1] + " is in CodeExpert but not in Echo.";
                problems[probcur][1] = str;
                problems[probcur][0] = code[i][0];
                probcur++;
            }
        }
         
         
         //finds the "problem students" in the echo array and puts them together with the appropriate warning in the array problems
        for(int i=0; i<echo.length; i++){
            
            boolean incode = false;
            for(int t=0; t<code.length; t++){
                
                 if(echo[i][0].equals(code[t][0]) && echo[i][1].equals(code[t][1]) && echo[i][2].equals(code[t][2])){
                    incode = true;
                 }
            }
            if(incode==false){
                String str = echo[i][2] + ' ' + echo[i][1] + " is in Echo but not in CodeExpert.";
                problems[probcur][1] = str;
                problems[probcur][0] = echo[i][0];
                probcur++;
            }
        }
         
        //just the same sorting algorithm as above
        //sorts the array problems
        for(int i=0;i<probcur; i++){
            StringBuffer str = new StringBuffer();
            str.append(problems[i][0].charAt(0)).append(problems[i][0].charAt(1)).append(problems[i][0].charAt(3)).append(problems[i][0].charAt(4)).append(problems[i][0].charAt(5)).append(problems[i][0].charAt(7)).append(problems[i][0].charAt(8)).append(problems[i][0].charAt(9));
            int min = Integer.parseInt(str.toString());
            int minst = i;
            
            for(int t=i+1; t<probcur; t++){
                StringBuffer buf = new StringBuffer();
                buf.append(problems[t][0].charAt(0)).append(problems[t][0].charAt(1)).append(problems[t][0].charAt(3)).append(problems[t][0].charAt(4)).append(problems[t][0].charAt(5)).append(problems[t][0].charAt(7)).append(problems[t][0].charAt(8)).append(problems[t][0].charAt(9));
                int cur = Integer.parseInt(buf.toString());
                
                if(min>cur){
                    min=cur;
                    minst=t;
                }
            }
            String[] swap=problems[i];
            problems[i]=problems[minst];
            problems[minst]=swap;
             
        }
        
        //prints the warnings for the problem students in the appropriate order
        for(int i=0; i<probcur; i++){
            System.out.println(problems[i][1]);
        }
         
    }
}
