public class Student{
    
    String vorname;
    String nachname;
    String legi;
    String tutor;
    
    public Student(String v, String n, String l, String t){
        vorname=v;
        nachname=n;
        legi=l;
        tutor=t;
    }
    
    public String toString(){
        StringBuffer str = new StringBuffer();
        str.append(vorname);
        str.append(' ');
        str.append(nachname);
        str.append("\n Legi-Nummer: ");
        str.append(legi);
        str.append("\n Tutor: ");
        str.append(tutor);
        
        return str.toString();
    }
    
}
