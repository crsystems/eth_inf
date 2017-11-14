#include <iostream>

using namespace std;

void read_vector(int storage[3][3]){
	for(int i = 0; i < 3; i++){
		cin >> storage[0][i];
	}
	return;
}

void read_matrix(int storage[3][3]){
	for(int i = 0; i < 3; i++){
		for(int k = 0; k < 3; k++){
			cin >> storage[i][k];
		}
	}
	return;
}

bool input(int operand_a[3][3],  int operand_b[3][3]){
	char type_a, type_b;
	cin >> type_a >> type_b;


	if(type_a == 'v'){
		read_vector(operand_a);
	}else if(type_a == 'm'){
		read_matrix(operand_a);
	}else{
		cout << "invalid input type" << type_a << endl;
		return false;
	}

	if(type_b == 'v'){
		read_vector(operand_b);
	}else if(type_b == 'm'){
		read_matrix(operand_b);
	}else{
		cout << "invalid input type: " << type_b << endl;
		return false;
	}

	return true;
}

void init(int arr[3][3]){
	for(int i = 0; i < 3; i++){
		for(int k = 0; k < 3; k++){
	       		arr[i][k] = 0;
		}
	}		
	return;
}

void cross_product(int in_left[3][3], int in_right[3][3], int out[3][3]){
	out[0][0] = (in_left[0][1]*in_right[0][2]) - (in_left[0][2]*in_right[0][1]); 
	out[0][1] = (in_left[0][2]*in_right[0][0]) - (in_left[0][0]*in_right[0][2]);
	out[0][2] = (in_left[0][0]*in_right[0][1]) - (in_left[0][1]*in_right[0][0]);
	return;
}


int main(void){

	int op_a[3][3], op_b[3][3], res[3][3];

	init(op_a);
	init(op_b);
	init(res);

	if(input(op_a, op_b) == false){
		cout << "exiting due to critical errors\n";
		return 0;
	}

	
        cross_product(op_a, op_b, res);
	cout << "\n\n\n" << res[0][0] << endl << res[0][1] << endl << res[0][2] << endl;	



	return 0;
}
