package com.hoa.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.hoa.game.screens.MainMenuScreen;

public class HoA extends Game {
	public static final int screenWidth = 800;
	public static final int screenHeight = 600;
	public int posx;
	public int posy;
	public int health;
	public int healththresh;
	public int xp;
	public int xpthresh;
	public int level;
	public String zone;

	public SpriteBatch batch;

	public int getPosx() {
		return posx;
	}

	public int getPosy() {
		return posy;
	}

	public void setPos(int x, int y)
	{
		posx = x;
		posy = y;
	}

	public void setZone (String zone){
		this.zone = zone;
	}

	public String getZone(){
		return zone;
	}


	@Override
	public void create() {
		batch = new SpriteBatch();
		setScreen(new MainMenuScreen(this));
;	}

	@Override

	public void render () {
		super.render();
	}

	public void setHealthThreshold(int healthThreshold) {
		this.healththresh = healthThreshold;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public void setXP(int XP) {
		this.xp = XP;
	}

	public void setXPThreshold(int XPThreshold) {
		this.xpthresh = XPThreshold;
	}

	public void setLevel(int level){
		this.level = level;
	}
}
