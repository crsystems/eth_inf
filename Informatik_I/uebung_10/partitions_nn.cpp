// UNCOMMENT THE NEXT LINE TO ENABLE THE TESTS WITHOUT SUBMITTING
#include "tests.h"

#include<iostream>

// PRE: [begin, end) is a valid nonempty range that describes
//      a sequence of denominations d_1 > d_2 > ... > d_n > 0
// POST: return value is the number of ways to partition amount
//       using denominations from d_1, ..., d_n
unsigned int partitions (const unsigned int amount,
                         const unsigned int* begin,
                         const unsigned int* end)
{
unsigned int anzahl=0;
unsigned int geld=amount;

if(amount==0){return (++anzahl);}
    
    
if(geld>0){
    int x=0;
    for(int i=0; i<(end-begin); i++){
        if(geld>=*(begin+i)){
            x=i;
            break;
        }
    }
    if((begin+x+1)<end){
            anzahl+=partitions(amount,begin+x+1,end);
    }
    
    while(geld>=*(begin+x)){
            
        geld-=*(begin+x);
        //std::cout << "amount:" << amount << "  schritte: " << *(begin+x) << "  geld: " << geld << " x: "<< x << std::endl;
        if(geld==0){(anzahl++); break;}
            
        if((begin+x+1)<end){
                anzahl+=partitions(geld,begin+x+1,end);
                //std::cout << anzahl << std::endl;
        }
    }
    
}


return anzahl;
}


int main()
{
  // the 13 denominations of CHF
  unsigned int chf[] = {100000, 20000, 10000, 5000, 2000, 1000, 500, 200, 100, 50, 20, 10, 5};

  // input
  std::cout << "in how many ways can I own x CHF-centimes for x =?\n";
  unsigned int x;
  std::cin >> x;

  // computation and output
  std::cout << partitions (x, chf, chf + 13) << "\n";

  return 0;
}
