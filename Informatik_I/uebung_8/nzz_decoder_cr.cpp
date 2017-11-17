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
	
	while(file.eof() != true){
		if(file.peek() == '1' || file.peek() == '0'){
			for(int i = 0; i < 8; i++){
				file.get(bit);
				if(bit == '1'){
					byte += pow(2, 7-i);
				}
			}
			cout << (char) byte;
			byte = 0;
		}else{
			file.get(bit);
		}
	}
	cout << endl;
	file.close();
	return 0;
}
