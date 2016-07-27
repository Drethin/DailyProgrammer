/**
 * Created by Alex on 27/07/2016.
 */
class SimplifyFractions {
    public static void main(String []args){
        for(int i = 0; i < args.length; i+=2){
            System.out.println(simplify(args[i] + " " + args[i+1]));
        }
    }
    static String simplify(String fraction){
        int left;
        int right;
        int gcd;
        String[] tmp = fraction.split(" ");
        left = Integer.parseInt(tmp[0]);
        right = Integer.parseInt(tmp[1]);
        gcd = gcd(right, left);
        return (left/gcd + " " + right/gcd);
    }
    private static int gcd(int a, int b){
        int c = a;
        while (c!=0){
            c=a%b;
            a=b;
            b=c;
        }
        return a;
    }
}
