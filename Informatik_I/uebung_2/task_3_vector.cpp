#include <iostream>
#include <vector>
using namespace std;

int main(void){

	vector<int> r (12, 0);

	cout << "Bitte geben Sie die Widerstandswerte für die Widerstände R1 bis R4 ein: \n";
	//cout << "R1: ";
	cin >> r[0];
	//cout << "R1 --> " << r[0] << endl;
	//cout << "R2: ";
	cin >> r[1];
	//cout << "R2 --> " << r[1] << endl;
	//cout << "R3: ";
	cin >> r[2];
	//cout << "R3 --> " << r[2] << endl;
	//cout << "R4: ";
	cin >> r[3];
	//cout << "R4 --> " << r[3] << endl;

    	r[4] = r[0] + r[1];
	r[5] = r[2] + r[3];
	
	//cout << "R12 --> " << r[4] << endl;
	//cout << "R34 --> " << r[5] << endl;

    	//rounding to zero
	r[6] = (r[4]*r[5])/(r[4]+r[5]);
	
	//calculating the remainder
	r[7] = (r[4]*r[5])%(r[4]+r[5]);
	
	//calculating the arithmetic rounded half of the divisor:
	//half of the divisor rounded to zero
	r[8] = (r[4]+r[5])/2;
	//remainder
	r[9] = (r[4]+r[5])%2;
	//remainder of modulo 2 can only be 1 or 0
	r[10] = r[8]+r[9];
	//remainder of resistance division divided by the half of the divisor -> check if bigger than 0.5
	r[11] = r[7]/r[10];
	
	//output the result
	cout << "Gesamtwiderstand: \n";
	cout << "Rges --> " << r[6] + r[11] << endl; 
	return 0;
}
