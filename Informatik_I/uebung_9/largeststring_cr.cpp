#include <iostream>
#include <string>
using namespace std;

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

int main(void){
  string a ("");
  string b;
  int num;

  cin >> num;

  for(int i = 0; i < num; i++){
    cin >> b;
    if(lexicographic_compare(a.begin(), a.end(), b.begin(), b.end())){
      a = b;
    }
  }
  
  cout << a;
  return 0;
}
