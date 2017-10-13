#include <iostream>
#include <vector>
using namespace std;

int main(void){

	unsigned int i, r;
	int c = 31;
	vector<int> binary (32, 0);
	
	cout << "Bitte geben Sie ihre Dezimalzahl ein: ";
	cin >> i;
	do {
		r = i % 2;
		i = i / 2;
		if(r == 1){
			binary[c] = 1;
		} else {
			binary[c] = 0;
		}
		c--;
	} while(i != 0 && c >= 0);
	
	c++;
	while(c <= 31){
		cout << binary[c];
		c++;
	}

	return 0;
}
