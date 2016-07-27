import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Alex on 27/07/2016.
 */
class FakeCoinLogic {
    String[]line;
    String left;
    String right;
    String lean;
    ArrayList<ValidCombo> validCombos;
    int[][]leftCombos;
    int[][]rightCombos;

    FakeCoinLogic(String[] input) {
        validCombos = new ArrayList<ValidCombo>();
        for (String s1 : input) {
            line = s1.split(" ");
            left = line[0];
            right = line[1];
            lean = line[2];
            addValidCombos();
            for (ValidCombo vc : validCombos) {
                System.out.println("ValidCombo:");
                for (Coin c : vc.getCombo()) {
                    System.out.print(c + " ");
                }
                System.out.println();
            }
        }
    }
    public static void main(String[]args){
        new FakeCoinLogic(new String[]{"a c equal"});
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
        ArrayList<Coin> getCombo(){
            return combo;
        }

    }
    private class Coin{
        int weight;
        String label;
        Coin(int weight, String label){
            this.weight=weight;
            this.label = label;
        }
        @Override
        public String toString(){
            return (label + ": " + weight);
            }
        }
    }

