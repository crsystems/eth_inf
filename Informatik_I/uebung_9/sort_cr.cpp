#include <iostream>

using namespace std;


void input(int *begin, int *end){
  int *ptr = begin;
  while(ptr < end){
    cin >> *ptr;
    ptr++;
  }
  return;
}

void output(const int *begin, const int *end){
  int i = 0;
  while((begin+i) < end){
    cout << *(begin+i) << " ";
    i++;
  }
  return;
}

void swap(int *elem1, int *elem2){
  int swap = *elem1;
  *elem1 = *elem2;
  *elem2 = swap;
  return;
}

int* minimum(int *begin, int *end){
  int *min, *ptr = begin;
  min = begin;
  while(ptr < end){
    if(*ptr < *min){
      min = ptr;
    }
    ptr++;
  }
  return min;
}

void swapFirstWithMinimum(int *begin, int *end){
  int *min = minimum(begin, end);
  swap(begin, min);
  return;
}

void sort(int *begin, int *end){
  int *ptr = begin;
  while(ptr < end){
    swapFirstWithMinimum(ptr, end);
    ptr++;
  }
  return;
}

int main(void){
  int arr[10];
  input(arr, arr+10);
  sort(arr, arr+10);
  output(arr, arr+10);

  return 0;
}
