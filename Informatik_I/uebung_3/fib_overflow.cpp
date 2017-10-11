#define MAX 4294967296
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
			cout << "---- 1\n";
			j = j + i;
		}
		if(j > i){
			if(i > (MAX/2)){
				overflow = true;
				cout << "Loop broken in j > i\n";
				break;
			}
			cout << "---- j: " << j << endl;
			i = j + i;
			count++;
		}
		else if(i > j){
			if(j > (MAX/2)){
				overflow = true;
				cout << "Loop broken in i > j\n";
				break;
			}
			cout << "---- i: " << i << endl;
			j = j + i;
			count++;
		} else {
			overflow = true;
		}
	
	}

	cout << "Anzahl Zahlen: " << count << endl;

	return 0;
}
