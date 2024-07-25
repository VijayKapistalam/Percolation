import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import java.lang.Math;

public class Percolation{
       
        public static int[][]grid;
        public static int[][]vgrid;
        public static int v;
        public static int length;
        public static int l;
        public static int openCount;
        public static WeightedQuickUnionUF weighted;
        public static boolean ans;
        public static int[]check;


        // creates n-by-n grid, with all sites initially blocked
        public Percolation(int n){
            ans = false;
            openCount = 0;
            grid = new int[n][n];
            vgrid = new int[n][n];
            check = new int[n*n];
            for(int row= 0; row<n; row++){
                for(int col = 0; col<n; col++){
                    grid[row][col] = 0; //closed; open = 1
                    vgrid[row][col] = -1;
                    check[l] = l;
                    l += 1;
                }
            }
            length = (int)Math.sqrt(l);
            weighted = new WeightedQuickUnionUF(l);
        }
        // opens the site (row, col) if it is not open already
        public static void open(int row, int col){
            if (row < 0 || col < 0 || row>length || col>length) {
                throw new IllegalArgumentException("invalid input");
            }

            if(length == 1){
                ans = true;
            }

            openCount = openCount+1;
            grid[row][col] = 1;

            v = row+col + (length-1)*(row);
            vgrid[row][col] = v;
            // System.out.println(v);


            if(row != 0){
                if(isOpen(row-1, col)){
                    weighted.union(vgrid[row][col], vgrid[row-1][col]);
                    if(vgrid[row][col] < vgrid[row-1][col] && vgrid[row][col] < check[weighted.find(vgrid[row][col])-1]){
                        check[weighted.find(vgrid[row][col])-1] = vgrid[row][col];
                    }
                    else if(vgrid[row][col] > vgrid[row-1][col] && vgrid[row-1][col] < check[weighted.find(vgrid[row][col])-1]){
                        check[weighted.find(vgrid[row][col])-1] = vgrid[row-1][col];
                    }
                }
            }
            if(col != 0){
                if(isOpen(row, col-1)){
                    weighted.union(vgrid[row][col], vgrid[row][col-1]);
                    if(vgrid[row][col] < vgrid[row][col-1] && vgrid[row][col] < check[weighted.find(vgrid[row][col])-1]){
                        check[weighted.find(vgrid[row][col])-1] = vgrid[row][col];
                    }
                    else if(vgrid[row][col] > vgrid[row][col-1] && vgrid[row][col-1] < check[weighted.find(vgrid[row][col])-1]){
                        check[weighted.find(vgrid[row][col])-1] = vgrid[row][col-1];
                    }
                }
            }
            if(row != length-1){
                if(isOpen(row+1, col)){
                    weighted.union(vgrid[row][col], vgrid[row+1][col]);
                }
            }
            if(col != length-1){
                if(isOpen(row, col+1)){
                    weighted.union(vgrid[row][col], vgrid[row][col+1]);
                    if(vgrid[row][col] < vgrid[row][col+1] && vgrid[row][col] < check[weighted.find(vgrid[row][col])-1]){
                        check[weighted.find(vgrid[row][col])-1] = vgrid[row][col];
                    }
                    else if(vgrid[row][col] > vgrid[row][col+1] && vgrid[row][col+1] < check[weighted.find(vgrid[row][col])-1]){
                        check[weighted.find(vgrid[row][col])-1] = vgrid[row][col+1];
                    }
                }
            }

        //possible answer
            if(length == 1 && row == 0 && col == 0){
                ans = true;
            }
            else if(vgrid[row][col] >= length*(length-1) && check[weighted.find(vgrid[row][col])-1] < length && length!=1){
                ans = true;
            }
        }
    
        // is the site (row, col) open?
        public static boolean isOpen(int row, int col){
            if (row < 0 || col < 0 || row>length || col>length) {
                throw new IllegalArgumentException("invalid input");
            }
            
            if(grid[row][col] == 1){
                return true;
            }
            else{
                return false;
            }
        }
    
        // is the site (row, col) full?
        public static boolean isFull(int row, int col){
            if (row < 0 || col < 0 || row>length || col>length) {
                throw new IllegalArgumentException("invalid input");
            }

            if(grid[row][col] == 0){
                return true;
            }
            else{
                return false;
            }
        }
    
        // returns the number of open sites
        public static int numberOfOpenSites(){
            return openCount;
        }
    
        // does the system percolate?
        public static boolean percolates(){
            return ans;
        }
    
        // test client (optional)
        public static void main(String[] args){
            Percolation p4 = new Percolation(3);
            p4.open(0, 1);
            p4.open(1, 1);
            p4.open(2, 1);
            System.out.println(p4.percolates()); // Should return true
            System.out.println(p4.numberOfOpenSites());
            
            
        }
}