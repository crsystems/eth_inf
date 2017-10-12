#define MAX 4294967296
#include <iostream>


using namespace std;

int fibonacci(unsigned int top);

int main(void){
	unsigned int m = 0;
	int num = 0;
	cout << "Bitte geben Sie die obere Grenze für die Fibonacci Reihe ein: ";
	cin >> m;

	cout << "Fibonacci Reihe bis " << m << endl;

	switch(m){

		case 0: cout << "---- 0\n";
			num = 1;
			break;

		case 1: cout << "---- 0\n";
			cout << "---- 1\n";
			cout << "---- 1\n";
			num = 3;
			break;
		default: 
			cout << "---- 0\n";
			cout << "---- 1\n";
			num = fibonacci(m);
			break;
	}

	cout << "Printed  " << num << " of " << m << "requested Fibonacci numbers" << endl;
	return 0;
}

int fibonacci(unsigned int top){
	unsigned int i = 1, j = 1;
	int count = 3;
	bool overflow = false;
	while(i <= top && j <= top && overflow == false){
		if(i == 1 && j == 1){
			cout << "---- 1\n";
			j = j + i;
			count++;
		}
		if(j > i){
			cout << "---- " << j << endl;
			if((MAX-j) <= i){
				overflow = true;
				break;
			}
			i += j;
		}
		else if(i > j){
			cout << "---- " << i << endl;
			if((MAX-i) <= j){
				overflow = true;
				break;
			}
			j += i;
		}
	}
	return count;
}
