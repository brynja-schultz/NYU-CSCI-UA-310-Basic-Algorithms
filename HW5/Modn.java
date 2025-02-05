public class Modn {
    public static void main(String[] args) {
    
        int ss = (args.length>0)? Integer.valueOf(args[0]) : 0;
        int nn = (args.length>1)? Integer.valueOf(args[1]) : 10;
        int mm = (args.length>2)? Integer.valueOf(args[2]) : 1; // either 1 with stars or 0 without stars

        int[][] table = new int[nn][nn]; // 2D arrays stores whole multiplication table --> only print bottom half
        int[] inverses = new int[nn];

        for (int i = 2; i < nn; i++) { // runs for 2 - (nn-1)
            for (int j = 2; j < nn; j++) { //runs for 2 - (nn-1)
                table[i][j] = (i * j) % nn;
                if (table[i][j] == 1){
                    inverses[i] = 1;
                    inverses[j] = 1;
                }
            }
        }
        System.out.print("       ");
        for (int i = 2; i < nn; i++) { // prints start labels for columns
            if(i>9){System.out.print(i + " ");}
            else {System.out.print(i + "  ");}
        }

        // Print the upper triangular matrix of the table
        System.out.print("\n  ");
        for (int i = 2; i < nn; i++){
            System.out.print("====");
        }
        System.out.print("\n");
        for (int i = 2; i < nn; i++) {
            if(i>9){System.out.print(i + ":  ");}
            else{System.out.print(i + ":   ");}
            for (int j = 2; j < nn; j++) {
                if (j > i) {
                    System.out.print(" ");
                } 
                else {
                    System.out.printf("%3d",table[i][j]);
                    //System.out.print(table[i][j] + "   ");
                }
            }
            System.out.println();
        }
        System.out.print("\n  ");
        for (int i = 2; i < nn; i++){
            System.out.print("====");
        }
        System.out.println();
        System.out.print("       ");
        for (int i = 2; i < nn; i++) { // prints end labels for columns
            if(i>9){System.out.print(i + " ");}
            else {System.out.print(i + "  ");}
        }
        System.out.println();
        System.out.print("       ");

        if (mm == 1){ //prints stars for #s with inverses
            for (int i = 2; i<nn; i++){
                if(find_GCD(nn, i) == 1){ System.out.print("*  ");}// inverse exists
                else{System.out.print("   ");}
            }
        }
    }
    static int find_GCD(int a, int b){
        if(b==0){return a;}
        else{ return find_GCD(b, a%b);}
    }
}