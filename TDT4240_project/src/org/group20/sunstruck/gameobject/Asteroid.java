package org.group20.sunstruck.gameobject;

import java.util.ArrayList;

import org.group20.sunstruck.Game;
import org.group20.sunstruck.behavior.Behavior.BEHAVIOR;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.WorldManifold;

/**
 * Example class for products of the GameObjectFactory
 * 
 * @author DiaAWAY
 * 
 */
public class Asteroid extends GameObject { // TODO remove test class
	ArrayList<TextureRegion> asteroidTexture = new ArrayList<TextureRegion>();

	public Asteroid(int size) {
		super(null, TYPES.ASTEROID, BEHAVIOR.LINE, true, true, null, 1, 20, 20,
				0, 0, 4, 200, 3, 9, 10);
		asteroidTexture.add(Game.textureAtlas.findRegion("asteroidSmall"));
		asteroidTexture.add(Game.textureAtlas.findRegion("asteroidMedium"));
		asteroidTexture.add(Game.textureAtlas.findRegion("asteroidLarge"));
		asteroidTexture.add(Game.textureAtlas.findRegion("asteroidHuge"));

		textureRegion = asteroidTexture.get(size);
		width = size;
	}

	@Override
	public void contact(WorldManifold worldManifold, float impactDamage) {
		hull -= impactDamage;
		if (hull < 0) {
			// Game.getInstance().getWorld().
			// gof.createMeteorite(body.getWorldCenter(), height/2);
			// gof.createMeteorite(body.getWorldCenter(), height/2);
			// gof.createMeteorite(body.getWorldCenter(), height/2);
			// gof.createMeteorite(body.getWorldCenter(), height/2);
			dispose();
		}

	}
}
