package com.winnie.the.pooh;

import it.unical.mat.embasp.base.Handler;
import it.unical.mat.embasp.languages.asp.ASPInputProgram;
import it.unical.mat.embasp.languages.asp.AnswerSet;
import it.unical.mat.embasp.platforms.desktop.DesktopHandler;
import it.unical.mat.embasp.specializations.dlv2.desktop.DLV2DesktopService;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

import javafx.util.Pair;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.processing.Processor;

public class Checkers {
    final private SpriteBatch batch;
    public Board board;
    public int numPlayers;
    public int playerTurn;
    public Integer selectedPiece;
    public ArrayList<Integer> possibleMoves;
    public ArrayList<Integer> jumpMoves;
    public Circle circle;
    public Integer onlyJump;
    public Integer beforeJump;
    public Stage stage;
    public ShapeRenderer shapeRenderer;
    public Boolean[] AI;
    public Audio audio;
    public AI ai;
    public int count=0;
    public static Pair<Integer,Integer> showPrevious;
    public Checkers(int numPlayers, Stage stage, Boolean[] AI, final Audio audio)
    {
        Gdx.input.setCatchKey(Input.Keys.BACK, true);
        this.stage=stage;
        beforeJump=-1;
        final int finalNumPlayers = numPlayers; //non capisco ma funziona solo così
        shapeRenderer= new ShapeRenderer();
        this.numPlayers=numPlayers;
        playerTurn=1;
        batch = new SpriteBatch();
        board = new Board(stage,audio);
        possibleMoves= new ArrayList<Integer>();
        jumpMoves=new ArrayList<Integer>();
        onlyJump=-1;
        board.addPlayer(1);
        board.addPlayer(2);
        numPlayers-=2;
        if (numPlayers>0)
        {
            int i=2;
            while (numPlayers>0)
            {
                i++;
                board.addPlayer(i);
                numPlayers--;
            }
        }
        //END TURN BUTTON
        stage.getActors().get(0).addListener(new ChangeListener() {
            public void changed (ChangeEvent event, Actor endTurn) {
                audio.playClick();
                if (onlyJump>-1 && beforeJump != selectedPiece)
                {
                    onlyJump = -1;
                    playerTurn++;
                    if (playerTurn== finalNumPlayers +1)
                        playerTurn=1;
                    board.turnChanged(playerTurn);
                    possibleMoves.clear();
                    beforeJump=-1;
                    endTurn.setVisible(false);
                    audio.playNext();
                }
            }
        });
        this.AI=AI;
        ai= new AI();
        this.audio = audio;
        audio.playNewGame();
        board.turnChanged(playerTurn);
        showPrevious= new Pair<>(-1,-1);
    }
    public void draw()
    {
        if (Gdx.input.isKeyJustPressed(Input.Keys.BACK))
            stage.getActors().get(stage.getActors().size-1).setVisible(!stage.getActors().get(stage.getActors().size-1).isVisible());
        stage.getCamera().update();
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.setProjectionMatrix(stage.getCamera().combined);
        shapeRenderer.setProjectionMatrix(stage.getCamera().combined);
        Game.input.convertInput();
        board.draw(batch,shapeRenderer,possibleMoves);
        stage.act();
        stage.draw();
    }
    public Boolean makeMove()
    {
    	//if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER))
    	//	System.out.println(getMoves());
        if (AI[playerTurn])
            return AIMove();
        else
            return humanMove();
    }
    public Boolean winCond()
    {
        boolean full=true;
        boolean atLeast1=false;
        switch(playerTurn)
        {
            case 1:
                for (Piece p: board.pieces)
                {
                    if (p.getOriginPlayer()==2 && p.getPlayer()==0)
                    {
                        full = false;
                        break;
                    }
                    if (p.getOriginPlayer()==2 && p.getPlayer()==1)
                        atLeast1=true;
                }
                break;
            case 2:
                for (Piece p: board.pieces)
                {
                    if (p.getOriginPlayer()==1 && p.getPlayer()==0)
                    {
                        full = false;
                        break;
                    }
                    if (p.getOriginPlayer()==1 && p.getPlayer()==2)
                        atLeast1=true;
                }
                break;
            case 3:
                for (Piece p: board.pieces)
                {
                    if (p.getOriginPlayer()==4 && p.getPlayer()==0)
                    {
                        full = false;
                        break;
                    }
                    if (p.getOriginPlayer()==4 && p.getPlayer()==3)
                        atLeast1=true;
                }
                break;
            case 4:
                for (Piece p: board.pieces)
                {
                    if (p.getOriginPlayer()==3 && p.getPlayer()==0)
                    {
                        full = false;
                        break;
                    }
                    if (p.getOriginPlayer()==3 && p.getPlayer()==4)
                        atLeast1=true;
                }
                break;
            case 5:
                for (Piece p: board.pieces)
                {
                    if (p.getOriginPlayer()==4 && p.getPlayer()==0)
                    {
                        full = false;
                        break;
                    }
                    if (p.getOriginPlayer()==4 && p.getPlayer()==5)
                        atLeast1=true;
                }
                break;
            case 6:
                for (Piece p: board.pieces)
                {
                    if (p.getOriginPlayer()==5 && p.getPlayer()==0)
                    {
                        full = false;
                        break;
                    }
                    if (p.getOriginPlayer()==5 && p.getPlayer()==6)
                        atLeast1=true;
                }
                break;
        }
        return full && atLeast1;
    }
    public void calculateMoves()
    {
        possibleMoves.clear();
        jumpMoves.clear();
        circle= new Circle(board.getPiece(selectedPiece).x,board.getPiece(selectedPiece).y,62);
        for (int i=0; i<board.pieces.size(); i++)
        {
            if (board.getPiece(i).overlaps(circle))
            {
                if (board.getPiece(i).getPlayer()==0)
                {
                    if (onlyJump == -1)
                    {
                        boolean check=true;
                        if (board.getPiece(i).getOriginPlayer()!=-1)
                        {
                            if ((playerTurn==1 || playerTurn==2) &&
                                    (board.getPiece(i).getOriginPlayer()!=1 && board.getPiece(i).getOriginPlayer()!=2) ||
                            (playerTurn==3 || playerTurn==4) &&
                                    (board.getPiece(i).getOriginPlayer()!=3 && board.getPiece(i).getOriginPlayer()!=4) ||
                            (playerTurn==5 || playerTurn==6) &&
                                    (board.getPiece(i).getOriginPlayer()!=5 && board.getPiece(i).getOriginPlayer()!=6)
                            )
                                check=false;
                        }
                        if (check)
                            possibleMoves.add(i);
                    }
                }
                else
                {
                    int diffX= (int) ((int)board.getPiece(i).x-board.getPiece(selectedPiece).x);
                    int diffY= (int) ((int)board.getPiece(i).y-board.getPiece(selectedPiece).y);
                    for (int j=0; j<board.pieces.size(); j++)
                    {
                        if (board.getPiece(j).getPlayer()==0 && board.getPiece(j).contains(board.getPiece(i).x+diffX, board.getPiece(i).y+diffY))
                        {
                            possibleMoves.add(j);
                            jumpMoves.add(j);
                            break;
                        }
                    }
                }
            }
        }
    }
    public Boolean humanMove()
    {
        if (stage.getActors().get(stage.getActors().size-1).isVisible())
            return false;
        Game.input.convertInput();
        for (int i=0; i< 121;i++)
        {
            if (Gdx.input.isTouched() && board.getPiece(i).contains(Game.input.x(),Game.input.y()))
            {
                if (board.getPiece(i).getPlayer()==playerTurn)
                {
                    if (onlyJump==-1) {
                        if (selectedPiece== null || selectedPiece!=i)
                            audio.playSelect();
                        selectedPiece = i;
                        beforeJump=i;
                    }
                    calculateMoves();
                }
                if (selectedPiece!= null && possibleMoves.contains(i) && board.move(selectedPiece,i,playerTurn))
                {
                    if (winCond())
                    {
                        audio.playWin();
                        return true;
                    }
                    if (jumpMoves.contains(i))
                    {
                        audio.playJump();
                        onlyJump = i;
                        selectedPiece = i;
                        if (onlyJump==beforeJump)
                        {
                            onlyJump=-1;
                            beforeJump=-1;
                            stage.getActors().get(0).setVisible(false);
                        }
                        else
                            stage.getActors().get(0).setVisible(true);
                    }
                    else
                    {
                        audio.playMove();
                        playerTurn++;
                        selectedPiece = null;
                    }
                    possibleMoves.clear();
                    jumpMoves.clear();
                }
                if (playerTurn==numPlayers+1)
                    playerTurn=1;
                board.turnChanged(playerTurn);
            }
        }
        return false;
    }
    public ArrayList<Pair<Integer,ArrayList<Integer>>> getMoves()
    {
        possibleMoves.clear();
        jumpMoves.clear();
        ArrayList<Pair<Integer,ArrayList<Integer>>> piecesAndMoves= new ArrayList<>();
        for (int i=0; i< board.pieces.size();i++)
        {
            if (board.getPiece(i).getPlayer()==playerTurn)
            {
                selectedPiece=i;
                calculateMoves();
                ArrayList<Integer> moves = new ArrayList<>(possibleMoves);
                if (moves.size()>0)
                    if (moves.contains(i))
                        moves.remove(i);
                HashSet<Integer> temp= new HashSet<>(moves);
                moves = new ArrayList<Integer>(temp);
                if (moves.size()>0)
                    piecesAndMoves.add(new Pair<Integer, ArrayList<Integer>>(i,moves));
            }
        }
        for (int i=0;i<piecesAndMoves.size();i++)
        {
            for (int j=0;j<piecesAndMoves.get(i).getValue().size();j++)
            {
                selectedPiece=piecesAndMoves.get(i).getValue().get(j);
                calculateMoves();
                for (Integer move: jumpMoves)
                {
                    if (!piecesAndMoves.get(i).getValue().contains(move))
                        piecesAndMoves.get(i).getValue().add(move);
                }
            }
        }
        selectedPiece=null;
        possibleMoves.clear();
        jumpMoves.clear();
        return piecesAndMoves;
    }
    public Boolean AIMove()
    {
        System.out.println("---------------------------------------------------------");
        System.out.println(playerTurn);
        jumpMoves.clear();
        possibleMoves.clear();
        count++;
    	ArrayList<Pair<Integer,ArrayList<Integer>>> moves = getMoves();
        //System.out.println("mosse:");
        //System.out.println(moves);
    	boolean winMove=false;
    	int init=-1;
    	int pos=-1;
    	ArrayList<Mossa> mosse= new ArrayList<Mossa>();
    	for (int i=0; i<moves.size();i++)
    		for (Integer j: moves.get(i).getValue())
    		{
    			board.move(moves.get(i).getKey(), j, playerTurn);
    			if (winCond())
    			{
    				winMove=true;
    				init=moves.get(i).getKey();
    				pos=j;
    			}
    			board.move(j,moves.get(i).getKey(),playerTurn);
    			mosse.add(new Mossa (moves.get(i).getKey(),j,playerTurn));
    		}
    		///
        ///
        ai.clear();
        ai.loadFacts(mosse);
        ai.loadEncoding(playerTurn);
        ArrayList<Pair<Integer,Integer>> bestMoves= new ArrayList<Pair<Integer,Integer>>();
        for (AnswerSet a: ai.getAnswersets(true).getAnswersets())
        {
        	try
        	{
        		for (Object obj: a.getAnswerSet())
        		{
        			Matcher m= Pattern.compile("in").matcher((CharSequence) obj);
        			if (m.find())
 				    {
 				    	ArrayList<String> values = new ArrayList<String>();
 				    	Matcher m2 = Pattern.compile("[0-9]+").matcher((CharSequence) obj);
 				    	while (m2.find())
 				    		values.add(m2.group());
 				    	bestMoves.add(new Pair<Integer,Integer> (Integer.parseInt(values.get(0)),Integer.parseInt(values.get(1))));
 				    }
        		}
        	}
        	catch (Exception e)
        	{
        		e.printStackTrace();
        	}
        }
        System.out.println("size array bestMoves: " + bestMoves.size());
        int player=playerTurn;
        int enemy;
        if (player==1)
            enemy=2;
        else
            enemy=1;
        WeakMoves costi= new WeakMoves(2);
        for (int a=0;a<bestMoves.size();a++)
        {
            int move1= bestMoves.get(a).getKey();
            int move2=bestMoves.get(a).getValue();
            board.move(move1,move2,playerTurn);
            playerTurn=enemy;
            ArrayList<Pair<Integer,ArrayList<Integer>>> movesEnemy = getMoves();
            playerTurn=player;
            board.move(move2,move1,playerTurn);
            ArrayList<Mossa> mosseEnemy= new ArrayList<Mossa>();
            mosseEnemy.add(new Mossa(move1,move2,player));//MOSSA DEL GIOCATORE IN CORSO
            for (int i=0; i<movesEnemy.size();i++)
                for (Integer j: movesEnemy.get(i).getValue())
                    mosseEnemy.add(new Mossa (movesEnemy.get(i).getKey(),j,enemy));
            ai.clear();
            ai.loadFacts(mosseEnemy);
            if (player==1)
                ai.loadEncoding(3);
            else
                ai.loadEncoding(4);
            costi.add(ai.getAnswersets(false).getAnswerSetsString());
        }
        //Random random= new Random();
        //int n= random.nextInt(bestMoves.size());
        System.out.println("migliore indice costo: " +  costi.bestCost());
        init= bestMoves.get(costi.bestCost()).getKey();
        pos=bestMoves.get(costi.bestCost()).getValue();
        //System.out.println(init);
        //System.out.println(pos);
        jumpMoves.clear();
        possibleMoves.clear();
        selectedPiece=init;
        showPrevious=new Pair<Integer,Integer>(playerTurn,init);
        board.move(init, pos, playerTurn);//pos iniz, fin, p turn
        if (winCond())
            return true;
        playerTurn++;
        if (playerTurn==numPlayers+1)
            playerTurn=1;
        board.turnChanged(playerTurn);
        possibleMoves.clear();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }
    public void dispose()
    {
        board.dispose();
        stage.dispose();
        batch.dispose();
        shapeRenderer.dispose();
    }
}
