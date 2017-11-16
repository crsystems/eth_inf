#include <iostream>


void input_v(int vector[3]){
    for(int i=0;i<3;i++){
        std::cin >> vector[i];
    }
}

void input_m(int matrix[3][3]){
    for(int i=0;i<3;i++){
        for(int t=0;t<3;t++){
            std::cin >> matrix[i][t];
        }
    }
}



void crossproduct(int in_left[3], int in_right[3], int out[3]){
    
    out[0]=in_left[1]*in_right[2]-in_left[2]*in_right[1];
    out[1]=in_left[2]*in_right[0]-in_left[0]*in_right[2];
    out[2]=in_left[0]*in_right[1]-in_left[1]*in_right[0];
    
    
    std::cout << out[0] << "\n" << out[1] << "\n" << out[2];
}


void column_vector(int matrix[3][3], int vector[3], int out[3]){
    
    for(int i=0;i<3;i++){
        out[i]=matrix[i][0]*vector[0]+matrix[i][1]*vector[1]+matrix[i][2]*vector[2];
    }
    std::cout << out[0] << "\n" << out[1] << "\n" << out[2];
}


void matrix_product(int matrix1[3][3], int matrix2[3][3], int out[3][3]){
    for(int i=0;i<3;i++){
        for(int t=0;t<3;t++){
            out[i][t]=matrix1[i][0]*matrix2[0][t]+matrix1[i][1]*matrix2[1][t]+matrix1[i][2]*matrix2[2][t];
        }
    }
    for(int q=0;q<3;q++){
       std::cout << out[q][0] << "  " << out[q][1] << "  " << out[q][2] << "\n";
    }
}


int main(){
    char operand_a, operand_b;
    
    std::cin >> operand_a;
    std::cin >> operand_b;
    
    if(operand_a=='v'){
        int a[3];
        input_v(a);
        int b[3];
        input_v(b);
        int out[3];
        crossproduct(a,b,out);
    }
    
    else if(operand_a=='m'){
        int a[3][3];
        input_m(a);
        
        if(operand_b=='v'){
            int b[3];
            input_v(b);
            int out[3];
            column_vector(a,b,out);
        }
        
        else if(operand_b=='m'){
            int b[3][3];
            input_m(b);
            int out[3][3];
            matrix_product(a,b,out);
        }
    }
    
}
