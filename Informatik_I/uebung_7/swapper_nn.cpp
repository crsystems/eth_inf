#include <iostream>


int main(){
    int arr[10];
    int speicher;
    bool change=false;
    
    std::cin >> arr[0];
    for(int i=1; i<10; i++){
        std::cin >> arr[i];
    }
    for(int i=1; i<=10; i++){
        for(int i=1; i<10; i++){
            if(arr[i]>arr[i-1]){
                    speicher=arr[i];
                    arr[i]=arr[i-1];
                    arr[i-1]=speicher;
                    change=true;
            }
        }
        if(change==false){break;}
        else{change=false;}
    }
    
    for(int i=0; i<10; i++){
        std::cout << arr[i] << " ";
    }
    
}
