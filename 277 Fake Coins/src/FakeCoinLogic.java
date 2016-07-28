import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Alex on 27/07/2016.
 */
public class FakeCoinLogic {
    String[]input;
    String equalCoins;
    String lighterCoins;
    boolean inconsistent;

    public FakeCoinLogic(String[]input){
        this.input = input;
        equalCoins = "";
        lighterCoins = "";
        inconsistent = false;
        parseInput();
    }
    public String getOutput(){
        if(lighterCoins.isEmpty() && !inconsistent)return("No fake coin detected");
        if(lighterCoins.length()==1)return(lighterCoins+" is fake");
        else return("Data is inconsistent");
    }
    private void parseInput(){
        for(String inp:input){
            String[] tmp = inp.split(" ");
            if(tmp[2].equals("equal"))for(String s: (tmp[0]+tmp[1]).split("")) addToEqual(s);
            else if(tmp[2].equals("right")){
                inconsistent = true;
                for(String s: (tmp[0]).split("")) addToLighter(s);
            } else{
                inconsistent = true;
                for(String s: (tmp[1]).split("")) addToLighter(s);
            }
        }
    }
    private void addToEqual(String s){
        if(lighterCoins.contains(s)){
            lighterCoins = lighterCoins.replaceAll(s, "");
        }if(!equalCoins.contains(s))equalCoins+=s;
    }
    private void addToLighter(String s){
        if(!equalCoins.contains(s) && !lighterCoins.contains(s))lighterCoins+=s;
    }
    public static void main(String[]args){
        FakeCoinLogic fcn = new FakeCoinLogic(new String[]{
            "abc efg equal",
                "a e right"
        });
        System.out.println(fcn.getOutput());
    }
}

