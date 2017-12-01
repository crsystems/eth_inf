#include <iostream>
#include <sstream>
#include <istream>
#include <string>

using namespace std;

bool train(istream &in);

char lookahead(istream &in);

bool consume(istream &in, char c);

bool open(istream &in);

bool loco(istream &in);

bool cars(istream &in);

bool compositions(istream &in);

bool composition(istream &in);



int main(void){
	string check;
	cin >> check;
	
	stringstream in (check);
	if(train(in)){
		cout << "Valid\n";
	}else {
		cout << "Invalid\n";
	}
	return 0;
}

bool train(istream &in){
	if(open(in) && !compositions(in)){
		return true;
	}else if(!open(in) && compositions(in)){
		return true;
	}
	return false;
}

char lookahead(istream &in){
	char kill;
	while(in.peek() == ' ' && in.eof() != true){
		in >> kill;
	}
	if(in.eof()){
		return 0;
	}else{
		return in.peek();
	}
}

bool consume(istream &in, char c){
	if(lookahead(in) == c){
		in >> c;
		return true;
	}else{
		return false;
	}
}

bool open(istream &in){
	if(loco(in)){
		if(cars(in)){
			return true;
		}
	}
	return false;
}

bool loco(istream &in){
	if(lookahead(in) != '*'){
		return false;
	}
	while(consume(in, '*')){
		loco(in);
	}
	return true;
}

bool cars(istream &in){
	if(lookahead(in) != '-'){
		return false;
	}
	while(consume(in, '-')){
		cars(in);
	}
	return true;
}

bool compositions(istream &in){
	if(composition(in)){
		if(in.eof() != true){
			while(composition(in)){}
			if(in.eof() != true){
		    		return false;
			}
		}
		return true;
	}else{
		return false;
	}
}

bool composition(istream &in){
	if(consume(in, '<') && open(in) && loco(in) && consume(in, '>')){
		return true;
	}else{
		return false;
	}
}

