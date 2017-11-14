#include <iostream>

using namespace std;


void input(int operand_a[3][3],  int operand_b[3][3], char *type_a, char *type_b){
	cin >> *type_a;
	cin >> *type_b;





	return;
}

void init(int arr[3][3]){
	for(int i = 0; i < 3; i++){
		for(int k = 0; k < 3; k++){
	       		arr[i][k] = 0;
		}
	}		
	return;

}
int main(void){


	char t_a, t_b;

	int op_a[3][3], op_b[3][3];

	init(op_a);
	init(op_b);

	input(op_a, op_b, &t_a, &t_b);
	
        cout << op_a[1][2] << endl << op_b[2][1] << endl << t_a << endl << t_b << endl;	



	return 0;
}
