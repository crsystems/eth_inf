// UNCOMMENT THE NEXT LINE TO ENABLE THE TESTS WITHOUT SUBMITTING
#include "tests.h"
#define MAX 4294967295
#include <iostream>


using namespace std;

int fibonacci(unsigned int top);

int main(void){
	unsigned int m = 0;
	int num = 0;
	cout << "Please enter the number of requested Fibonacci numbers: ";
	cin >> m;
	cout << "\n";

	switch(m){

		case 0: num = 0;
			break;

		case 1: cout << "0\n";
			num = 1;
			break;
		default: 
			cout << "0\n";
			cout << "1\n";
			num = fibonacci(m);
			break;
	}

	cout << num << " of " << m << endl;
	return 0;
}

int fibonacci(unsigned int top){
	unsigned int i = 1, j = 1;
	unsigned int count = 3;
	bool overflow = false;
	while(count <= top && overflow == false){
		if(i == 1 && j == 1){
			cout << "1\n";
			j = j + i;
		}
		else if(j > i){
			cout << j << endl;
			if((MAX-j) <= i){
				overflow = true;
				count++;
				break;
			}
			i += j;
		}
		else if(i > j){
			cout << i << endl;
			if((MAX-i) <= j){
				overflow = true;
				count++;
				break;
			}
			j += i;
		}
		count++;
	}
	return count-1;
}
