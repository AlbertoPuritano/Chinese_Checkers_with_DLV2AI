package com.winnie.the.pooh;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WeakMoves {

    ArrayList<ArrayList<Integer>> deboli;
    int levelSize;
    ArrayList<Integer> best;
    boolean uguali;
    public WeakMoves(int level) {
        deboli = new ArrayList<ArrayList<Integer>>();
        levelSize = level;
        best=null;
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
        for (int i = levelSize; i > 0; i--)
        {
            if (answ2.get(i) < answ1.get(i))
                return false;
            else if (answ2.get(i) > answ1.get(i))
                return true;
        }
        uguali=true;
        return true;
    }

    public int bestCost() {// rest indice costi migliori (+basso al livello + alto)
        int bestAnsw = 0;
        for (int i = 0; i < deboli.size(); i++) {
            uguali=false;
            if (migliore(deboli.get(i), deboli.get(bestAnsw)))
            {
                if (uguali)
                {
                    if (best==null)
                        best= new ArrayList<Integer>();
                    if (!best.contains(bestAnsw))
                        best.add(bestAnsw);
                    if (!best.contains(i))
                        best.add(i);
                }
                bestAnsw = i;
            }
        }
        if (best!=null)
        {
            Random random= new Random();
            int r= random.nextInt(best.size());
            return r;
        }
        return bestAnsw;
    }
}

