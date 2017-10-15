#include <iostream>

using namespace std;

int main(void){

	int n = 0;
	double sum1 = 0, sum2 = 0;

	cout << "Please enter the number of summands you want to use to approximate pi: ";
	cin >> n;
	cout << "\n";

	for(int i = 1; i <= n; i++){

		if(i % 2 == 0){
			sum1 -= (double) 1/((i*2)-1);
		}else{
			sum1 += (double) 1/((i*2)-1);
		}
	}

	cout << "Sum1: " << sum1 << endl;

	return 0;
}

