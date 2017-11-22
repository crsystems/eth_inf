#include <iostream>

bool lexicographic_compare(std::string::iterator str_1_b, std::string::iterator str_1_e, std::string::iterator str_2_b, std::string::iterator str_2_e){
  int i = 0;
  while((str_1_b + i) < str_1_e && (str_2_b +i) < str_2_e){
    if(*(str_1_b +i) != *(str_2_b + i)){
      break;
    }
    i++;
  }
  if((str_1_b + i) == str_1_e && (str_2_b +i) != str_2_e){
    return true;
  }else if((str_2_b + i) == str_2_e){
    return false;
  }else{
    int a = (int) *(str_1_b + i);
    int b = (int) *(str_2_b + i);
    if(a < b){
      return true;
    }else{
      return false;
    }
  }
}


int main() {
  // string input
  std::string name1;
  std::string name2;
  std::cin >> name1;
  std::cin >> name2;

  // output result for standard library strings
  if (lexicographic_compare (name1.begin(), name1.end(), name2.begin(), name2.end()))
    std::cout << name1 << " < " << name2 << "\n";
  else
    std::cout << name1 << " >= " << name2 << "\n";

  return 0;
}
