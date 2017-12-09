// Prog: calculator_double.cpp
// evaluates arithmetic expressions involving +, -, *, /, (, ) 
// over double operands

// Syntax in EBNF:
// ---------------
// expression = term { "+" term | "-" term }
// term = factor { "*" factor | "/" factor }
// factor = unsigned_double | "(" expression ")" | -factor

#include<iostream>
#include<istream>
#include<sstream>
#include<cassert>
#include<cmath>

using namespace std;

struct Complex{
	double a;
	double b;
};

istream& operator>> (istream &in, Complex &comp){
	char dummy;
	in >> dummy;
	while(dummy != '['){
	    in >> dummy;
	}
	in >> comp.a;
	in >> dummy;
	in >> comp.b;
	in >> dummy;
	return in;
}


ostream& operator<< (ostream &out, Complex comp){
	return out << "[" << comp.a << "," << comp.b << "]";
}

Complex operator+ (Complex comp1, Complex comp2){
	Complex sum;
	sum.a = comp1.a + comp2.a;
	sum.b = comp1.b + comp2.b;
	return sum;
}

Complex operator- (Complex comp1, Complex comp2){
	Complex sub;
	sub.a = comp1.a - comp2.a;
	sub.b = comp1.b - comp2.b;
	return sub;
}

Complex operator- (Complex comp){
	Complex comp_neg;
	comp_neg.a = -comp.a;
	comp_neg.b = -comp.b;
	return comp_neg;
}

Complex operator* (Complex comp1, Complex comp2){
	Complex mul;
	mul.a = (comp1.a * comp2.a) - (comp1.b * comp2.b);
	mul.b = (comp1.a * comp2.b) + (comp1.b * comp2.a);
	return mul;
}

Complex operator/ (Complex comp1, Complex comp2){
	Complex div;
	div.a = ((comp1.a * comp2.a) + (comp1.b * comp2.b))/((comp2.a * comp2.a) + (comp2.b * comp2.b));
	div.b = ((comp1.b * comp2.a) - (comp1.a * comp2.b))/((comp2.a * comp2.a) + (comp2.b * comp2.b));
	return div;
}

bool operator== (Complex comp1, Complex comp2){
	if(comp1.a == comp2.a && comp1.b == comp2.b){
		return true;
	}else{
		return false;
	}
}

bool operator!= (Complex comp1, Complex comp2){
	if(comp1 == comp2){
		return false;
	}else{
		return true;
	}
}

// declarations
// ------------

// PRE: is = expression...
// POST: expression is extracted from is, and
//       its value is returned
Complex expression (std::istream& is);

// PRE: is = term...
// POST: term is extracted from is, and
//       its value is returned
Complex term  (std::istream& is);

// PRE: is = factor...
// POST: factor is extracted from is, and
//       its value is returned
Complex factor  (std::istream& is);

// definitions
// -----------

// POST: leading whitespace characters are extracted
//       from is, and the first non-whitespace character 
//       is returned (0 if there is no such character)
char lookahead (std::istream& is)
{
  is >> std::ws;         // skip whitespaces
  if (is.eof()) 
    return 0;            // end of stream
  else 
    return is.peek();    // next character in is
}

// POST: if next chararcer in is is ch, consume c and return
//       true, otherwise return false
bool consume  (std::istream& is, char c)
{
  if (lookahead (is) == c) {
    is >> c;
    return true;
  } else
    return false;
}

// expression = term { "+" term | "-" term }
Complex expression (std::istream& is)
{
  Complex value = term (is);        // term
  while (true) {
    if (consume (is, '+')) 
      value = value + term (is);          // "+" term
    else if (consume (is, '-'))
      value = value - term (is);          // "-" term
    else
      return value;
  }
}

// term = factor { "*" factor | "/" factor }
Complex term (std::istream& is)
{
  Complex value = factor (is);        // factor
  while (true) {
    if (consume (is, '*')) 
      value = value * factor (is);          // "*" factor
    else if (consume (is, '/'))
      value = value / factor (is);          // "/" factor
    else
      return value;
  }
}

// factor = unsigned_double | "(" expression ")" | -factor
Complex factor (std::istream& is)
{
  Complex value;
  if (consume (is, '(')) {   
    value = expression (is);      // "(" expression 
    consume (is, ')');            // ")"
  } else if (consume (is, '-'))
    value = -factor (is);         // - factor 
  else
    is >> value;                  // unsigned_number
  return value;
}

int main()
{
  std::cout << "enter expression to be calculated:\n";
  
  // reads a whole line from stream and wrap it into string stream
  std::string line;
  getline(std::cin, line);
  std::stringstream input(line);

  // parse and calculate expression
  std::cout << expression(input) << "\n";

  return 0;
}

