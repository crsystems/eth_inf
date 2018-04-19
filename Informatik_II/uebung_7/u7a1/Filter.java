package u7a1;

import java.util.ArrayList;
import u7a1.*;
public class Filter implements IFilter{

        public final int maxNumberofPoints = 80;
        public final double criteria = 50;

        public Filter() { }

        public ArrayList filterRaw(ArrayList groups){
                ArrayList<Student> passed = new ArrayList<Student>();

                int pass = (int) (this.maxNumberofPoints * (this.criteria / 100));

                for(int i = 0; i < groups.size(); i++){
                        ArrayList<Student> tmp = (ArrayList<Student>) groups.get(i);

                        for(int k = 0; k < tmp.size(); k++){
                                if(tmp.get(k).getPoints() >= pass){
                                        passed.add(tmp.get(k));
                                }
                        }
                }
                return passed;
        }

        public ArrayList<Student> filterGeneric(ArrayList<ArrayList<Student>> groups){
                ArrayList<Student> passed = new ArrayList<Student>();

                int pass = (int) (this.maxNumberofPoints * (this.criteria / 100));

                for(int i = 0; i < groups.size(); i++){
                        ArrayList<Student> tmp = groups.get(i);

                        for(int k = 0; k < tmp.size(); k++){
                                if(tmp.get(k).getPoints() >= pass){
                                        passed.add(tmp.get(k));
                                }
                        }
                }
                return passed;
        }
}

