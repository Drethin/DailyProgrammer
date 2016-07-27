import java.util.ArrayList;

/**
 * Created by Alex on 27/07/2016.
 */
class FakeCoinLogic {
    String[]line;
    String left;
    String right;
    String lean;
    String coinList;
    String equalCoins;
    ArrayList<ValidCombo> validCombos;
    int[][]leftCombos;
    int[][]rightCombos;

    FakeCoinLogic(String[] input){
        validCombos = new ArrayList<ValidCombo>();
        coinList = "";
        for (String s1: input) {
            line = s1.split(" ");
            left = line[0];
            right = line[1];
            lean = line[2];
            int numCoins = left.length() + right.length();
            int[][] combos = new int[numCoins*numCoins][numCoins];
            for(int i = 0; i < numCoins*numCoins; i++){
                for(int j = 0; j < numCoins; j++){
                    combos[i][j] = (i & (1 << j));
                }
            }
        }
    }
    public static void main(String[]args){
        int [][]combos = new int[4][2];
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 2; j++){
                combos[i][j] = (i >> j)%2;
                System.out.print(combos[i][j]);
            }
            System.out.println();
        }
    }
    private int[][] combos(String line){
        int [][]combos = new int[line.length()*line.length()][line.length()];
        for(int i = 0; i < line.length()*line.length(); i++){
            for(int j = 0; j < line.length(); j++){
                combos[i][j] = (i >> j)%2;
            }
        }
        return combos;
    }
    private void addValidCombos(){
        rightCombos = combos(right);
        leftCombos = combos(left);
        for(int[]i:leftCombos){
            for(int[]j:rightCombos){
                if(lean.equals("equal")) {
                    if (sumArray(i) == sumArray(j)) {
                        validCombos.add(new ValidCombo(matchCoins(i, left), matchCoins(j, right)));
                    }
                }else if(lean.equals("left")){
                    if (sumArray(i) > sumArray(j)) {
                        validCombos.add(new ValidCombo(matchCoins(i, left), matchCoins(j, right)));
                    }
                }else if(lean.equals("right")){
                    if (sumArray(i) < sumArray(j)) {
                        validCombos.add(new ValidCombo(matchCoins(i, left), matchCoins(j, right)));
                    }
                }
            }
        }
    }
    private Coin[] matchCoins(int[]combo, String coins){
        Coin[]matchedCoins = new Coin[combo.length];
        String[]coinArray = coins.split("");
        for(int i = 0; i < combo.length; i++){
            matchedCoins[i] = new Coin(combo[i], coinArray[i]);
        }
        return matchedCoins;
    }

    private int sumArray(int[]array){
        int sum = 0;
        for(int i: array){
            sum += i;
        }
        return sum;
    }
    private class ValidCombo{
        ArrayList<Coin> combo;
        ValidCombo(Coin[]sideA, Coin[]sideB){
            combo = new ArrayList<Coin>();
            for(Coin c:sideA){
                combo.add(c);
            }
            for(Coin c:sideB){
                combo.add(c);
            }
        }

    }
    private class Coin{
        int weight;
        String label;
        Coin(int weight, String label){
            this.weight=weight;
            this.label = label;
        }
    }
}
