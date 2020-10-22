package com.winnie.the.pooh;



import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;

import java.util.ArrayList;

public class Board {
    public ArrayList<Piece> pieces;
    public TextureRegion background;
    public Texture player1;
    public Texture player2;
    public Texture player3;
    public Texture player4;
    public Texture player5;
    public Texture player6;
    public Stage stage;
    public TextButton endTurn;
    public Label playerTurnText1;
    public Label playerTurnText2;
    public FreeTypeFontGenerator fontGenerator;
    public FreeTypeFontGenerator fontGenerator2;
    public BitmapFont textFont;
    public BitmapFont textFont1;
    public BitmapFont textFont2;
    public Label.LabelStyle labelStyle;
    public Label.LabelStyle labelStyle1;
    public Label.LabelStyle labelStyle2;
    public TextButton backToMenu;
    public Dialog youSure;
    public Audio audio;
    public TextButton yesButton;
    public TextButton noButton;
    public Board(Stage stage, final Audio audio)
    {
        this.stage= stage;
        this.audio=audio;
        background = new TextureRegion(new Texture("board/board.png"), 0, 0, Game.width, Game.height);
        player1= new Texture("board/green.png");
        player2= new Texture("board/orange.png");
        player3= new Texture("board/yellow.png");
        player4= new Texture("board/red.png");
        player5= new Texture("board/blue.png");
        player6= new Texture("board/light_blue.png");
        pieces= new ArrayList<Piece>();
        build();
        fontGenerator=  new FreeTypeFontGenerator(Gdx.files.internal("board/FORTSSH_.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 40;
        textFont= fontGenerator.generateFont(parameter);
        labelStyle = new Label.LabelStyle();
        labelStyle.font = textFont;
        endTurn = new TextButton("", Game.skin);
        endTurn.setPosition(30,950);
        endTurn.setSize(350,150);
        endTurn.setLabel(new Label("END TURN", labelStyle));
        endTurn.getLabel().setColor(Color.BLACK);
        stage.addActor(endTurn);
        endTurn.setVisible(false);
        fontGenerator2=  new FreeTypeFontGenerator(Gdx.files.internal("board/FORTSSH_.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter2 = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter2.size = 110;
        textFont1= fontGenerator.generateFont(parameter);
        textFont2= fontGenerator.generateFont(parameter2);
        labelStyle1 = new Label.LabelStyle();
        labelStyle1.font = textFont1;
        labelStyle2 = new Label.LabelStyle();
        labelStyle2.font = textFont2;
        playerTurnText1= new Label("Player",labelStyle1);
        playerTurnText1.setPosition(1450,150);
        playerTurnText1.setColor(Color.WHITE);
        playerTurnText2= new Label("",labelStyle2);
        playerTurnText2.setPosition(1515,80);
        playerTurnText2.setColor(Color.GREEN);
        stage.addActor(playerTurnText1);
        stage.addActor(playerTurnText2);
        backToMenu = new TextButton("", Game.skin);
        backToMenu.setPosition(1225,1000);
        backToMenu.setSize(420,135);
        backToMenu.setLabel(new Label("BACK TO MENU", labelStyle));
        backToMenu.getLabel().setColor(Color.BLACK);
        backToMenu.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                audio.playList();
                youSure.setVisible(true);
            }
        });
        youSure= new Dialog("\nAre you \nsure?",Game.skin);
        youSure.getTitleLabel().setStyle(labelStyle);
        yesButton = new TextButton("YES", Game.skin, "default");
        yesButton.getLabel().setFontScale((float) 1.5);
        noButton = new TextButton("NO", Game.skin, "default");
        noButton.getLabel().setFontScale((float)1.5);
        youSure.getTitleTable().add(yesButton).size(100, 100).padLeft(-370).padBottom(-300);
        youSure.getTitleTable().add(noButton).size(100, 100).padLeft(-150).padBottom(-300);
        yesButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                audio.playClickMenu();
                Game.exitMenu=true;
            }
        });
        noButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                audio.playClick();
                youSure.setVisible(false);
            }
        });
        youSure.setVisible(false);
        youSure.setSize(400,400);
        youSure.setPosition(Game.width/2,Game.height/2,Align.center);
        youSure.setMovable(true);
        stage.addActor(backToMenu);
        stage.addActor(youSure);
        Gdx.input.setInputProcessor(stage);
    }
    public void addPlayer(int p)
    {
        switch (p)
        {
            case 1:
                for (int i=0;i<10;i++)
                {
                    getPiece(i).setPlayer(1);
                    getPiece(i).setOriginPlayer(1);
                    getPiece(10).setOriginPlayer(3);
                    getPiece(11).setOriginPlayer(3);
                    getPiece(12).setOriginPlayer(3);
                    getPiece(13).setOriginPlayer(3);
                    getPiece(23).setOriginPlayer(3);
                    getPiece(24).setOriginPlayer(3);
                    getPiece(25).setOriginPlayer(3);
                    getPiece(35).setOriginPlayer(3);
                    getPiece(36).setOriginPlayer(3);
                    getPiece(46).setOriginPlayer(3);
                    getPiece(74).setOriginPlayer(4);
                    getPiece(84).setOriginPlayer(4);
                    getPiece(85).setOriginPlayer(4);
                    getPiece(95).setOriginPlayer(4);
                    getPiece(96).setOriginPlayer(4);
                    getPiece(97).setOriginPlayer(4);
                    getPiece(107).setOriginPlayer(4);
                    getPiece(108).setOriginPlayer(4);
                    getPiece(109).setOriginPlayer(4);
                    getPiece(110).setOriginPlayer(4);
                    getPiece(19).setOriginPlayer(5);
                    getPiece(20).setOriginPlayer(5);
                    getPiece(21).setOriginPlayer(5);
                    getPiece(22).setOriginPlayer(5);
                    getPiece(32).setOriginPlayer(5);
                    getPiece(33).setOriginPlayer(5);
                    getPiece(34).setOriginPlayer(5);
                    getPiece(44).setOriginPlayer(5);
                    getPiece(45).setOriginPlayer(5);
                    getPiece(55).setOriginPlayer(5);
                    getPiece(65).setOriginPlayer(6);
                    getPiece(75).setOriginPlayer(6);
                    getPiece(76).setOriginPlayer(6);
                    getPiece(86).setOriginPlayer(6);
                    getPiece(87).setOriginPlayer(6);
                    getPiece(88).setOriginPlayer(6);
                    getPiece(98).setOriginPlayer(6);
                    getPiece(99).setOriginPlayer(6);
                    getPiece(100).setOriginPlayer(6);
                    getPiece(101).setOriginPlayer(6);
                }
                break;
            case 2:
                for (int i=111;i<121;i++)
                {
                    getPiece(i).setPlayer(2);
                    getPiece(i).setOriginPlayer(2);
                    getPiece(10).setOriginPlayer(3);
                    getPiece(11).setOriginPlayer(3);
                    getPiece(12).setOriginPlayer(3);
                    getPiece(13).setOriginPlayer(3);
                    getPiece(23).setOriginPlayer(3);
                    getPiece(24).setOriginPlayer(3);
                    getPiece(25).setOriginPlayer(3);
                    getPiece(35).setOriginPlayer(3);
                    getPiece(36).setOriginPlayer(3);
                    getPiece(46).setOriginPlayer(3);
                    getPiece(74).setOriginPlayer(4);
                    getPiece(84).setOriginPlayer(4);
                    getPiece(85).setOriginPlayer(4);
                    getPiece(95).setOriginPlayer(4);
                    getPiece(96).setOriginPlayer(4);
                    getPiece(97).setOriginPlayer(4);
                    getPiece(107).setOriginPlayer(4);
                    getPiece(108).setOriginPlayer(4);
                    getPiece(109).setOriginPlayer(4);
                    getPiece(110).setOriginPlayer(4);
                    getPiece(19).setOriginPlayer(5);
                    getPiece(20).setOriginPlayer(5);
                    getPiece(21).setOriginPlayer(5);
                    getPiece(22).setOriginPlayer(5);
                    getPiece(32).setOriginPlayer(5);
                    getPiece(33).setOriginPlayer(5);
                    getPiece(34).setOriginPlayer(5);
                    getPiece(44).setOriginPlayer(5);
                    getPiece(45).setOriginPlayer(5);
                    getPiece(55).setOriginPlayer(5);
                    getPiece(65).setOriginPlayer(6);
                    getPiece(75).setOriginPlayer(6);
                    getPiece(76).setOriginPlayer(6);
                    getPiece(86).setOriginPlayer(6);
                    getPiece(87).setOriginPlayer(6);
                    getPiece(88).setOriginPlayer(6);
                    getPiece(98).setOriginPlayer(6);
                    getPiece(99).setOriginPlayer(6);
                    getPiece(100).setOriginPlayer(6);
                    getPiece(101).setOriginPlayer(6);
                }
                break;
            case 3:
                getPiece(10).setPlayer(3);
                getPiece(10).setOriginPlayer(3);
                getPiece(11).setPlayer(3);
                getPiece(11).setOriginPlayer(3);
                getPiece(12).setPlayer(3);
                getPiece(12).setOriginPlayer(3);
                getPiece(13).setPlayer(3);
                getPiece(13).setOriginPlayer(3);
                getPiece(23).setPlayer(3);
                getPiece(23).setOriginPlayer(3);
                getPiece(24).setPlayer(3);
                getPiece(24).setOriginPlayer(3);
                getPiece(25).setPlayer(3);
                getPiece(25).setOriginPlayer(3);
                getPiece(35).setPlayer(3);
                getPiece(35).setOriginPlayer(3);
                getPiece(36).setPlayer(3);
                getPiece(36).setOriginPlayer(3);
                getPiece(46).setPlayer(3);
                getPiece(46).setOriginPlayer(3);
                break;
            case 4:
                getPiece(74).setPlayer(4);
                getPiece(74).setOriginPlayer(4);
                getPiece(84).setPlayer(4);
                getPiece(84).setOriginPlayer(4);
                getPiece(85).setPlayer(4);
                getPiece(85).setOriginPlayer(4);
                getPiece(95).setPlayer(4);
                getPiece(95).setOriginPlayer(4);
                getPiece(96).setPlayer(4);
                getPiece(96).setOriginPlayer(4);
                getPiece(97).setPlayer(4);
                getPiece(97).setOriginPlayer(4);
                getPiece(107).setPlayer(4);
                getPiece(107).setOriginPlayer(4);
                getPiece(108).setPlayer(4);
                getPiece(108).setOriginPlayer(4);
                getPiece(109).setPlayer(4);
                getPiece(109).setOriginPlayer(4);
                getPiece(110).setPlayer(4);
                getPiece(110).setOriginPlayer(4);

                break;
            case 5:
                getPiece(19).setPlayer(5);
                getPiece(19).setOriginPlayer(5);
                getPiece(20).setPlayer(5);
                getPiece(20).setOriginPlayer(5);
                getPiece(21).setPlayer(5);
                getPiece(21).setOriginPlayer(5);
                getPiece(22).setPlayer(5);
                getPiece(22).setOriginPlayer(5);
                getPiece(32).setPlayer(5);
                getPiece(32).setOriginPlayer(5);
                getPiece(33).setPlayer(5);
                getPiece(33).setOriginPlayer(5);
                getPiece(34).setPlayer(5);
                getPiece(34).setOriginPlayer(5);
                getPiece(44).setPlayer(5);
                getPiece(44).setOriginPlayer(5);
                getPiece(45).setPlayer(5);
                getPiece(45).setOriginPlayer(5);
                getPiece(55).setPlayer(5);
                getPiece(55).setOriginPlayer(5);
                break;
            case 6:
                getPiece(65).setPlayer(6);
                getPiece(65).setOriginPlayer(6);
                getPiece(75).setPlayer(6);
                getPiece(75).setOriginPlayer(6);
                getPiece(76).setPlayer(6);
                getPiece(76).setOriginPlayer(6);
                getPiece(86).setPlayer(6);
                getPiece(86).setOriginPlayer(6);
                getPiece(87).setPlayer(6);
                getPiece(87).setOriginPlayer(6);
                getPiece(88).setPlayer(6);
                getPiece(88).setOriginPlayer(6);
                getPiece(98).setPlayer(6);
                getPiece(98).setOriginPlayer(6);
                getPiece(99).setPlayer(6);
                getPiece(99).setOriginPlayer(6);
                getPiece(100).setPlayer(6);
                getPiece(100).setOriginPlayer(6);
                getPiece(101).setPlayer(6);
                getPiece(101).setOriginPlayer(6);
                break;
        }
    }
    public Piece getPiece(int p)
    {
        return pieces.get(p);
    }
    public void draw(SpriteBatch batch,ShapeRenderer shapeRenderer, ArrayList<Integer> possibleMoves)
    {
        batch.begin();
        batch.draw(background,0,0);
        for (Piece p : pieces)
        {
            switch (p.getPlayer())
            {
                case 1:
                    batch.draw(player1,p.x-22,p.y-20);
                    break;
                case 2:
                    batch.draw(player2,p.x-22,p.y-20);
                    break;
                case 3:
                    batch.draw(player3,p.x-22,p.y-20);
                    break;
                case 4:
                    batch.draw(player4,p.x-22,p.y-20);
                    break;
                case 5:
                    batch.draw(player5,p.x-22,p.y-20);
                    break;
                case 6:
                    batch.draw(player6,p.x-22,p.y-20);
                    break;
            }
        }
        batch.end();
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.BROWN);
        for (int i=0; i< 121;i++)
        {
            if (possibleMoves.contains(i))
                shapeRenderer.circle(getPiece(i).x, getPiece(i).y, 22);
        }
        shapeRenderer.end();
    }
    public Boolean move(int init,int pos,int playerTurn)
    {
        if (pieces.get(pos).getPlayer()>0 || playerTurn!=pieces.get(init).getPlayer())
            return false;
        pieces.get(pos).setPlayer(pieces.get(init).getPlayer());
        pieces.get(init).setPlayer(0);
        return true;
    }
    public void build()
    {
        pieces.add(new Piece(1036-200,967,22));
        pieces.add(new Piece(1018-8-200,944-23,22));
        pieces.add(new Piece(1070-8-200,944-23,22));
        pieces.add(new Piece(983-200,868,22));
        pieces.add(new Piece(1044-8-200,894-23,22));
        pieces.add(new Piece(1096-8-200,894-23,22));
        pieces.add(new Piece(966-8-200,847-23-1,22));
        pieces.add(new Piece(1010-200,821,22));
        pieces.add(new Piece(1070-8-200,847-23-1,22));
        pieces.add(new Piece(1115-200,821,22));
        pieces.add(new Piece(692-8-200,794-23-1,22));
        pieces.add(new Piece(748-8-200,794-23-1,22));
        pieces.add(new Piece(804-8-200,790-23-1,22));
        pieces.add(new Piece(859-8-200,788-23,22));
        pieces.add(new Piece(939-8-200,784-23,22));
        pieces.add(new Piece(991-8-200,784-23,22));
        pieces.add(new Piece(1043-8-200,784-23,22));
        pieces.add(new Piece(1096-8-200,785-23,22));
        pieces.add(new Piece(1140-200,760,22));
        pieces.add(new Piece(1227-8-200,789-23-1,22));
        pieces.add(new Piece(1283-8-200,790-23-1,22));
        pieces.add(new Piece(1339-8-200,792-23,22));
        pieces.add(new Piece(1394-8-200,793-23-1,22));
        pieces.add(new Piece(722-8-200,747-23,22));
        pieces.add(new Piece(778-8-200,744-23,22));
        pieces.add(new Piece(833-8-200,743-23,22));
        pieces.add(new Piece(914-8-200,737-23,22));
        pieces.add(new Piece(966-8-200,737-23,22));
        pieces.add(new Piece(1018-8-200,736-23,22));
        pieces.add(new Piece(1070-8-200,736-23,22));
        pieces.add(new Piece(1122-8-200,736-23,22));
        pieces.add(new Piece(1175-8-200,736-23,22));
        pieces.add(new Piece(1246-200,718,22));
        pieces.add(new Piece(1301-200,719,22));
        pieces.add(new Piece(1357-200,721,22));
        pieces.add(new Piece(744-200,674,22));
        pieces.add(new Piece(799-200,673,22));
        pieces.add(new Piece(879-200,662,22));
        pieces.add(new Piece(931-200,661,22));
        pieces.add(new Piece(983-200,661,22));
        pieces.add(new Piece(1036-200,662,22));
        pieces.add(new Piece(1088-200,662,22));
        pieces.add(new Piece(1144-200,662,22));
        pieces.add(new Piece(1192-200,662,22));
        pieces.add(new Piece(1272-200,672,22));
        pieces.add(new Piece(1327-200,674,22));
        pieces.add(new Piece(772-200,628,22));
        pieces.add(new Piece(853-200,613,22));
        pieces.add(new Piece(906-200,613,22));
        pieces.add(new Piece(957-200,613,22));
        pieces.add(new Piece(1010-200,613,22));
        pieces.add(new Piece(1062-200,613,22));
        pieces.add(new Piece(1114-200,613,22));
        pieces.add(new Piece(1166-200,613,22));
        pieces.add(new Piece(1219-200,613,22));
        pieces.add(new Piece(1298-200,627,22));
        pieces.add(new Piece(827-200,565,22));
        pieces.add(new Piece(879-200,565,22));
        pieces.add(new Piece(931-200,565,22));
        pieces.add(new Piece(983-200,565,22));
        pieces.add(new Piece(1035-200,565,22));
        pieces.add(new Piece(1088-200,565,22));
        pieces.add(new Piece(1140-200,565,22));
        pieces.add(new Piece(1192-200,565,22));
        pieces.add(new Piece(1244-200,565,22));
        pieces.add(new Piece(772-200,628-126,22));
        pieces.add(new Piece(853-200,613-99,22));
        pieces.add(new Piece(906-200,613-99,22));
        pieces.add(new Piece(957-200,613-99,22));
        pieces.add(new Piece(1010-200,613-99,22));
        pieces.add(new Piece(1062-200,613-99,22));
        pieces.add(new Piece(1114-200,613-99,22));
        pieces.add(new Piece(1166-200,613-99,22));
        pieces.add(new Piece(1219-200,613-99,22));
        pieces.add(new Piece(1298-200,627-126,22));
        pieces.add(new Piece(744-200,674-217-1,22));
        pieces.add(new Piece(799-200,673-217,22));
        pieces.add(new Piece(879-200,662-195,22));
        pieces.add(new Piece(931-200,661-195,22));
        pieces.add(new Piece(983-200,661-195,22));
        pieces.add(new Piece(1036-200,662-195,22));
        pieces.add(new Piece(1088-200,662-195,22));
        pieces.add(new Piece(1144-200,662-195,22));
        pieces.add(new Piece(1192-200,662-195,22));
        pieces.add(new Piece(1272-200,672-217,22));
        pieces.add(new Piece(1327-200,674-217,22));
        pieces.add(new Piece(722-8-200,747-23-311-5,22));
        pieces.add(new Piece(778-8-200,744-23-311,22));
        pieces.add(new Piece(833-8-200,743-23-311,22));
        pieces.add(new Piece(914-8-200,737-23-294,22));
        pieces.add(new Piece(966-8-200,737-23-294,22));
        pieces.add(new Piece(1018-8-200,736-23-294,22));
        pieces.add(new Piece(1070-8-200,736-23-294,22));
        pieces.add(new Piece(1122-8-200,736-23-294,22));
        pieces.add(new Piece(1175-8-200,736-23-294,22));
        pieces.add(new Piece(1246-200,718-308,22));
        pieces.add(new Piece(1301-200,719-308,22));
        pieces.add(new Piece(1357-200,721-308-5,22));
        pieces.add(new Piece(685-200,360,22));
        pieces.add(new Piece(740-200,362,22));
        pieces.add(new Piece(796-200,364,22));
        pieces.add(new Piece(850-200,365,22));
        pieces.add(new Piece(931-200,368,22));
        pieces.add(new Piece(983-200,369,22));
        pieces.add(new Piece(1035-200,369,22));
        pieces.add(new Piece(1087-200,369,22));
        pieces.add(new Piece(1139-200,369,22));
        pieces.add(new Piece(1219-200,366,22));
        pieces.add(new Piece(1275-200,364,22));
        pieces.add(new Piece(1330-200,361,22));
        pieces.add(new Piece(1386-200,360,22));
        pieces.add(new Piece(958-200,306,22));
        pieces.add(new Piece(1010-200,306,22));
        pieces.add(new Piece(1063-200,306,22));
        pieces.add(new Piece(1115-200,306,22));
        pieces.add(new Piece(984-200,257,22));
        pieces.add(new Piece(1036-200,257,22));
        pieces.add(new Piece(1087-200,257,22));
        pieces.add(new Piece(1010-200,208,22));
        pieces.add(new Piece(1062-200,208,22));
        pieces.add(new Piece(1036-200,160,22));
    }
    public void turnChanged(int playerTurn)
    {
        playerTurnText2.setText(playerTurn);
        switch (playerTurn)
        {
            case 1:
                playerTurnText2.setColor(Color.GREEN);
                break;
            case 2:
                playerTurnText2.setColor(Color.ORANGE);
                break;
            case 3:
                playerTurnText2.setColor(Color.YELLOW);
                break;
            case 4:
                playerTurnText2.setColor(Color.RED);
                break;
            case 5:
                playerTurnText2.setColor(Color.BLUE);
                break;
            case 6:
                playerTurnText2.setColor(Color.CYAN);
                break;
        }
    }
    public void dispose()
    {
        pieces.clear();
        pieces=null;
        background.getTexture().dispose();
        player1.dispose();
        player2.dispose();
        player3.dispose();
        player4.dispose();
        player5.dispose();
        player6.dispose();
        fontGenerator.dispose();
        fontGenerator2.dispose();
        textFont.dispose();
        textFont1.dispose();
        textFont2.dispose();
    }
}
