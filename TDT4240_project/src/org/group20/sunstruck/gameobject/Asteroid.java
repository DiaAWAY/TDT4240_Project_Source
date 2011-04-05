package org.group20.sunstruck.gameobject;

import java.util.ArrayList;

import org.group20.sunstruck.Game;
import org.group20.sunstruck.behavior.Behavior;
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
	int size;
	boolean split = false;
	boolean hasSplit = false;

	private static ArrayList<TextureRegion> asteroidTexture = new ArrayList<TextureRegion>();

	public Asteroid() {
		this(3);
	}

	public Asteroid(int size) {
		super(null, TYPES.ASTEROID, BEHAVIOR.LINE, false, true, null, 1, 20,
				20, 0, 0, 4, 1000, 0, 0, 10, 10);
		asteroidTexture.add(Game.textureAtlas.findRegion("asteroidSmall"));
		asteroidTexture.add(Game.textureAtlas.findRegion("asteroidMedium"));
		asteroidTexture.add(Game.textureAtlas.findRegion("asteroidLarge"));
		asteroidTexture.add(Game.textureAtlas.findRegion("asteroidHuge"));
		
		this.weaponType = this;
		this.size = size;
		textureRegion = asteroidTexture.get(size);
		width = (float) Math.pow(2, size);
		height = width;
	}

	@Override
	public void contact(WorldManifold worldManifold, float impactDamage) {
		hull -= impactDamage;
		if (hull <= 0) {
			split = true;
		}
	}

	@Override
	public void update() {
		if (split && !hasSplit) {
			dispose();
			if (size > 0)
				Game.getInstance().getGoFactory().generateWeaponShot(
						weaponType, this);
			hasSplit = true;
		}

		Behavior.applyBehavior(this);
	}

}
