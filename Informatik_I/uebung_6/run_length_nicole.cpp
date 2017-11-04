#include <iostream>


//PRE: -
//POST: writes out the now encoded byte-sequence
void encode(){
    int numb1;
    int numb2;
    int count=1;
    std::cin >> numb1;
    while (numb1!=-1 && numb1<=255){
        std::cin >> numb2;
        if (numb2==numb1 && count<255){count++;}
        else{
            if(count<255){std::cout << count << " " << numb1 << " ";}
            else{std::cout << count << " " << numb1 << " ";}
            count=1;
        }
        numb1=numb2;
    }
    if (numb1>255){std::cout << "error ";}
}
//PRE: -
//POST: writes out the now decoded byte-sequence
void decode(){
    int numb1;
    int numb2;
    std::cin >> numb1;
    while (numb1!=-1 && numb1<=255 && numb2!=-1 && numb2<=255){
        std::cin >> numb2;
        for(int i=1; i<=numb1; i++){std::cout << numb2 << " ";}
        if(numb2!=-1){std::cin >> numb1;}
        else{std::cout << "error ";}
    }
    if (numb1>255 || numb2 > 255){std::cout << "error ";}
}

//PRE: 0 or 1, starting the byte sequence
//POST: depending on the read-in bool, executes encode or decode with the appropriate start- and end-integer
void startseq(bool startbool){
    if(startbool){
        std::cout << "0 ";
        decode();
        std::cout << "-1";
    }
    else{
        std::cout << "1 ";
        encode();
        std::cout << "-1";
    }
}

int main(){
    bool start;
    std::cin >> start;
    startseq(start);
}
