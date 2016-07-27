import java.util.Hashtable;

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
    Hashtable<String, Integer> coinWeights;
    FakeCoinLogic(String[] input){
        coinList = "";
        for (String s1: input) {
            line = s1.split(" ");
            left = line[0];
            right = line[1];
            lean = line[2];

            if(lean.equals("left")){
                updateCoins(left, 2);
                updateCoins(right, 1);
            }else if(lean.equals("right")){
                updateCoins(left, 1);
                updateCoins(right, 2);
            }else{
                updateCoins(left+right, 0);
            }

        }
    }
    void updateCoins(String coins, int weight){
        for(String s: coins.split("")){
            if(!coinList.contains(s)) {
                coinList += s;
                coinWeights.put(s, weight);
            }else{
                coinWeights.replace(s, coinWeights.get(s)+weight);
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
