#include <iostream>

using namespace std;


//PRE: N/A
//POST: reads a newline separated bytestring from stdin and outputs the runlength encoded string
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

//PRE: N/A
//POST: reads a newline separated, runlength encoded bytestring and outputs the decoded bytestring
void decode(){
	int byte = 0, count, counter = 0;

	cout << "0 ";

	while(byte != -1){
		cin >> count;
	        if(count == -1){
			cout << "-1";
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

//PRE: input of type integer with meanings: 0 for encoding and 1 for decoding
//POST: calls the appropriate method or outputs error
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
