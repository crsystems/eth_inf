#include <iostream>
#include <fstream>
#include <string>
#include <cmath>
using namespace std;

int main(void){

	string filename;
	cin >> filename;

	ifstream file (filename);

	char bit;
	int byte = 0;
	while(file.get(bit)){
		byte = 0;
		for(int i = 0; i < 8; i++){
			if(bit == '1'){
				byte += pow(2, (7-i));
			}
		}
		cout << (char) byte;
	}

	file.close();

	return 0;
}
