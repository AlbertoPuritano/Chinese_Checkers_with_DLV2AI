package com.winnie.the.pooh;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.StretchViewport;


public class Game extends ApplicationAdapter {

	public static int numPlayers;
	public static Boolean isMenu;
	private Menu menu;
	private Checkers checkers;
	public static Input input;
	private OrthographicCamera cam;
	public StretchViewport view;
	public Stage stageMenu;
	public Stage stageGame;
	public static int width= 1665;
	public static int height= 1130;
	public int lastWinner;
	public Boolean[] AI;
	public Audio audio;
	public static Boolean exitMenu;
	public static Skin skin;
	public static boolean pause;
	@Override
	public void create () {
		cam = new OrthographicCamera();
		cam.setToOrtho(false, width, height);
		view = new StretchViewport(width,height);
		stageMenu= new Stage(view);
		stageGame= new Stage(view);
		lastWinner=-1;
		AI= new Boolean[]{false, false, false, false, false,false,false};
		audio = new Audio();
		skin = new Skin(Gdx.files.internal("rusty-robot/skin/rusty-robot-ui.json"));
		menu= new Menu(stageMenu,lastWinner,AI,audio);
		input= new Input(cam);
		exitMenu=false;
		pause = false;
	}

	@Override
	public void render () {
		if (isMenu)
		{
			menu.draw();
		}
		else
		{
			if (checkers!=null) {
				if (Gdx.input.isKeyJustPressed(com.badlogic.gdx.Input.Keys.ENTER))
					pause=!pause;
				if (!pause)
				{
					checkers.draw();
					if (checkers.makeMove()) {
						isMenu = true;
						//stageGame.clear();
						System.out.println("Player " + checkers.playerTurn + " has won!");
						lastWinner = checkers.playerTurn;
						checkers.dispose();
						stageMenu = new Stage(view);
						menu = new Menu(stageMenu, lastWinner, AI, audio);
						checkers = null;
					}
					if (exitMenu) {
						isMenu = true;
						//stageGame.clear();
						checkers.dispose();
						stageMenu = new Stage(view);
						menu = new Menu(stageMenu, lastWinner, AI, audio);
						checkers = null;
						exitMenu = false;
					}
				}
			}
			else
			{
				//stageMenu.clear();
				menu.dispose();
				stageGame= new Stage(view);
				checkers = new Checkers(numPlayers,stageGame,AI,audio);
				menu=null;
			}
		}
	}

	@Override
	public void resize(int width, int height) {
		if (isMenu)
			stageMenu.getViewport().update(width, height, true);
		else
			stageGame.getViewport().update(width, height, true);
	}

	@Override
	public void dispose() {
		audio.dispose();
	}
}
