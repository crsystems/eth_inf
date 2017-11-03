//Author: Nicole Narr
//Owner: Nicole Narr 

#include <iostream>

// PRE:  a year greater or equal than 1900
// POST: returns whether that year was a leap year
bool is_leap_year(unsigned int year){
    if ((year-1900)%4==0){
        if (year%100==0 && year%400!=0){return false;}
        else{return true;}
    }
    else{return false;}
}

// PRE:  a year greater or equal than 1900
// POST: returns the number of days in that year
unsigned int count_days_in_year(unsigned int year){
    if(is_leap_year(year)==true){return 366;}
    else{return 365;}
}

// PRE:  a month between 1 and 12 and a year greater or equal than 1900
// POST: returns the number of days in the month of that year
unsigned int count_days_in_month(unsigned int month, unsigned int year){
    switch(month){
        case 1:
        case 3:
        case 5:
        case 7:
        case 8:
        case 10:
        case 12: return 31; break;
        case 2: if(count_days_in_year(year)==366){return 29; break;}
                else{return 28;}
                break;
        case 4:
        case 6:
        case 9:
        case 11: return 30; break;
        
    }
}

// PRE:  n/a
// POST: returns whether the given values represent a valid date
bool is_valid_date(unsigned int day, unsigned int month, unsigned int year){
    if(year>=1900){
        if(month>=1 && month<=12){
            if(day>=1 && day<=count_days_in_month(month,year)){
                return true;
            }
            else return false;
        }
        else return false;
    }
    else return false;
}

// PRE:  the given values represent a valid date
// POST: returns the number of days between January 1, 1900 and this date
unsigned int count_days(unsigned int day, unsigned int month, unsigned int year){
    unsigned int daycount=0;
    
    for(unsigned int i=1900; i<year;i++){
        daycount=daycount+count_days_in_year(i);
    }
    for(unsigned int i=1;i<month;i++){
        daycount=daycount+count_days_in_month(i,year);
    }
    for(unsigned int i=1;i<day;i++){
        daycount++;
    }
    
    return daycount;
}

int main(){
    unsigned int day,month,year;
    std::cin >> day >> month >> year;
    if(is_valid_date(day,month,year)){
        switch(count_days(day,month,year)%7){
            case 0: std::cout << "Monday"; break;
            case 1: std::cout << "Tuesday"; break;
            case 2: std::cout << "Wednesday"; break;
            case 3: std::cout << "Thursday"; break;
            case 4: std::cout << "Friday"; break;
            case 5: std::cout << "Saturday"; break;
            case 6: std::cout << "Sunday"; break;
        }
    }
    else{std::cout << "Invalid date";}
}
