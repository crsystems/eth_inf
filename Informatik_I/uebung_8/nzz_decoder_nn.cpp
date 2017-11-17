#include <iostream>
#include <fstream>
#include <cmath>

int main(){
    
    int byte, dec=0, remainder=0;
    
    std::string filename;
    std::cin >> filename;
    std::ifstream file (filename.c_str());
    
    while(file >> byte){
    
        for(int i=0;i<=7;i++){
            remainder = byte%10;
            byte /= 10;
            dec += remainder*pow(2,i);
        }
        std::cout << (char) dec;
        dec = 0; 
        remainder = 0;
    }
    file.close();
    cout << std::endl;
    return 0;
}
