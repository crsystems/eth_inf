#include <iostream>

using namespace std;

int main(void){
    
    int in[10], i_min = 0, i_max = 0;
    
    cin >> in[0];
    i_min = in[0];
    i_max = in[0];
    
    for(int i = 1; i < 10; i++){
        cin >> in[i];
        if(in[i] >= i_max){
            i_max = in[i];
        } else if(in[i] <= i_min){
            i_min = in[i];
        }
    }
    
    cout << i_min << "/" << i_max << endl;
    
    return 0;
}
