package org.group20.sunstruck.gameobject;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.WorldManifold;

public class TieInterceptor extends GameObject{

	
	public TieInterceptor(Vector2 position, float width, float height,
			TextureRegion textureRegion, float density, float speed,
			float hull, float weapon, float shield) {
		super(position, width, height, textureRegion, density, speed, hull, weapon,
				shield, shield);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void update() {
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void contact(WorldManifold worldManifold, float impactDamage) {
		// TODO Auto-generated method stub
		
	}

}
