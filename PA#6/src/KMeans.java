import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.Random;

public class KMeans {

	public static ArrayList<Sample> originalData = new ArrayList<Sample>();
	public static int numClusters;
	public static Cluster[] clusters;
	public static String filename;

	public static void main(String[] args) throws FileNotFoundException {

		Scanner Sc = new Scanner(System.in);
		System.out.print("Filename: ");
		filename = Sc.nextLine();
		initializeOriginalData(filename);
		System.out.print("Number of clusters: ");
		numClusters = Sc.nextInt();

		run(100);
	}
	/**
	 *  This program initializes original data, finds random points as centroids,
	 *  processes to find k means given number of times, and finally prints centroids.
	 * @param times -- running times, 100 by default
	 */
	public static void run(int times) {
		initializeOriginalData(filename);
		SetRandomPoints();
		for (int i = 1; i <= times; i++) {
			findKMeans();
			for (int j = 0; j < numClusters && i<times ; j++)
				clusters[j].clusterList.clear();;
		}
		replaceCentroid();
		for (int i = 0; i < numClusters; i++)
			clusters[i].printCluster();
	}

	public static void initializeOriginalData(String filename) {
		try {
			Scanner fileSc = new Scanner(new File(filename));
			while (fileSc.hasNextDouble()) {
				double x = fileSc.nextDouble();
				double y = fileSc.nextDouble();
				double[] p = { x, y };
				originalData.add(new Sample(p));
			}
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred");
			e.printStackTrace();
		}
		clusters = new Cluster[numClusters];
	}

	/**
	 * Assign a random centriod to each cluster.
	 */
	public static void SetRandomPoints() {
		for (int i = 0; i < numClusters; i++) {
			Random rand = new Random();
			int num = rand.nextInt(originalData.size());
			clusters[i] = new Cluster();
			clusters[i].centroid = originalData.get(num);
		}
	}

	/**
	 * For each point, assign it to the closest cluster; For each cluster, find its
	 * new centroid.
	 */
	public static void findKMeans() {
		for (Sample a : originalData)
			addToClosestCluster(clusters, a);
		for (int i = 0; i < clusters.length; i++)
			clusters[i].centroid = clusters[i].AvgOfCluster();
	}

	/**
	 * Given a sample s and a cluster list, this method adds s to the closest
	 * cluster.
	 * @return
	 */
	public static void addToClosestCluster(Cluster[] clusters, Sample s) {
		double distance = clusters[0].centroid.findDistance(s);
		int index = 0;
		for(int i=1; i<clusters.length; i++) {
			if(clusters[i].centroid.findDistance(s)<distance) {
				distance = clusters[i].centroid.findDistance(s);
				index = i;
			}
		}
		clusters[index].clusterList.add(s);
	}
	
	/**
	 *  This method replaces calculated centroids in array clusters with closest points.
	 */
	public static void replaceCentroid()
	{
		for(Cluster c : clusters) {
			double distance = c.centroid.findDistance(c.clusterList.get(0));
			int index = 0;
			for(int i=1; i<c.clusterList.size(); i++) {
				if(c.centroid.findDistance(c.clusterList.get(i))<distance) {
					distance = c.centroid.findDistance(c.clusterList.get(i));
					index = i;
				}
			}
			c.centroid = c.clusterList.get(index);
		}
	}
}
