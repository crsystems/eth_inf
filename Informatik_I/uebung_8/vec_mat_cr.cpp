#include <iostream>

using namespace std;

//PRE: A writable 3x3 int array
//POST: Fills the first column with the values provided via stdin
void read_vector(int storage[3][3]){
	for(int i = 0; i < 3; i++){
		cin >> storage[0][i];
	}
	return;
}

//PRE: A writeable 3x3 int array
//POST: Fills the array column-wise with the values provided via stdin
void read_matrix(int storage[3][3]){
	for(int i = 0; i < 3; i++){
		for(int k = 0; k < 3; k++){
			cin >> storage[i][k];
		}
	}
	return;
}

//PRE: Two writeable 3x3 int arrays
//POST: Querys for the input types of the two operands, reads the values into the provided arrays
//	and returns values from -1 for error to 2 for matrix matrix product
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

//PRE: The 3x3 int array to be initiated
//POST: Fills the array with zeros
void init(int arr[3][3]){
	for(int i = 0; i < 3; i++){
		for(int k = 0; k < 3; k++){
	       		arr[i][k] = 0;
		}
	}		
	return;
}

//PRE: Two 3x3 operand int arrays with the column vector in the first column and a 3x3, preferably initiated, output int array 
//POST: Stores the calculated cross product in the first column of the result array
void v_v_p(int in_left[3][3], int in_right[3][3], int out[3][3]){
	out[0][0] = (in_left[0][1]*in_right[0][2]) - (in_left[0][2]*in_right[0][1]); 
	out[0][1] = (in_left[0][2]*in_right[0][0]) - (in_left[0][0]*in_right[0][2]);
	out[0][2] = (in_left[0][0]*in_right[0][1]) - (in_left[0][1]*in_right[0][0]);
	return;
}

//PRE: Two 3x3 operand int arrays with the column vector in the first column and a 3x3, preferably initiated, output int array
//POST: Stores the calculated matrix vector product in the first column of the result array
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

//PRE: Two 3x3 operand int arrays and a 3x3, preferably initiated, output int array
//POST: Stores the calculated matrix matrix product in the output array
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

//PRE: The int value for the kind of result to be displayed and the result 3x3 int array
//POST: N/A
void output(int action, int result[3][3]){
	switch(action){
		case 0:
		case 1:	for(int i = 0; i < 3; i++){
				cout << result[0][i] << endl;
			}
			break;

		case 2: for(int i = 0; i < 3; i++){
				cout << result[0][i] << " " << result[1][i] << " " << result[2][i] << endl;
			}
			break;
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
			output(0, res);
			break;

		case 1: m_v_p(op_a, op_b, res);
			output(1, res);
			break;

		case 2: m_m_p(op_a, op_b, res);
			output(2, res);
			break;

		default: cout << "unsupported action.\n Quitting due to critical errors\n";
			 return 0;
	}	

	return 0;
}
