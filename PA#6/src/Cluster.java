
/**
 * A cluster is a cluster point (which is itself a sample)
 * and a list of Samples (the one's closest to that sample point, ideally).
 * @author presenting
 *
 */
import java.util.ArrayList;
import java.util.Random;

public class Cluster {

	public Sample centroid;
	public ArrayList<Sample> clusterList;

	public Cluster() {
		double[] values = { 0, 0 };
		centroid = new Sample(values);
		clusterList = new ArrayList<Sample>();
	}

	public Sample AvgOfCluster() {
		if (clusterList.size() != 0) {
			double x = 0;
			double y = 0;
			for (int i = 0; i < clusterList.size(); i++) {
				x += clusterList.get(i).sample.get(0);
				y += clusterList.get(i).sample.get(1);
			}
			int size = clusterList.size();
			double[] a = { x / size, y / size };
			return new Sample(a);
		} else {
			double[] a = { 0, 0 };
			return new Sample(a);
		}
	}

	public void printCluster() {
		System.out.println("Centeroid: " + centroid.toString());
//		System.out.println("Cluster List: " + clusterList);
	}

}
