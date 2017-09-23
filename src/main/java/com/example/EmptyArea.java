import java.util.*;

public class EmptyArea {

	public EmptyArea(EmptyAreaWrapper input) {


	}

	class Circle() {
		double radius;
		int[] coord;

		public double getArea() {
			return radius*radius*Math.PI;
		}

		public double getRadius() {
			return radius;
		}

		public int[] getCoord() {
			return coord;
		}
	}

	class Rectangle() {
		double width;
		double len;
		int[] coord;

		public double getArea() {
			return width*len;
		}

		public double getWidth() {
			return width;
		}

		public double getLength() {
			return len;
		}

		public int[] getCoord() {
			return coord;
		}
	}

	class Square() {
		double width;
		int[] coord;

		public double getArea() {
			return width*width;
		}

		public double getWidth() {
			return width;
		}

		public int[] getCoord() {
			return coord;
		}
	}

}