package com.hoa.game.Sprites;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.*;
import com.hoa.game.HoA;

/**
 * Created by BMW on 03/05/2016.
 */
public abstract class InteractiveTile {
    protected World world;
    protected TiledMap map;
    protected TiledMapTile tile;
    protected Rectangle bounds;
    protected Body body;
    protected Fixture fixture;
    protected TmxMapLoader mapLoader;
    public HoA game;

    public InteractiveTile(World world, TiledMap map, Rectangle bounds, HoA game) {
        this.world = world;
        this.map = map;
        this.bounds = bounds;
        this.game = game;

        BodyDef bdef = new BodyDef();
        FixtureDef fdef = new FixtureDef();
        PolygonShape shape = new PolygonShape();

        bdef.type = BodyDef.BodyType.StaticBody;
        bdef.position.set(bounds.getX() + bounds.getWidth() / 2, bounds.getY() + bounds.getHeight() / 2);

        body = world.createBody(bdef);

        shape.setAsBox(bounds.getWidth() / 2, bounds.getHeight() / 2);
        fdef.shape = shape;
        fixture = body.createFixture(fdef);
    }

    public abstract void onCollision();
}
