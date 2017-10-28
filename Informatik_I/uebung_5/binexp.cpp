#include <iostream>

using namespace std;

int main(void){
    
    double in, digit = 1;
    int count = 0;
    cin >> in;
    
    while(count < 16){
        
        if((in-digit) >= 0){
            cout << "1";
            in = in - digit;
        } else {
            cout << "0";
        }
        
        if(count == 0){
            cout << ".";
        }
        digit = digit / 2;
        count++;
    }
    cout << "\n";
    return 0;
}
