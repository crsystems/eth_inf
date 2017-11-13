#include <iostream>

using namespace std;


void input(int *operand_a,  int *operand_b, char *type_a, char *type_b){





	return;
}

void init(int &arr){
	for(int i = 0; i < 3; i++){
		for(int k = 0; k < 3; k++){
	       		arr[i][k] = 0;
		}
	}		
	return;

}
int main(void){


	char *t_a, *t_b;

	t_a = (char *) malloc(sizeof(char));

	t_b = (char *) malloc(sizeof(char));

	int op_a[3][3], op_b[3][3];

	init(op_a);
	init(op_b);

	input(op_a, op_b, t_a, t_b); 



	return 0;
}
