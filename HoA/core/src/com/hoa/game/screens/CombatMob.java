package com.hoa.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.hoa.game.HoA;
import com.hoa.game.Scenes.CombatMobHud;
import com.hoa.game.Scenes.Hud;
import com.hoa.game.Sprites.Mob;
import com.hoa.game.Tools.B2WorldCreator;
import com.hoa.game.Tools.CountDownTimer;

import static java.lang.Math.log;

/**
 * Created by shughi on 17/05/2016.
 */
public class CombatMob implements Screen {

    private Mob mob;

    private double mobLife;
    private String mobName;
    private Texture mobTexture;
    private int mobXp;
    private SuperClass current;

    //game class
    private HoA game;
    //private CombatScreen current;
    private Viewport gamePort;
    private Stage stage;
    // Current game camera & screen display, currently a FitViewPort
    //hud of the program
    private InputMultiplexer input;
    private CombatMobHud combatscene;
    public boolean killed = false;
    private int tot = 0;
    private CountDownTimer timer;
    //private boolean running;
    private boolean defeated;
    private boolean startedfight = false;

    public CombatMob (HoA game, Mob mob, SuperClass current){

        this.current=current;

        this.game = game;

        this.mob = mob;

        defeated=false;

        this.mobLife = mob.getLife();
        this.mobName = mob.getName();

        stage = new Stage(new FitViewport(HoA.screenWidth, HoA.screenHeight));
        input = new InputMultiplexer();

        gamePort = new FitViewport(HoA.screenWidth, HoA.screenHeight);

        //hud
        combatscene = new CombatMobHud(game.batch, mob , game);


    }


    public void handleInput(float dt){


        if (Gdx.input.isKeyPressed(Input.Keys.E)){
           // current.worldcreator=new B2WorldCreator(current.world, current.map, game, current);
            current.hud=new Hud(game.batch, game);
            game.setScreen(current);
        }

        else if (Gdx.input.justTouched()) {

            damageHandler();
        }

    }


    public void damageHandler(){
        if(killed){   // this is to make this if enter only once.
            if (tot >= mobLife-game.dmg ) {  // boss is dead. This is the click that kills him, or 10 sec have passed.

                    String ciao = "You defeated the " + mobName;
                    combatscene.setCounter(ciao);

            }
        }
        else  {
            if (tot == 0) {  // fight is about to start

                tot = tot + game.dmg;
                double giorgio = mobLife - tot;
                String hue = mobName + " life : " + giorgio;
                combatscene.setCounter(hue);
                combatscene.setOut("Currently Fighting!");


            }
            else if(tot>=mobLife-game.dmg){
                killed = true;

                    String ciao = "You defeated the " + mobName ;
                    combatscene.setCounter(ciao);
                    victory();
            }
            else {  // fight is going on
                tot = tot + game.dmg;
                double giorgio = mobLife - tot;
                String hue = mobName + " life : " + giorgio;
                combatscene.setCounter(hue);
            }
        }
    }

    public void update(float dt){
        handleInput(dt);
    }




    public void victory(){
        game.collisioncount=0;
        int boss_xp = mob.getXp();
        //handle level xp end so on
        if( (game.xp + boss_xp) >= game.xpthresh){  // when you increase level
            game.xp = game.xp + boss_xp;
            while (game.xp - game.xpthresh >= 0) {  // if you increase more than 1 level
                game.xp = game.xp - game.xpthresh;
                game.level++;
                game.xpthresh = game.xpthresh + game.xpthresh*(game.level-1);
                double damage=game.dmg*log(10+game.dmg);
                game.dmg = (int) damage;

            }
            if (game.level % 5 == 0 & game.level < 10) {

                    game.healththresh++;
                    game.health++;}
                 else if (game.health < game.healththresh) {
                    game.health++;
                }

            combatscene.setOut("You gained " + boss_xp + "XP, and you are now level "+game.level+ "!"+" press E to continue.");
        }
        else {
            game.setXP(game.xp + boss_xp);
            combatscene.setOut("You gained " + boss_xp + "XP, press E to continue.");
        }
    }





    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        update(delta);
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //stage.act(delta);
        //stage.setDebugAll();

        //stage.getViewport().apply();
        combatscene.stage.draw();



    }

    @Override
    public void resize(int width, int height) {
        gamePort.update(width, height);
        stage.getViewport().update(width, height);

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        combatscene.dispose();

    }
}