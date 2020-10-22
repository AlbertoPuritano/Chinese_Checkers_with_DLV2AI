package com.winnie.the.pooh;

import com.badlogic.gdx.math.Circle;

import it.unical.mat.embasp.languages.Id;

public class Piece extends Circle {
    public int player;
    public int originPlayer;
    public Piece(float x, float y, float radius) {
        super(x, y, radius);
        player=0;
        originPlayer=-1;
    }
    public void setPlayer(int player)
    {
        this.player=player;
    }
    public int getPlayer()
    {
        return player;
    }
    public void setOriginPlayer(int n)
    {
        originPlayer=n;
    }
    public int getOriginPlayer()
    {
        return originPlayer;
    }

}
