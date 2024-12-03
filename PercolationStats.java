import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class PercolationStats {

   private double[] t;
   private double size;
   private double numT;
    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials){
      t = new double[trials];
      size = (double)(n*n);
      double point = 0;
      numT = (double)trials;
      for(int i = 0; i<trials; i++){
         Percolation perc = new Percolation(n);
         while(!perc.percolates()){
            int v1 = StdRandom.uniformInt(1,n+1);
            int v2 = StdRandom.uniformInt(1,n+1);
            perc.open(v1, v2);
            point = perc.numberOfOpenSites()/size;
         }
         t[i] = point;
      }

    }

    // sample mean of percolation threshold
    public double mean(){
      return StdStats.mean(t);
    }

    // sample standard deviation of percolation threshold
    public double stddev(){
      return StdStats.stddev(t);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo(){
      return StdStats.mean(t)-(1.96 * StdStats.stddev(t))/Math.sqrt(numT);
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi(){
      return StdStats.mean(t)+(1.96 * StdStats.stddev(t))/Math.sqrt(numT);
    }

    public static void main(String []args) {
      int n = StdIn.readInt();
      int trials = StdIn.readInt();
      PercolationStats ps = new PercolationStats(n, trials);
      StdOut.println(ps.mean());
      StdOut.println(ps.stddev());
      StdOut.println("[" + ps.confidenceLo() + ", " + ps.confidenceHi() + "]");
    }
 }