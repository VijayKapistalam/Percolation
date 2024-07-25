import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class PercolationStats {
    // perform independent trials on an n-by-n grid
    public static int index1;
    public static int index2;
    
    public PercolationStats(int n, int trials){
        if(n <= 0 || trials <= 0){
            throw new IllegalArgumentException("invalid input");
        }
        Percolation p = new Percolation(n);
        for(int i = 0; i<trials; i++){
            index1 = StdRandom.uniformInt(n);
            index2 = StdRandom.uniformInt(n);
            p.open(index1, index2);
            if(p.percolates()){
                System.out.println("percolates");
                return;
            }
        }
    }

    // sample mean of percolation threshold
    public double mean(){
        return -1;
    }

    // sample standard deviation of percolation threshold
    public double stddev(){
        return -1;
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo(){
        return -1;
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi(){
        return -1;
    }

   // test client (see below)
    public static void main(String[] args){
        PercolationStats perc = new PercolationStats(3, 100);
    }
}
