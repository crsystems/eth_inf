#include <iostream>

using namespace std;

int main(void){

	unsigned int i, r;

	cout << "Bitte geben Sie ihre Dezimalzahl ein: ";
	cin >> i;
	do {
		r = i % 2;
		i = i / 2;
		cout << r << endl;
	} while(i != 0);

	return 0;
}
