package org.group20.sunstruck.gameobject;

import java.util.Iterator;

import org.group20.sunstruck.Game;
import org.group20.sunstruck.Main;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.WorldManifold;


public class Player extends GameObject {
	public Player(Vector2 position, float width, float height,
			TextureRegion textureRegion, float density, float speed,
			float hull, float weapon, float shield, float impactDamage) {
		super(position, width, height, textureRegion, density, speed, hull, weapon,
				shield, impactDamage);
		// TODO Auto-generated constructor stub
	}

	private long startGun = System.currentTimeMillis();
	private long reloadTimeGun = 100;
	private long startBomb = System.currentTimeMillis();
	private long reloadTimeBomb = 1000;
	

	private int score;
	
	@Override
	public void update() {	
		
		boolean setXSpeedToZero = false;
		boolean setYSpeedToZero = false;
		
		body.setLinearVelocity(Game.getInstance().getInput().getNewVelocity());
		
		if(body.getWorldCenter().x < -Main.CAMERA_WIDTH/2 && body.getLinearVelocity().x < 0)
			setXSpeedToZero = true;
		if(body.getWorldCenter().x > Main.CAMERA_WIDTH/2 && body.getLinearVelocity().x > 0)
			setXSpeedToZero = true;
		if(body.getWorldCenter().y < -Main.CAMERA_WIDTH*Gdx.graphics.getHeight()/(2*Gdx.graphics.getWidth()) && body.getLinearVelocity().y < 0)
			setYSpeedToZero = true;
		if(body.getWorldCenter().y > Main.CAMERA_WIDTH*Gdx.graphics.getHeight()/(2*Gdx.graphics.getWidth()) && body.getLinearVelocity().y > 0)
			setYSpeedToZero = true;
		
		if(setXSpeedToZero)
			body.setLinearVelocity(new Vector2(0,body.getLinearVelocity().y));
		if(setYSpeedToZero)
			body.setLinearVelocity(new Vector2(body.getLinearVelocity().x, 0));
				
				
		long time = System.currentTimeMillis() - startGun;
		if(time>reloadTimeGun)
			if(Game.getInstance().getInput().getHasFired()){
				shoot();
				startGun = System.currentTimeMillis();
			}
		
		time = System.currentTimeMillis() - startBomb;
		if(time>reloadTimeBomb)
			if(Game.getInstance().getInput().getHasFiredBomb()){
				fireBomb();
				startBomb = System.currentTimeMillis();
			}
	}

	private void fireBomb() {		
		Vector2 pos = body.getWorldCenter().add((float) (width/2 +0.6), 0);
	
		// TODO use gameobjectfactory!
		Projectile laser = new Projectile(pos, 1f, 1f, Game.textureAtlas.findRegion("redLaser"),  1000, 0.1f, 0, 0, 0, 10);
		Vector2 vel = new Vector2(1, 0);
		vel.mul(laser.getSpeed());
		laser.getBody().setLinearVelocity(vel);
		laser.isBomb = true;
		//laser.getBody().setAngularVelocity((float) (Math.random()*100-5));
		Game.getInstance().getGameObjectList().add(laser);
	}

	@Override
	public void dispose() {
		Game.getInstance().getGameObjectsToBeDestroyed().add((GameObject)this);
		
	}
	
	public String toString(){
		return "Player";
	}
	
	private void shoot(){
		
		Vector2 pos = body.getWorldCenter().add((float) (width/2 +0.6), 0);
		
		// TODO use gameobjectfactory!
		Projectile laser = new Projectile(pos, 0.1f, 0.1f, Game.textureAtlas.findRegion("redLaser"), 10, 10, 0, 0, 0, 10);
		Vector2 vel = new Vector2(1, 0);
		vel.mul(laser.getSpeed());
		laser.getBody().setLinearVelocity(vel);
		//laser.getBody().setAngularVelocity((float) (Math.random()*100-5));
		Game.getInstance().getGameObjectList().add(laser);
	}
	public void setScore(int score) {
		this.score = score;
	}

	public int getScore() {
		return score;
	}

	@Override
	public void contact(WorldManifold worldManifold, float impactDamage) {
		impactDamage = 0;
		shield -= impactDamage;
		if(shield < 0){
			hull += shield;
			shield = 0;
			if(hull < 0)
				dispose();
		}
		System.out.println("Shield: "+shield+" Hull: "+hull);
	}

}
