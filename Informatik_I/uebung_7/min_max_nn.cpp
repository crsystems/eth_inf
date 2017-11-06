#include <iostream>


int main(){
    int arr[10];
    int maxel=0,minel=0;
    std::cin >> arr[0];
    minel=arr[0];
    maxel=arr[0];
    for(int i=1; i<10; i++){
        std::cin >> arr[i];
        if(arr[i]>maxel){maxel=arr[i];}
        if(arr[i]<minel){minel=arr[i];}
    }
    std::cout << minel <<"/"<<maxel;
    
    
}
