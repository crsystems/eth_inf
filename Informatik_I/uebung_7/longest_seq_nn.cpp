#include <iostream>


int main(){
    int arr[10];
    int count=1,countmax=1;
    std::cin >> arr[0];
    for(int i=1; i<10; i++){
        std::cin >> arr[i];
        if(arr[i]>arr[i-1]){
            count++; 
            if (count>countmax){countmax=count;}
            
        }
        else{
            count=1;
        }
    }
    std::cout << countmax;
      
}
