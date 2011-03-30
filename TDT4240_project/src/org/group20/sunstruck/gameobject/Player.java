package org.group20.sunstruck.gameobject;

import org.group20.sunstruck.Game;
import org.group20.sunstruck.Main;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Fixture;


public class Player extends GameObject {
	private long start = System.currentTimeMillis();
	private long reloadTime = 250;
	
	public Player(Vector2 position, float width, float height,
			TextureRegion textureRegion, float density, float speed,
			float hull, float weapon, float shield) {
		super(position, width, height, textureRegion, density, speed, hull, weapon,
				shield);
		body.setFixedRotation(true);
	}

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
				
				
		long time = System.currentTimeMillis() - start;
		if(time>reloadTime)
			if(Game.getInstance().getInput().getHasFired()){
				shoot();
				start = System.currentTimeMillis();
			}
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub   
		
	}
	
	public String toString(){
		return "Player";
	}
	
	private void shoot(){
		
		Vector2 pos = body.getWorldCenter().add((float) (width/2 +0.5), 0);
		
		// TODO use gameobjectfactory!
		Projectile laser = new Projectile(pos, 1f, 0.05f, Game.textureAtlas.findRegion("redLaser"), 1, 20, 0, 0, 0);

		Vector2 vel = new Vector2(1, 0);
		vel.mul(laser.getSpeed());
		laser.getBody().setLinearVelocity(vel);
		laser.getBody().setAngularVelocity((float) (Math.random()*100-5));
		
		Game.getInstance().getGameObjectList().add(laser);
	}
	public void setScore(int score) {
		this.score = score;
	}

	public int getScore() {
		return score;
	}

}
