#include "tests.h"

#include <iostream>

using namespace std;

int main(void){
    
    int arr[10];
    bool changed = true, executed = false;
    
    for(int i = 0; i < 10; i++){
        cin >> arr[i];
    }
    
    while(changed != false){
        for(int i = 0; i < 10; i++){
            int swap;
            if(arr[i] > arr[i-1]){
                swap = arr[i];
                arr[i] = arr[i-1];
                arr[i-1] = swap;
                executed = true;
            } else if(i == 9 && executed == false){
                changed = false;
            }
        }
        executed = false;
    }
    
    for(int i = 0; i < 10; i++){
        cout << arr[i] << " " ;
    }

    return 0;
}
