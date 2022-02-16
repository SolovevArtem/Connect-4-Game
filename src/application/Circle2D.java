/**********************************************
Workshop #7
Course: JAC444 - Semester Winter
Last Name: Solovev
First Name: Artem
ID: 136267184
Section: NFF
This assignment represents my own work in accordance with Seneca Academic Policy. Signature: Solovev
Date: 03/21/2021
**********************************************/
package application;

public class Circle2D {
private double x;
private double y;
private double radius;


Circle2D() {
	this(0,0,1);
}

Circle2D(double x1, double y2, double rad) {
	this.x = x1;
	this.y = y2;
	this.radius = rad;

}


public double getX() {
	return x;
}


public double getY() {
	return y;
}


public double getRadius() {
	return radius;
}

public double getArea() {
	return Math.PI * this.getRadius() * this.getRadius();
}

public double getPerimeter() {
	return 2 * Math.PI * this.getRadius();
}

public boolean contains(double x, double y) {

	return Math.sqrt(Math.pow(x - this.x, 2) + Math.pow(y - this.y, 2)) < radius;
}

public boolean contains(Circle2D Circle) {
	
	return Math.sqrt(Math.pow(Circle.getX() - x, 2) + Math.pow(Circle.getY() - y, 2)) <= Math.abs(radius - Circle.getRadius());
}

public boolean overlaps(Circle2D Circle) {
	
	
	return Math.sqrt(Math.pow(Circle.getX() - x, 2) + Math.pow(Circle.getY() - y, 2)) <= radius + Circle.getRadius();
}
	
public static void main(String[] args) {
	Circle2D c1 = new Circle2D(2, 2, 5.5);
	
	System.out.println("Circle1 area: " + c1.getArea()); 
	System.out.println("Circle1 perimeter: " + c1.getPerimeter()); 
	System.out.println("Circle1 contains the point (3, 3)? " + c1.contains(3, 3)); 
	System.out.println("Circle1 contains the circle centered at (4, 5) and radius 10.5? " + c1.contains(new Circle2D(4, 5, 10.5)));
	System.out.println("Circle1 overlaps the circle centered at (3, 5) and radius 2.3? " + c1.overlaps(new Circle2D(3, 5, 2.3)));
	
}	
	
}
