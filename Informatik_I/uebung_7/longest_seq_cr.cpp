#include <iostream>

using namespace std;

int main(void){
    
    int in[10], len_max = 1, len_cur = 1;
    
    cin >> in[0];
    for(int i = 1; i < 10; i++){
        cin >> in[i];
        
        if(in[i] > in[i-1]){
            len_cur++;
        } else {
            if(len_cur > len_max){
                len_max = len_cur;
            }
            len_cur = 1;
        }
    }
    
    if(len_cur > len_max){
        cout << len_cur << endl;
    } else {
        cout << len_max << endl;
    }
    
    return 0;
}
