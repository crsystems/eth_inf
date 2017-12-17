#include <iostream>

using namespace std;

class Averager {

	private:
		double average = 0;
		int size = 0;
		double *vals;

		void resize(void){
			double *new_values = new double [size+1];
			if(size != 0){
			    for(int i = 0; i < size; i++){
				    new_values[i] = vals[i];
			    }
			    delete vals;
			}
			vals = new_values;
			size++;
			return;
		}

		void update_average(void){
			double sum = 0;
			for(int i = 0; i < size; i++){
				sum += vals[i];
			}

			average = sum / size;
			return;
		}




	public:
		Averager(){}
		~Averager(){
		    if(size > 0){
			    delete vals;
		    }
		}

		void add_value(double value){
			resize();
			vals[size-1] = value;					
			update_average();
			return;
		}

		double get_average(void){
			return average;
		}

		void reset(void){
			average = 0;
			delete vals;
			vals = 0;
			size = 0;
			return;
		}
};

int main() {
    Averager averager;
    std::string operation;

    // query for new operations until end of stream is reached
    while(std::cin >> operation) {
        if (operation == "add") {
            double value;
            std::cin >> value;
            averager.add_value(value);

        } else if (operation == "get") {
            std::cout << averager.get_average() << " ";

        } else if (operation == "reset") {
            averager.reset();
        }
    }
}
