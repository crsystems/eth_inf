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

		case 1:
			num = 0;
			break;
		default: 
			num = fibonacci(m);
			break;
	}

	cout << num << " Fibonacci prime(s) found." << endl;
	return 0;
}

int fibonacci(unsigned int top){
	unsigned int i = 1, j = 2;
	unsigned int count = 0;
	bool overflow = false;
	while(j <= top && i <= top && overflow == false){
		if(j > i){
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
	return count;
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
