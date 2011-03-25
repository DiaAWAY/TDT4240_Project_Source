package org.group20.sunstruck.gameobject;

import org.group20.sunstruck.Game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;


public class Player extends GameObject {
	private long start = System.currentTimeMillis();
	private long realoadTime = 500;
	
	public Player(Vector2 position, float width, float height,
			TextureRegion textureRegion, float density, float speed,
			float hull, float weapon, float shield) {
		super(position, width, height, textureRegion, density, speed, hull, weapon,
				shield);
	}

	private int score;
	
	@Override
	public void update() {
		body.setLinearVelocity(Game.getInstance().getInput().getNewVelocity());
		long time = System.currentTimeMillis() - start;
		if(time>realoadTime)
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
		Vector2 pos = body.getWorldCenter().add(width*2, 0);
		Projectile laser = new Projectile(pos, 3f, 0.5f, new TextureRegion(new Texture(Gdx.files.internal("data/redLaser.png"))), 1, 1000, 0, 0, 0);
		
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
