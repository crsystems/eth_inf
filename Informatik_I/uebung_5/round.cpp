#include <iostream>

using namespace std;

int round(double x) {
	int rounded = (int) x;

	if(rounded < x){
		if((x - (double) rounded) >= (double) 0.5){
			rounded++;
		}
	} else if(rounded > x){
		if(((double) rounded - x) >= (double) 0.5){
			rounded--;
		}
	}
	return rounded;
}

int main(void){

	double input = 0;
	cin >> input;
	cout << round(input) << endl;
	return 0;
}
