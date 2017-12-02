// UNCOMMENT THE NEXT LINE TO ENABLE THE TESTS WITHOUT SUBMITTING
#include "tests.h"
#include<iostream>
#include<istream>
#include<sstream>
#include<cassert>
#include<cmath>


bool train(std::istream& is);
bool open(std::istream& is);
bool loco(std::istream& is);
bool cars(std::istream& is);
bool compositions(std::istream& is);
bool composition(std::istream& is);

char lookahead(std::istream& is){
    
    if(is.eof()){ return 0;}
    is >> std::ws;
    if (is.eof()){return 0;}
    return is.peek();
}

bool consume  (std::istream& is, char c)
{
  if (lookahead (is) == c) {
    is >> c;
    return true;
  } else{
    return false;}
}


bool train(std::istream& is){
    if(open(is)){
        if(is.eof()){return true;}
    }
    else if(compositions(is)){
        if(is.eof()){return true;}
    }
    return false;
}


bool open(std::istream& is){
    
    if(loco(is)){
        if(cars(is)){
            return true;   
        }
        else{return false;}
    }
    return false;
}


bool loco(std::istream& is){
    
    if(consume(is,'*')){
        while(consume(is,'*')){}
        return true;
    }
    return false;
    
}
    

bool cars(std::istream& is){
    
    if(consume(is,'-')){
        while(consume(is,'-')){}
        return true;
    }
    return false;
}

bool compositions(std::istream& is){
    if(lookahead(is)!='<'){return false;}
    while(lookahead(is)=='<'){
        if(!composition(is)){
                return false;
            }
        
    }
    return true;
}

bool composition(std::istream& is){
    if(consume(is,'<')){
        if(open(is)){
            if(loco(is)){
                if(consume(is,'>')){
                    return true;
                }    
            }
        }
    }
    return false;
}



void check(std::string s){
    std::stringstream input (s);
    if (train (input)){
        std::cout << "valid" << std::endl;
    } else {
        std::cout << "invalid"  << std::endl;
    }
}

 
 
int main(){
    std::string train;
    std::cin >> train;
    check(train);
    return 0;
}
