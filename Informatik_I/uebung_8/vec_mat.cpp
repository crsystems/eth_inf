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

int input(int operand_a[3][3],  int operand_b[3][3]){
	char type_a, type_b;
	cin >> type_a >> type_b;


	if(type_a == 'v'){
		read_vector(operand_a);
	}else if(type_a == 'm'){
		read_matrix(operand_a);
	}else{
		cout << "invalid input type" << type_a << endl;
		return -1;
	}

	if(type_b == 'v'){
		read_vector(operand_b);
	}else if(type_b == 'm'){
		read_matrix(operand_b);
	}else{
		cout << "invalid input type: " << type_b << endl;
		return -1;
	}

	if(type_a == 'v' && type_b == 'v'){
		return 0;
	}else if(type_a == 'm' && type_b == 'v'){
		return 1;
	}else if(type_a == 'm' && type_b == 'm'){
		return 2;
	}else{
		return -1;
	}
}

void init(int arr[3][3]){
	for(int i = 0; i < 3; i++){
		for(int k = 0; k < 3; k++){
	       		arr[i][k] = 0;
		}
	}		
	return;
}

void v_v_p(int in_left[3][3], int in_right[3][3], int out[3][3]){
	out[0][0] = (in_left[0][1]*in_right[0][2]) - (in_left[0][2]*in_right[0][1]); 
	out[0][1] = (in_left[0][2]*in_right[0][0]) - (in_left[0][0]*in_right[0][2]);
	out[0][2] = (in_left[0][0]*in_right[0][1]) - (in_left[0][1]*in_right[0][0]);
	return;
}

void m_v_p(int in_left[3][3], int in_right[3][3], int out[3][3]){
	for(int i = 0; i < 3; i++){
		int val = 0;
		for(int k = 0; k < 3; k++){
			val += in_left[i][k]*in_right[0][k];
		}
		out[0][i] = val;
	}
	return;
}

void m_m_p(int in_left[3][3], int in_right[3][3], int out[3][3]){
	for(int i = 0; i < 3; i++){
		for(int k = 0; k < 3; k++){
			int val = 0;
			for(int l = 0; l < 3; l++){
				val += in_left[i][l]*in_right[l][k];
			}
			out[k][i] = val;
		}
	}
	return;
}

int main(void){

	int op_a[3][3], op_b[3][3], res[3][3];

	init(op_a);
	init(op_b);
	init(res);

	switch(input(op_a, op_b)){
		case 0: v_v_p(op_a, op_b, res);
			break;

		case 1: m_v_p(op_a, op_b, res);
			break;

		case 2: m_m_p(op_a, op_b, res);
			break;

		default: cout << "unsupported action.\n Quitting due to critical errors\n";
			 return 0;
	}

	
	cout << "\n\n\n" << res[0][0] << " " << res[1][0] << " " << res[2][0] << endl;
	cout << res[0][1] << " " << res[1][1] << " " << res[2][1] << endl;
	cout << res[0][2] << " " << res[1][2] << " " << res[2][2] << endl;	



	return 0;
}
