#define MAX 4294967295
#include <iostream>


using namespace std;

int main(void){

	unsigned int i = 1, j = 1, m = 0, count = 1;

	bool overflow = false;

	cout << "Bitte geben Sie die obere Grenze für die Fibonacci Reihe ein: ";
	cin >> m;

	cout << "Fibonacci Reihe bis " << m << endl;
	cout << "---- 0\n";
	while(i <= m && j <= m && overflow == false){
		if(i == 1 && j == 1){
			cout << "---- 1\n";
			count++;
		}
		if((i+j) > MAX){
			overflow = true;
			break;
		}

		if(j > i){
			cout << "---- " << j << endl;
			i = j + i;
		}
		else {
			cout << "---- " << i << endl;
			j = j + i;

		}
		count++;
	}

	cout << "Anzahl Zahlen: " << count << endl;

	return 0;
}
