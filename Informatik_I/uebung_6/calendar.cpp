#include <iostream>

using namespace std;

// PRE:  a year greater or equal than 1900
// POST: returns whether that year was a leap year
bool is_leap_year(unsigned int year);

// PRE:  a year greater or equal than 1900
// POST: returns the number of days in that year
unsigned int count_days_in_year(unsigned int year);

// PRE:  a month between 1 and 12 and a year greater or equal than 1900
// POST: returns the number of days in the month of that year
unsigned int count_days_in_month(unsigned int month, unsigned int year);

// PRE:  n/a
// POST: returns whether the given values represent a valid date
bool is_valid_date(unsigned int day, unsigned int month, unsigned int year);

// PRE:  the given values represent a valid date
// POST: returns the number of days between January 1, 1900 and this date
unsigned int count_days(unsigned int day, unsigned int month, unsigned int year);

int main(void){
  unsigned int year, month, day;
  cin >> day >> month >> year;
  if(is_valid_date(day, month, year)){

    switch(count_days(day, month, year) % 7){
      case 0: cout << "Monday" << endl; break;

      case 1: cout << "Tuesday" << endl; break;

      case 2: cout << "Wednesday" << endl; break;

      case 3: cout << "Thursday" << endl; break;

      case 4: cout << "Friday" << endl; break;

      case 5: cout << "Saturday" << endl; break;

      case 6: cout << "Sunday" << endl; break;
    }
  } else {
    cout << "invalid date\n";
  }
  return 0;
}

// PRE:  a year greater or equal than 1900
// POST: returns whether that year was a leap year
bool is_leap_year(unsigned int year){
    if(year % 4 != 0){
      return false;
    } else if(year % 100 != 0){
      return true;
    } else if(year % 400 != 0){
      return false;
    } else {
      return true;
    }
}

// PRE:  a year greater or equal than 1900
// POST: returns the number of days in that year
unsigned int count_days_in_year(unsigned int year){
    if(is_leap_year(year)){
      return 366;
    } else {
      return 365;
    }
}

// PRE:  a month between 1 and 12 and a year greater or equal than 1900
// POST: returns the number of days in the month of that year
unsigned int count_days_in_month(unsigned int month, unsigned int year){
    if(month == 2){
      if(is_leap_year(year)){
        return 29;
      } else {
        return 28;
      }
    } else if((month < 8 && month % 2 != 0) || (month >= 8 && month % 2 == 0)){
      return 31;
    } else {
      return 30;
    }
}

// PRE:  n/a
// POST: returns whether the given values represent a valid date
bool is_valid_date(unsigned int day, unsigned int month, unsigned int year){
    if(year < 1900){
      return false;
    }
    if(month > 12){
      return false;
    }
    if(day > 31 || day < 1){
      return false;
    }
    if(is_leap_year(year) == true && month == 2 && day > 29){
      return false;
    }
    if(is_leap_year(year) == false && month == 2 && day > 28){
      return false;
    }
    if(month != 2 && ((month < 8 && month % 2 == 0 && day > 30) || (month >= 8 && month % 2 != 0 && day > 30))){
      return false;
    }
    if(month != 2 && ((month <8 && month % 2 != 0 && day > 31) || (month >= 8 && month % 2 == 0 && day > 31))){
      return false;
    }
    return true;
}

// PRE:  the given values represent a valid date
// POST: returns the number of days between January 1, 1900 and this date
unsigned int count_days(unsigned int day, unsigned int month, unsigned int year){
    unsigned int y = 1900, m = 1, days = 0;

    while(y < year){
      days += count_days_in_year(y);
      y++;
    }

    while(m < month){
      days += count_days_in_month(m, year);
      m++;
    }

    if(1 < day){
        days += day-1;
    }

    return days;
}
