#include <iostream>

using namespace std;

void encode(){
	int cur_byte, last_byte, count = 0;

	cout << "1 ";

	cin >> cur_byte;
	last_byte = cur_byte;
	while(cur_byte != -1){
		while(cur_byte == last_byte){
			if(cur_byte > 255 || cur_byte < 0){
				cout << "\rerror";
			}
			if(count == 255){
				cout << count << " " << cur_byte << " ";
				count = 0;
			}
			count++;
			cin >> cur_byte;
		}

		cout << count << " " << last_byte << " ";
		
		last_byte = cur_byte;
		count = 0;
	}
	cout << "-1";
	return;

}

void decode(){
	int byte = 0, count, counter = 0;

	cout << "0 ";

	while(byte != -1){
		cin >> count;
	        if(count == -1){
			break;
		}else if(count < 0 || count > 255){
			cout << "\rerror";
			break;
		}
		
		cin >> byte;
		if(byte < 0 || byte > 255){
			cout << "\rerror";
			break;
		}else{
			while(counter < count){
				cout << byte << " ";
				counter++;
			}
			counter = 0;
		}
	}
	return;
}


void decide(int in){
	switch(in){
		case 0: encode();
			break;
		case 1: decode();
			break;
		default: cout << "error\n";
			 break;
	}
	return;
}


int main(void){

	int start;
	cin >> start;

	decide(start);

	return 0;
}
