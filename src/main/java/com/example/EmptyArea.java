// // import java.util.*;

// // public class EmptyArea {

// <<<<<<< HEAD
// // 	public EmptyArea(EmptyAreaWrapper input) {
// =======
// 	Rectangle container = new Rectangle();
// 	Rectangle rectangle = new Rectangle();
// 	Circle circle = new Circle();
// 	Square square = new Square();

// 	public EmptyArea(EmptyAreaWrapper input) {
// 		container.setx1 = input.getContainer.getx1();
// 		container.setx2 = input.getContainer.getx2();
// 		container.set

// 	}

// 	public double calculateArea(Rectangle container, Object Child){
// 		if(child.getx1()>= container.getX2() || child.getx2() <= container.getX1() || child.gety1() >= container.gety2() || child.gety2() <= container.gety1()){
// 			calculateOutsideArea(container, child);
// 		}
// 		else if (child.getx1() >= container.getx1() && child.getx2() <= container.getx2( && child.gety1() >= container.gety1() && child.gety2() <= container.gety2(){
// 			calculateInsideArea(container, child);
// 		}
// 		else{
// 			calculateIntersectArea(container, child);
// 		}
// 	}
	
// 	public double calculateInsideArea(Rectangle container, Object child){
// 		if (child instanceof Circle) {
// 			Circle circle = (Circle) child;
// 		} else if (child instanceof Rectangle) {
// 			Rectangle rect = (Rectangle) child;
// 		} else {
// 			Square sq = (Square) child;
// 		}

// 		double area = container.getArea() - child.getArea();
// 		return area;
// 	}

// 	public double calculateOutsideArea(Rectangle container, Object child){
// 		return container.getArea();
// 	}

// 	//intersection
// 	public double calculateIntersectArea(Rectangle container, Object child) {
// 		double area = 0;
// 		double wid = 0;
// 		double height = 0;
// 		double radius = 0;

// 		if (child instanceof Circle) {
// 			Circle circle = (Circle) child;
// 		} else if (child instanceof Rectangle) {
// 			Rectangle rect = (Rectangle) child;
// 		} else {
// 			Square sq = (Square) child;
// 		}
// >>>>>>> 15200b07d091ebbd62e5e6601e2998879a7bede1

// 		if (child.getx1() <= container.getx1()) {
// 			wid = child.getx2() - container.getx1();
// 		} else {
// 			wid = container.getx2() - child.getx1();
// 		}

// 		if (child.gety1() <= container.gety1()) {
// 			height = child.gety2() - container.gety1();
// 		} else {
// 			height = container.gety2() - child.gety1();
// 		}

// <<<<<<< HEAD
// // 	}

// // 	class Circle() {
// // 		double radius;
// // 		doublt x1;
// // 		double y1;
// =======
// 		double intersectArea = wid * height;
// 		area = container.getArea() - intersectArea;
// 		return area;
// 	}

// 	class Circle {
// 		double radius;
// 		double x1;
// 		double y1;
// >>>>>>> 15200b07d091ebbd62e5e6601e2998879a7bede1

// // 		public double getArea() {
// // 			return radius*radius*Math.PI;
// // 		}

// // 		public double getRadius() {
// // 			return radius;
// // 		}

// // 		public double getx1() {
// // 			return x1;
// // 		}

// <<<<<<< HEAD
// // 		public double gety1() {
// // 			return y1;
// // 		}
// // 	}

// // 	class Rectangle() {
// // 		double x1;
// // 		double y1;
// // 		double width;
// // 		double len;
// =======
// 		public double gety1() {
// 			return y1;
// 		}

// 		public void setRadius(double radius){
// 			this.radius = radius;
// 		}

// 		public void setx1(double x1) {
// 			this.x1 = x1;
// 		}

// 		public void setx2(double x2){
// 			this.x2 = x2;
// 		}
// 	}

// 	class Rectangle {
// 		double x1;
// 		double y1;
// 		double width;
// 		double height;
// >>>>>>> 15200b07d091ebbd62e5e6601e2998879a7bede1

// // 		public double getArea() {
// // 			return width*len;
// // 		}

// // 		public double getx1() {
// // 			return x1;
// // 		}

// // 		public double gety1() {
// // 			return y1;
// // 		}

// // 		public double getx2() {
// // 			return x1+width;
// // 		}

// <<<<<<< HEAD
// // 		public double gety2() {
// // 			return y2+len;
// // 		}
// // 	}

// // 	class Square() {
// // 		double width;
// // 		double x1;
// // 		double y1
// =======
// 		public double gety2() {
// 			return y2+height;
// 		}

// 		public void setWidth(double width){
// 			this.width = width;
// 		}

// 		public void setHeight(double height){
// 			this.height = height;
// 		}

// 		public void setx1(double x1) {
// 			this.x1 = x1;
// 		}

// 		public void setx2(double x2){
// 			this.x2 = x2;
// 		}
// 	}

// 	class Square {
// 		double width;
// 		double x1;
// 		double y1
// >>>>>>> 15200b07d091ebbd62e5e6601e2998879a7bede1

// // 		public double getArea() {
// // 			return width*width;
// // 		}

// // 		public double getx1() {
// // 			return x1;
// // 		}

// // 		public double gety1() {
// // 			return y1;
// // 		}

// // 		public double getx2() {
// // 			return x1+width;
// // 		}

// <<<<<<< HEAD
// // 		public double gety2() {
// // 			return y1+width;
// // 		}
// // 	}
// =======
// 		public double gety2() {
// 			return y1+width;
// 		}

// 		public void setWidth(double width){
// 			this.width = width;
// 		}

// 		public void setHeight(double height){
// 			this.height = height;
// 		}
		
// 		public void setx1(double x1) {
// 			this.x1 = x1;
// 		}

// 		public void setx2(double x2){
// 			this.x2 = x2;
// 		}
// 	}
// >>>>>>> 15200b07d091ebbd62e5e6601e2998879a7bede1

// // }