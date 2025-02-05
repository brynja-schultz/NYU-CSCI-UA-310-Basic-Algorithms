public class FLT {
    public static void main(String[] args) {
        int ss = (args.length>0)? Integer.valueOf(args[0]) : 0;
        int nn = (args.length>1)? Integer.valueOf(args[1]) : 18;
        int mm = (args.length>2)? Integer.valueOf(args[2]) : 10; 

        if (mm == 1) {
            int count = countPseudoPrimes(nn);
            System.out.println("Number of pseudo primes in [" + nn + ", " + (2 * nn - 1) + ") = " + count);
        }
    }

    static boolean power(int b, int n) {
        int curr_mod = b;
        for (int i=2; i<=n; i++){curr_mod = (curr_mod * b)%n;} // starts at b^2, ends b^n
        if(curr_mod == b){return true;}
        else{return false;}
    }

    static boolean isPseudoPrime(int i) {
        if (i <= 1) {return false;}
        for (int j = 2; j<i; j++) {
            if (!power(j, i)) {return false;} // if power returns false, return false; else continue testing
        }
        return true;
    }

    static int countPseudoPrimes(int n) {
        int count = 0;
        for (int i = n; i <2*n; i++) {// counts pseudoprimes from n-(2n-1)
            if (isPseudoPrime(i)) {count++;}
        }
        return count;
    }
}
