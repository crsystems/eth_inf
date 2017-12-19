// UNCOMMENT THE NEXT LINE TO ENABLE THE TESTS WITHOUT SUBMITTING
//#include "tests.h"

#include <iostream>
#include <vector>
#include <cmath>

/////////////////////////////////////////////////////////////////////////////
// CLASS DEFINITIONS
/////////////////////////////////////////////////////////////////////////////

class Shape {
public:
    Shape(bool p) : paintable(p) {}

    // PRE:-
    // POST: Returns true is area is paintable.
    bool can_paint() const
    {
        return paintable;
    }

    // PRE: -
    // POST: Returns the area of the shape.
    virtual double get_area() const = 0;

private:
    bool paintable;
};

// TODO: IMPLEMENT TRIANGLE CLASS

class Triangle : public Shape {
	public:
		Triangle(bool p, double w, double h) : Shape(p) {
			width = w;
			height = h;
		}

		double get_area() const {
			double area = (width * height) / 2;
			return area;
		}

	private:
		double width = 0;
		double height = 0;

};


// TODO: IMPLEMENT RECTANGLE CLASS

class Rectangle : public Shape {
	public:
		Rectangle(bool p, double w, double h) : Shape(p) {
			width = w;
			height = h;
		}

		double get_area() const {
			double area = width * height;
			return area;
		}

	private:
		double width = 0;
		double height = 0;
};

// TODO: IMPLEMENT CIRCLE CLASS

class Circle : public Shape {
	public:
		Circle(bool p, double r) : Shape(p) {
			radius = r;
		}

		double get_area() const {
			double area = radius * radius * pi;
			return area;
		}

	private:
		double radius = 0;
		double pi = atan(1)*4;
};

/////////////////////////////////////////////////////////////////////////////
// TEST MAIN FUNCTION (MODIFY WHERE STATED)
/////////////////////////////////////////////////////////////////////////////

int main() {
    std::vector<Shape*> shapes;

    // parse test data in the following format until end marker is found:
    // <shape> <add/remove> <parameter 1> <parameter 2>
    // where <parameter 2> is optional
    char sign;
    while(std::cin >> sign && (sign == '+' || sign == '-')) {
        bool is_paintable = sign == '+';
        
        std::string shape;
        std::cin >> shape;
        
        if (shape == "triangle") {
            double width, height;
            std::cin >> width >> height;

            // TODO: IMPLEMENT, ADD PARAMETERS AND UNCOMMENT
            shapes.push_back(new Triangle(is_paintable, width, height));
            
        } else if (shape == "rectangle") {
            double width, height;
            std::cin >> width >> height;

            // TODO: IMPLEMENT, ADD PARAMETERS AND UNCOMMENT
            shapes.push_back(new Rectangle(is_paintable, width, height));
            
        } else if (shape == "circle") {
            double radius;
            std::cin >> radius;

            // TODO: IMPLEMENT, ADD PARAMETERS AND UNCOMMENT
            shapes.push_back(new Circle(is_paintable, radius));
            
        } else {
            std::cout << "unknown shape: " << shape << "\n";
            break;
        }
    }

    // calculate area to be painted
    double area = 0;
    for(std::vector<Shape*>::iterator it = shapes.begin(); it != shapes.end(); ++it) {
        const Shape* shape = *it;

        if (shape->can_paint()) {
            area += shape->get_area();
        } else {
            area -= shape->get_area();
        }
    }
    std::cout << "Paintable area:\n" << area << "\n";

    // clean up allocated shape objects
    for(std::vector<Shape*>::iterator it = shapes.begin(); it != shapes.end(); ++it)
        delete *it;
    return 0;
}
