/**
 * Created by Alex on 27/07/2016.
 */
class SimplifyFractions {
    String[] equations;
    String[] fractions;
    boolean letters;
    SimplifyFractions(){

    }
    public static void main(String []args){
        SimplifyFractions sf = new SimplifyFractions();
        sf.parseInput(args);
        for(int i = 0; i < sf.fractions.length; i++){
            System.out.println(sf.simplify(sf.fractions[i]));
        }

    }
    private void parseInput(String[]args){
        letters = false;
        if(args[1].matches("\\D")){
            int numEquations = Integer.parseInt(args[0]);
            fractions = new String[(args.length -numEquations*2)/2];
            System.out.println(numEquations);
            letters = true;
            equations = new String[numEquations];
            int j = 0;
            for(int i = 1; i <= numEquations*2; i+=2){
                equations[j] = args[i]+" "+args[i+1];
                j++;
            }
            j=0;
            for(int i = (numEquations*2)+1; i < args.length; i+=2){
                fractions[j] = args[i] + " " + args[i+1];
                System.out.println(fractions[j]);
                j++;
            }
        }else{
            fractions = new String[(args.length/2)];
            //System.out.println(args.length/2);
            int j = 0;
            for(int i = 0; i < args.length; i+=2){
                fractions[j] = (args[i] + " " + args[i+1]);
                j++;
            }
        }

    }

    private String simplify(String fraction){
        int left;
        int right;
        int gcd;
        String[] tmp;

        if(letters){
            boolean loop = true;
            while(loop){
                loop = false;
                for (String s : equations) {
                    String[] eq = s.split(" ");
                    fraction = fraction.replaceAll(eq[0], eq[1]);
                }
                for (String s : equations) {
                    String[] eq = s.split(" ");
                    if(fraction.contains(eq[0]))loop = true;
                }
            }
            tmp = fraction.split(" ");
            for(String s1: tmp[0].split("")){
                if(tmp[1].contains(s1)){
                    tmp[0] = tmp[0].replaceFirst(s1, "");
                    tmp[1] = tmp[1].replaceFirst(s1, "");
                }
            }
            if(tmp[0].isEmpty())tmp[0] = "1";
            if(tmp[1].isEmpty())tmp[1] = "1";
            return(tmp[0] + " " + tmp[1]);
        }
        tmp = fraction.split(" ");
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
