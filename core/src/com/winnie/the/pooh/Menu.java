package com.winnie.the.pooh;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
public class Menu {
    private Stage stage;
    public FreeTypeFontGenerator fontGenerator;
    public BitmapFont textFont;
    public Label.LabelStyle labelStyle;
    public Label dialogText;
    public Dialog dialog;
    public TextButton button;
    public Label won;
    final SelectBox<String> selectBox;
    public Boolean[] AI;
    public Audio audio;
    public Menu(final Stage stage, int lastWinner, final Boolean[] AI, final Audio audio)
    {
        Gdx.input.setCatchKey(Input.Keys.BACK, false);
        this.audio= audio;
        audio.playMenuMusic();
        Game.isMenu=true;
        this.stage=stage;
        dialog=new Dialog("",Game.skin);
        dialog.setSize(1150, 800);
        dialog.setPosition(200,160);
        dialog.setMovable(false);
        dialog.setTouchable(Touchable.disabled);
        fontGenerator=  new FreeTypeFontGenerator(Gdx.files.internal("board/FORTSSH_.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 35;
        textFont= fontGenerator.generateFont(parameter);
        labelStyle = new Label.LabelStyle();
        labelStyle.font = textFont;
        dialogText= new Label("Choose the number of players:", labelStyle);
        dialogText.setPosition(595-170,890);
        dialogText.setColor(Color.LIGHT_GRAY);
        selectBox=new SelectBox<String>(Game.skin);
        FreeTypeFontGenerator.FreeTypeFontParameter parameter1 = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter1.size = 25;
        BitmapFont textFont1= fontGenerator.generateFont(parameter1);
        SelectBox.SelectBoxStyle ss= selectBox.getStyle();
        ss.font= textFont1;
        List.ListStyle ls= selectBox.getList().getStyle();
        ls.font=textFont1;
        selectBox.getList().setStyle(ls);
        selectBox.setItems("Two Players","Three Players","Four Players","Five Players","Six Players");
        selectBox.setSize(350,50);
        selectBox.setPosition(800-170,810);
        button= new TextButton("PLAY!", Game.skin);
        button.setPosition(870-155,665);
        button.setSize(160,120);
        button.getLabel().setFontScale(2,2);
        stage.addActor(dialog);
        stage.addActor(button);
        stage.addActor(selectBox);
        stage.addActor(dialogText);
        if (lastWinner>-1)
        {
            won = new Label("Player "+ lastWinner + " has won the game!", labelStyle);
            switch (lastWinner)
            {
                case 1:
                    won.setColor(Color.GREEN);
                    break;
                case 2:
                    won.setColor(Color.ORANGE);
                    break;
                case 3:
                    won.setColor(Color.YELLOW);
                    break;
                case 4:
                    won.setColor(Color.RED);
                    break;
                case 5:
                    won.setColor(Color.BLUE);
                    break;
                case 6:
                    won.setColor(Color.CYAN);
                    break;
            }
            won.setPosition(630-170,600);
            stage.addActor(won);
        }
        this.AI=AI;
            for (int i = 1; i < 7; i++)
            {
                Label player = new Label("P" + i, Game.skin);
                player.setScale(6);
                player.setColor(Color.BLACK);
                player.setPosition(1020 + (i * 65) - 170, 760);
                final CheckBox checkbox = new CheckBox("AI", Game.skin);
                checkbox.setPosition(1015 + (i * 65) - 170, 720);
                final int finalI = i;
                checkbox.addListener(new ChangeListener() {
                    @Override
                    public void changed(ChangeEvent event, Actor actor) {
                        audio.playCheckbox();
                        AI[finalI] = checkbox.isChecked();
                    }
                });
                if (i > 2) {
                    checkbox.setVisible(false);
                    player.setVisible(false);
                }
                if (Gdx.app.getType()== Application.ApplicationType.Android)
                {
                    player.setTouchable(Touchable.disabled);
                    checkbox.setTouchable(Touchable.disabled);
                }
                stage.addActor(player);
                stage.addActor(checkbox);
            }
            Game.numPlayers = 2;
            selectBox.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    audio.playList();
                    switch (selectBox.getSelected()) {
                        case "Two Players":
                            Game.numPlayers = 2;
                            stage.getActors().get(8).setVisible(false);
                            stage.getActors().get(9).setVisible(false);
                            stage.getActors().get(10).setVisible(false);
                            stage.getActors().get(11).setVisible(false);
                            stage.getActors().get(12).setVisible(false);
                            stage.getActors().get(13).setVisible(false);
                            stage.getActors().get(14).setVisible(false);
                            stage.getActors().get(15).setVisible(false);
                            break;
                        case "Three Players":
                            Game.numPlayers = 3;
                            stage.getActors().get(8).setVisible(true);
                            stage.getActors().get(9).setVisible(true);
                            stage.getActors().get(10).setVisible(false);
                            stage.getActors().get(11).setVisible(false);
                            stage.getActors().get(12).setVisible(false);
                            stage.getActors().get(13).setVisible(false);
                            stage.getActors().get(14).setVisible(false);
                            stage.getActors().get(15).setVisible(false);
                            break;
                        case "Four Players":
                            stage.getActors().get(8).setVisible(true);
                            stage.getActors().get(9).setVisible(true);
                            stage.getActors().get(10).setVisible(true);
                            stage.getActors().get(11).setVisible(true);
                            stage.getActors().get(12).setVisible(false);
                            stage.getActors().get(13).setVisible(false);
                            stage.getActors().get(14).setVisible(false);
                            stage.getActors().get(15).setVisible(false);
                            Game.numPlayers = 4;
                            break;
                        case "Five Players":
                            stage.getActors().get(8).setVisible(true);
                            stage.getActors().get(9).setVisible(true);
                            stage.getActors().get(10).setVisible(true);
                            stage.getActors().get(11).setVisible(true);
                            stage.getActors().get(12).setVisible(true);
                            stage.getActors().get(13).setVisible(true);
                            stage.getActors().get(14).setVisible(false);
                            stage.getActors().get(15).setVisible(false);
                            Game.numPlayers = 5;
                            break;
                        case "Six Players":
                            stage.getActors().get(8).setVisible(true);
                            stage.getActors().get(9).setVisible(true);
                            stage.getActors().get(10).setVisible(true);
                            stage.getActors().get(11).setVisible(true);
                            stage.getActors().get(12).setVisible(true);
                            stage.getActors().get(13).setVisible(true);
                            stage.getActors().get(14).setVisible(true);
                            stage.getActors().get(15).setVisible(true);
                            Game.numPlayers = 6;
                            break;
                    }
                }
            });
        selectBox.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                audio.playClickMenu();
            }
        });
        button.addListener( new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                audio.playClickMenu();
                Game.isMenu=false;
                audio.stopMenuMusic();
            }
        } );
        Gdx.input.setInputProcessor(stage);
    }
    public void draw()
    {
        stage.act();
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.draw();
    }
    public void dispose()
    {
        fontGenerator.dispose();
        textFont.dispose();
        stage.dispose();
    }
}
