#include "tests.h"

#include <iostream>

typedef std::string::iterator It;

bool lexicographic_compare(It str1_b, It str1_e, It str2_b, It str2_e){
    
    for(int i=0; i<(str1_e-str1_b)||i<(str2_e-str2_b); i++){
        
        if (*(str1_b+i)<*(str2_b+i)){
            
            return true;
            break;
            
        }else if(*(str1_b+i)>*(str2_b+i)){
            
            return false;
            break;
        }
    }
    
}

int main() {
  // string input
  std::string name1;
  std::string name2;
  std::cin >> name1;
  std::cin >> name2;

  //output result for standard library strings
  if (lexicographic_compare (name1.begin(), name1.end(), name2.begin(), name2.end()))
    std::cout << name1 << " < " << name2 << "\n";
  else
    std::cout << name1 << " >= " << name2 << "\n";

  return 0;
}
