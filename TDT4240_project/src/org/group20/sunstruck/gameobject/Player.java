package org.group20.sunstruck.gameobject;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;


public class Player extends GameObject {
	
	public Player(Vector2 position, float width, float height,
			TextureRegion textureRegion, float density, float speed,
			float hull, float weapon, float shield) {
		super(position, width, height, textureRegion, density, speed, hull, weapon,
				shield);
	}

	private int score;
	
	@Override
	public void update() {
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getScore() {
		return score;
	}

}
