#include <iostream>
#include <string>

using namespace std;

struct Complex{
	double a;
	double b;
};

istream& operator>> (istream &in, Complex &comp){
	char dummy;
	while(dummy != '['){
	    in >> dummy;
	}
	in >> comp.a;
	in >> dummy;
	in >> comp.b;
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


int main() {
    // input operation and operands
    std::string op;
    Complex c1, c2;
    std::cin >> op >> c1;
    if (op != "inout" && op != "neg") {
        std::cin >> c2;
    }
    
    // execute operation
    if      (op == "add")   { std::cout << c1 + c2;    }
    else if (op == "sub")   { std::cout << c1 - c2;    }
    else if (op == "mul")   { std::cout << c1 * c2;    }
    else if (op == "div")   { std::cout << c1 / c2;    }
    else if (op == "eq")    { std::cout << (c1 == c2); }
    else if (op == "ne")    { std::cout << (c1 != c2); }
    else if (op == "inout") { std::cout << c1;         }
    else if (op == "neg")   { std::cout << -c1;        }
    std::cout << "\n";

    return 0;
}
