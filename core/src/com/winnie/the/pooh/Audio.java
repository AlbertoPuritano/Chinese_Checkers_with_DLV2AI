package com.winnie.the.pooh;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

public class Audio {
    Sound move;
    Sound jump;
    Sound select;
    Sound win;
    Sound next;
    Sound no;
    Sound click;
    Sound clickMenu;
    Sound newGame;
    Sound list;
    Sound checkbox;
    Music menuMusic;
    public Audio()
    {
        move= Gdx.audio.newSound((Gdx.files.internal("sound/move.mp3")));
        jump= Gdx.audio.newSound(Gdx.files.internal("sound/jump.mp3"));
        select= Gdx.audio.newSound((Gdx.files.internal("sound/select.mp3")));
        win= Gdx.audio.newSound(Gdx.files.internal("sound/win.mp3"));
        next= Gdx.audio.newSound(Gdx.files.internal("sound/next.mp3"));
        no= Gdx.audio.newSound(Gdx.files.internal("sound/no.mp3"));
        click= Gdx.audio.newSound(Gdx.files.internal("sound/click.mp3"));
        clickMenu= Gdx.audio.newSound(Gdx.files.internal("sound/clickMenu.mp3"));
        newGame= Gdx.audio.newSound(Gdx.files.internal("sound/newGame.mp3"));
        list= Gdx.audio.newSound(Gdx.files.internal("sound/list.mp3"));
        checkbox= Gdx.audio.newSound(Gdx.files.internal("sound/checkbox.mp3"));
        menuMusic= Gdx.audio.newMusic(Gdx.files.internal("sound/menuMusic.mp3"));
        menuMusic.setVolume((float) 0.2);
        menuMusic.setLooping(true);
    }
    public void playMove()
    {
        move.play(0.1f);
    }
    public void playJump()
    {
        jump.play(0.3f);
    }
    public void playSelect()
    {
        select.play(0.2f);
    }
    public void playWin()
    {
        win.play();
    }
    public void playNext()
    {
        next.play(0.3f);
    }
    public void playNo()
    {
        no.play();
    }
    public void playClick()
    {
        click.play(0.3f);
    }
    public void playClickMenu()
    {
        clickMenu.play(0.2f);
    }
    public void playList()
    {
        list.play(0.2f);
    }
    public void playNewGame()
    {
        newGame.play();
    }
    public void playCheckbox()
    {
        checkbox.play(0.1f);
    }
    public void playMenuMusic()
    {
        menuMusic.play();
    }
    public void stopMenuMusic()
    {
        menuMusic.stop();
    }
    public void dispose()
    {
        move.dispose();
        jump.dispose();
        select.dispose();
        win.dispose();
        next.dispose();
        no.dispose();
        click.dispose();
        clickMenu.dispose();
        newGame.dispose();
        list.dispose();
        checkbox.dispose();
    }

}
