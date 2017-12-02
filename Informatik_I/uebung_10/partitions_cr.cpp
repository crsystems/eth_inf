#include<iostream>

// PRE: [begin, end) is a valid nonempty range that describes
//      a sequence of denominations d_1 > d_2 > ... > d_n > 0
// POST: return value is the number of ways to partition amount
//       using denominations from d_1, ..., d_n
unsigned int partitions (const unsigned int amount, const unsigned int* begin, const unsigned int* end){
	unsigned int times = 0;
	unsigned int money = amount;

	if(amount == 0){
		return 1;
	}
    
	if(money > 0){
	    int x = 0;
	    for(int i = 0; i < (end-begin); i++){
	        if(money >= *(begin+i)){
	            x=i;
	            break;
	        }
	    }
	    if((begin+x+1) < end){
	            times += partitions(amount, begin+x+1, end);
	    }
	    
	    while(money >= *(begin+x)){
	            
	        money -= *(begin+x);
	        
	        if(money == 0){
			times++; 
			break;
		}
            
	        if((begin+x+1) < end){
	                times += partitions(money, begin+x+1, end);
	 	}
	    }
	 }
	 return times;
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
