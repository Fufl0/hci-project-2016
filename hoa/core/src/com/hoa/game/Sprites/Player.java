package com.hoa.game.Sprites;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.*;

/**
 * Created by lorenzo on 28/04/16.
 */
public class Player extends Sprite{
    public World world;
    public Body b2body;

    public Player(World world){
        this.world = world;
        definePlayer();
    }

    /** Defining the player */
    public void definePlayer(){
        BodyDef bdef = new BodyDef();
        bdef.position.set(32,32);
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(5);

        fdef.shape = shape;
        b2body.createFixture(fdef);
    }
}
