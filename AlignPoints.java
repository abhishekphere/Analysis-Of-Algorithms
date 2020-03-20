
/**
 * AlignPoints.java
 *
 * Version:
 * 		 v1.2, 2/10/2018, 20:11:09
 *
 *		Initial revision
 */

/**
 * This program determines the execution time taken by the three different types of sorts
 *
 * @author Patil, Abhishek Sanjay
 *
 */

import java.util.*;

public class AlignPoints {
	
	public static ArrayList FoldingLines;
	public static ArrayList<Points> midPointsY;				// Stores midpoints of all points which makes line parallel to Y axis
	public static ArrayList<Points> midPointsX;				// Stores midpoints of all points which makes line parallel to X axis 
	public static ArrayList<Line> linesParallelToY;	
	public static ArrayList<Line> linesParallelToX;
	public static Points allPoints[];
	
	public static void main(String args[]) {
			
		Scanner s = new Scanner(System.in);
		FoldingLines = new ArrayList();
		
		int x, y, i, j, max = 0, count;
		int n = s.nextInt();
		midPointsY = new ArrayList<Points>();
		midPointsX = new ArrayList<Points>();
		linesParallelToY = new ArrayList();
		linesParallelToX = new ArrayList();
		allPoints = new Points[n];
		
		// Input
		for(i = 0; i < n; i++) {					// O(n)
			x = s.nextInt();
			y = s.nextInt();
			Points p = new Points(x, y);
			allPoints[i] = p;
		}
	
		// Constructs all possible folding lines 				O(n**2)
		for(i = 0; i < n - 1; i++) {
			for(j = i + 1; j < n; j++) {
				constructFoldingLines(i, j);
			}
		}
		
		// Stores the slope and y intercepts of lines in 2D array
		double line[][] = new double[FoldingLines.size()][2]; 
		for(i = 0; i < FoldingLines.size() ; i++) {
			Line l = (Line)FoldingLines.get(i);
			line[i][0] = (double)l.yintercept;
			line[i][1] = (double)l.m;
		}
		
		// Computes max aligned points for all folding lines parallel to y-axis
		int maxY = 0, maxX = 0;
		for( Line l: linesParallelToY ) {	
			count = countInLineParallelToY(l);
			if (count > maxY)
				maxY = count;
		}
		max = maxY;
		
		// Computes max aligned points for all folding lines parallel to y-axis
		count = 0;
		for( Line l: linesParallelToX ) {	
			count = countInLineParallelToX(l);
			if (count > maxX)
				maxX = count;
		}
		if(maxX > max)
			max = maxX;
		
		count = countMaxAligned(line);
		if(count > max)
			max = count;
		
		System.out.println(max);
		
	}
	
	public static void constructFoldingLines(int i, int j) {
		
		Points point1 = allPoints[i];
		Points point2 = allPoints[j];
		
		double x1 = point1.x;
		double x2 = point2.x;
		double y1 = point1.y;
		double y2 = point2.y;
		
		double xmid = (x1 + x2) / 2;
		double ymid = (y1 + y2) / 2;
		Points mid = new Points(xmid, ymid);		
		
		double m = - ((x2 - x1) / (y2 - y1));   // slope of folding line
		
		double yintercept = ((y1 + y2) / 2) - ((x1 + x2) / 2) * m;   // Intercept of folding line { c = y - mx (y = mx + c) }
		
		Line l = new Line(yintercept, m, x1, y1, x2, y2);
		
		// All the lines parallel to y axis are stored here
		if((y2 - y1) == 0) {
			midPointsY.add(mid);
			linesParallelToY.add(l);
		}
		// All the lines parallel to x axis are stored here
		else if((x2 - x1) == 0) {
			midPointsX.add(mid);
			linesParallelToX.add(l);
		}
		// all the slant folding lines are stored in this list
		else {
			FoldingLines.add(l);
		}
		
		
	}
	
	public static int countInLineParallelToY(Line l) {
		
		double xmid = (l.x1 + l.x2) / 2;
		int count = 0;
		
		// checks all points(x- coordinate) having same midpoints 
		for(Points p : midPointsY) {
			if( xmid == p.x ){
				count++;
			}
		}
		
		return count;
	}
	
	public static int countInLineParallelToX(Line l) {
		
		double ymid = (l.y1 + l.y2) / 2;
		int count = 0;
		
		// checks all points(y- coordinate) having same midpoints 
		for(Points p : midPointsX) {
			if( ymid == p.y ){
				count++;
			} 
		}
		
		return count;
	}
	
	public static int countMaxAligned(double line[][]) {
		
		// Sorts using column 1 of the array
		Arrays.sort(line, new Comparator<double[]>(){
			public int compare(double[] o1, double[] o2) {
	            return Double.compare(o1[1], o2[1]);
	        }
		});
		
		// Sorts using column 0 of the array again, so that every element gets organized  
		Arrays.sort(line, new Comparator<double[]>(){
			public int compare(double[] o1, double[] o2) {
	            return Double.compare(o1[0], o2[0]);
	        }
		});
				
		int i = 0, j = 0, count = 0, max = 0;
		double x1, x2;
		
		// Counts the lines with same slope and y-intercept
		for(int k = 0; k < line.length; k++) {
			
			x1 = line[k][0];
			x2 = line[k][1];
			i = k;
			
			while(i < line.length && (x1 == line[i][0] && x2 == line[i][1])) {
				i++;
				count++;
				k = i-1;
			}
			
			if (count > max) {
				max = count;
				j = k;
			}
			count = 0;
		}
		
		return max;
	} 

}

class Points{
	double x;
	double y;
	
	Points(double x, double y){
		this .x = x;
		this.y = y;
	}
	
}

class Line{
	double yintercept;
	double m;
	double x1;
	double y1;
	double x2;
	double y2;
	
	
	Line(double yintercept, double m, double x1, double y1, double x2, double y2){
		this.yintercept = yintercept;
		this.m = m;
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
	}
}