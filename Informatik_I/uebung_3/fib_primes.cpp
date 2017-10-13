#include <iostream>

using namespace std;

int fibonacci(unsigned int top);

bool is_prime(int candidate);

int main(void){
	unsigned int m = 0;
	int num = 0;
	cout << "Please enter the upper limit of the requested Fibonacci primes: ";
	cin >> m;
	cout << "\n";

	switch(m){

		case 0: num = 0;
			break;

		case 1: cout << "0\n";
			cout << "1\n";
			cout << "1\n";
			num = 3;
			break;
		default: 
			cout << "0\n";
			cout << "1\n";
			num = fibonacci(m);
			break;
	}

	cout << num << " Fibonacci primes found." << endl;
	return 0;
}

int fibonacci(unsigned int top){
	unsigned int i = 1, j = 1;
	unsigned int count = 3;
	bool overflow = false;
	while(j <= top && i <= top && overflow == false){
		if(i == 1 && j == 1){
			cout << "1\n";
			count++;
			j = j + i;
		}
		else if(j > i){
			if(is_prime(j)){
				cout << j << endl;
				count++;
			}
			i += j;
		}
		else if(i > j){
			if(is_prime(i)){
				cout << i << endl;
				count++;
			}
			j += i;
		}
	}
	return count-1;
}

bool is_prime(int candidate){
	bool result = true;
	int d = 2;
	while(d < candidate){
		if(candidate % d == 0){
			result = false;
			break;
		}
		d++;
	}

	return result;
}
