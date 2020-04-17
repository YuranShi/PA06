import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * A Sample represents a vector of doubles to be used in a clustering algorithm...
 * @author presenting
 *
 */

public class Sample {

	ArrayList<Double> sample;

	public Sample(double[] values) {
		this.sample = new ArrayList<Double>();
		for (int i=0; i<values.length; i++) {
			sample.add(values[i]);
		}
	}

	public double findDistance(Sample a) {
		double distance  = Math.sqrt((Math.pow(this.sample.get(0)-a.sample.get(0), 2)+
									  Math.pow(this.sample.get(1)-a.sample.get(1), 2)));
		return distance;
	}
	
    public String toString() {
    	return this.sample.get(0)+" "+this.sample.get(1);
    }
    
	public static void main(String[] args) throws FileNotFoundException {
		System.out.println("testing for the Sample class.");
		double[] p1 = {1d, 2d};
		double[] p2 = {0.1, 1.1};
		Sample s1 = new Sample(p1);
		Sample s2 = new Sample(p2);
		System.out.println("s1="+s1);
		System.out.println("s2="+s2);
		System.out.println(s1.findDistance(s2));
		
	}

}
