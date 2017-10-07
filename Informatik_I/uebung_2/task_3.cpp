#include <iostream>
using namespace std;

int main(void){

	int *r;
	r = (int *) calloc(9, sizeof(int));

	cout << "Bitte geben Sie die Widerstandswerte für die Widerstände R1 bis R4 ein: \n";
	cout << "R1: ";
	cin >> r[0];
	cout << "R1 --> " << r[0] << endl;
	cout << "R2: ";
	cin >> r[1];
	cout << "R2 --> " << r[1] << endl;
	cout << "R3: ";
	cin >> r[2];
	cout << "R3 --> " << r[2] << endl;
	cout << "R4: ";
	cin >> r[3];
	cout << "R4 --> " << r[3] << endl;

        r[4] = r[0] + r[1];
	r[5] = r[2] + r[3];
	
	cout << "R12 --> " << r[4] << endl;
	cout << "R34 --> " << r[5] << endl;

	r[6] = (r[4]*r[5])/(r[4]+r[5]);
	r[7] = (r[4]*r[5])%(r[4]+r[5]);
	r[8] = r[7]/((r[4]+r[5])/2);
	
	cout << "Rges --> " << r[6] + r[8] << endl; 
	free(r);
	return 0;
}
