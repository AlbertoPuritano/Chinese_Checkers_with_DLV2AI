package com.winnie.the.pooh;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WeakMoves {

    ArrayList<ArrayList<Integer>> deboli;
    int levelSize;

    public WeakMoves(int level) {
        deboli = new ArrayList<ArrayList<Integer>>();
        levelSize = level;
    }

    public void add(String answerSet) {
        ArrayList<Integer> tmp = new ArrayList<Integer>();
        for (int i = 0; i <= levelSize; i++)
            tmp.add(0);
        Matcher m = Pattern.compile("([0-9]+)@([0-9]+)+").matcher((CharSequence) answerSet);
        while (m.find()) {
            System.out.println("aggiungo costi: " + m.group(1) + "  " + m.group(2));
            tmp.set(Integer.parseInt(m.group(2)), Integer.parseInt(m.group(1)));
        }
        deboli.add(tmp);
    }

    public void stampa() {
        System.out.println(deboli);
    }

    private boolean migliore(ArrayList<Integer> answ1, ArrayList<Integer> answ2) {
        for (int i = levelSize; i > 0; i--) {
            if (answ2.get(i) < answ1.get(i))
                return false;
            else if (answ2.get(i) > answ1.get(i))
                return true;
        }
        return true;
    }

    public int bestCost() {// rest indice costi migliori (+basso al livello + alto)
        int bestAnsw = 0;
        for (int i = 0; i < deboli.size(); i++) {
            if (migliore(deboli.get(i), deboli.get(bestAnsw)))
                bestAnsw = i;
        }
        return bestAnsw; ////VA INSERITA CASUALITA'
    }
}

