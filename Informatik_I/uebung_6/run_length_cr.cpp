//Author: Christopher Reinwardt
//Owner:  Christopher Reinwardt
//
#include <iostream>

using namespace std;

void encode(int *data){
	int last_byte, cur_byte, count = 0;
	int *swap;

	data = (int *) calloc(2, sizeof(int));


	while(...){

		cin >> cur_byte;
		last_byte = cur_byte;
		while(cur_byte == last_byte){
			count++;
			cin >> cur_byte;
		}
		swap = (int *) calloc(1, sizeof(data)+(2*sizeof(int)));
		memcpy(swap, data, sizeof(data));
		free(data);
		data = swap;








int main(void){
	
	int *data;

	cin >> input;
	switch(input){
		case 0: encode(data);
			break;
		case 1: decode(data);
			break;
		default: cout << "error";
			 break;
	}

	return 0;	
}
