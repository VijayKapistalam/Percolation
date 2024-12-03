import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import edu.princeton.cs.algs4.StdOut;

public class Percolation {
   private boolean[][]grid;
   private int count;
   private WeightedQuickUnionUF union;
   private int oneSide;
    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n){
    if(n <= 0){
        throw new IllegalArgumentException("n must be greater than or equal to 0");
    }
     grid = new boolean[n][n];
     union = new WeightedQuickUnionUF(n*n);
     oneSide = n;
     for(int i = 0; i < n; i++){
      for(int j=0; j<n; j++){
         grid[i][j] = false;
         if(i*n+j>=((n*n)-n)){
            union.union(n*n-1,i*n+j);
         }
      }
      if(i<n){
         union.union(0, i);
      }
     }

    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col){
      if (row < 1) {
         throw new IllegalArgumentException("Row must be (1,n)");
      }
      if(col < 1){
         throw new IllegalArgumentException("Col must be (1,n)");
      }
      if(row > oneSide){
        throw new IllegalArgumentException("Col must be (1,n)");
      }
      if(col > oneSide){
        throw new IllegalArgumentException("Col must be (1,n)");
      }
      int tempRow = row-1;
      int tempCol = col-1;
      if(oneSide == 1){
        count+=1;
        grid[tempRow][tempCol] = true;
        return;
      }
      if(!isOpen(row, col)){
         count+=1;
      }
      grid[tempRow][tempCol] = true;
      int v = tempRow*oneSide+tempCol;
      if(tempRow != oneSide-1 && tempRow != 0){
         if (isOpen(row + 1, col)) {
            union.union(v, v + oneSide);
         }
         if (isOpen(row - 1, col)) {
            union.union(v, v - oneSide);
         }
      }
      if (tempCol != oneSide - 1 && tempCol != 0) {
         if (isOpen(row, col +1 )) {
            union.union(v, v + 1);
         }
         if (isOpen(row, col - 1)) {
            union.union(v, v - 1);
         }
      }
      if (tempRow == oneSide - 1) {
         if (isOpen(row - 1, col)) {
            union.union(v, v - oneSide);
         }
      }
      if (tempCol == oneSide - 1) {
         if (isOpen(row, col - 1)) {
            union.union(v, v - 1);
         }
      }
      if (tempRow == 0) {
        if (isOpen(row + 1, col)) {
         union.union(v, v + oneSide);
        }
      }
      if(tempCol == 0) {
        if (isOpen(row, col + 1)) {
         union.union(v, v + 1);
        }
      }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col){
      if (row < 1) {
         throw new IllegalArgumentException("Row must be (1,n)");
      }
      if (col < 1) {
         throw new IllegalArgumentException("Col must be (1,n)");
      }
      if (row > oneSide) {
        throw new IllegalArgumentException("Col must be (1,n)");
      }
      if (col > oneSide) {
        throw new IllegalArgumentException("Col must be (1,n)");
      }
      int tempRow = row - 1;
      int tempCol = col - 1;
      if (grid[tempRow][tempCol] == true) {
         return true;
      }
      else {
         return false;
      }
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col){
      if (row < 1) {
         throw new IllegalArgumentException("Row must be (1,n)");
      }
      if (col < 1) {
         throw new IllegalArgumentException("Col must be (1,n)");
      }
      if (row > oneSide) {
        throw new IllegalArgumentException("Col must be (1,n)");
      }
      if (col > oneSide) {
        throw new IllegalArgumentException("Col must be (1,n)");
      }
      int tempRow = row - 1;
      int tempCol = col - 1;
      int v = tempRow * oneSide + tempCol;
      if(isOpen(row, col) && union.find(v) == union.find(0)){
        return true;
      }
      else {
        return false;
      }
    }

    // returns the number of open sites
    public int numberOfOpenSites(){
      return count;
    }

    // does the system percolate?
    public boolean percolates(){
        if (oneSide == 1 && isOpen(1,1)) {
            return true;
        }
        if ((union.find(0) == union.find(oneSide*oneSide-1)) && oneSide > 1) {
         return true;
        }
        else {
         return false;
        }
    }

   public static void main(String []args) {
      Percolation perc = new Percolation(3);
      StdOut.println(perc.isFull(1, 1));
      perc.open(1,1);
      perc.open(2,2);
      StdOut.println(perc.numberOfOpenSites());
   }
}