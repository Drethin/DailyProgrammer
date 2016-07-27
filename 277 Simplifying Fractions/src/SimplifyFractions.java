/**
 * Created by Alex on 27/07/2016.
 */
class SimplifyFractions {
    SimplifyFractions(){

        System.out.println(simplify("5 10"));

    }
    public static void main(String []args){
        new SimplifyFractions();
    }
    String simplify(String fraction){
        int left;
        int right;
        int gcd;
        String[] tmp = fraction.split(" ");
        left = Integer.parseInt(tmp[0]);
        right = Integer.parseInt(tmp[1]);
        gcd = gcd(right, left);
        return (left/gcd + " " + right/gcd);
    }
    int gcd(int a, int b){
        int c = a;
        while (c!=0){
            c=a%b;
            a=b;
            b=c;
        }
        return a;
    }
}
