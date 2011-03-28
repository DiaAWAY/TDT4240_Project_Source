package org.group20.sunstruck.gameobject;

import org.group20.sunstruck.Game;
import org.group20.sunstruck.Main;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;


public class Player extends GameObject {
	private long start = System.currentTimeMillis();
	private long reloadTime = 100;
	
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
		boolean setVelocityToZero = false;
		
		body.setLinearVelocity(Game.getInstance().getInput().getNewVelocity());
		
		if(body.getWorldCenter().x < -Main.CAMERA_WIDTH/2 && body.getLinearVelocity().x < 0)
			setVelocityToZero = true;
		if(body.getWorldCenter().x > Main.CAMERA_WIDTH/2 && body.getLinearVelocity().x > 0)
			setVelocityToZero = true;
		if(body.getWorldCenter().y < -Main.CAMERA_WIDTH*Gdx.graphics.getHeight()/(2*Gdx.graphics.getWidth()) && body.getLinearVelocity().y < 0)
			setVelocityToZero = true;
		if(body.getWorldCenter().y > Main.CAMERA_WIDTH*Gdx.graphics.getHeight()/(2*Gdx.graphics.getWidth()) && body.getLinearVelocity().y > 0)
			setVelocityToZero = true;
		if(setVelocityToZero)
			body.setLinearVelocity(new Vector2(0,0));
				
				
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
	
	private void shoot(){
		Vector2 pos = body.getWorldCenter().add((float)(width/2+0.25),0);
		Projectile laser = new Projectile(pos, 0.5f, 0.01f, new TextureRegion(new Texture(Gdx.files.internal("data/redLaser.png"))), 1, 20f, 0, 0, 0);
		
		Vector2 vel = new Vector2(1, 0);
		vel.mul(laser.getSpeed());
		laser.getBody().setLinearVelocity(vel);
		
		Game.getInstance().getGameObjectList().add(laser);
	}
	public void setScore(int score) {
		this.score = score;
	}

	public int getScore() {
		return score;
	}

}
