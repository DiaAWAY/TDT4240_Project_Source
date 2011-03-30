package org.group20.sunstruck.gameobject;

import org.group20.sunstruck.Game;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Fixture;

public class Projectile extends GameObject{

	public Projectile(Vector2 position, float width, float height,
			TextureRegion textureRegion, float density, float speed,
			float hull, float weapon, float shield) {
		super(position, width, height, textureRegion, density, speed, hull, weapon,
				shield);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void dispose() {
		Game.destroyGameObject(this);
	}
	
	public String toString(){
		return "Shot";
	}

	@Override
	public void update() {
		
	}

}
