#define MAX 4294967295
#include <iostream>

using namespace std;

int main(void){

	int n = 0;
	unsigned long int a = 1, b = 1;
	double sum1 = 0, sum2 = 0;

	cout << "Please enter the number of summands you want to use to approximate pi: ";
	cin >> n;

	for(int i = 1; i <= n; i++){

		if(i % 2 == 0){
			sum1 -= (double) 1/((i*2)-1);
		}else{
			sum1 += (double) 1/((i*2)-1);
		}
		
		if(i == 1){
			sum2 += 1;
		} else {
			if(MAX/(i-1) >= a && MAX/((i*2)-1) >= b){
				a *= i-1;
				b *= (i*2)-1;
				sum2 += (double) a/b;
			}
		}
	}

	cout << "Pi with Sum1: " << sum1*4 << endl;
	cout << "Pi with Sum2: " << sum2*2 << endl;

	return 0;
}

