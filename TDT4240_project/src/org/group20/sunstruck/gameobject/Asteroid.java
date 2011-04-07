package org.group20.sunstruck.gameobject;

import java.util.ArrayList;

import org.group20.sunstruck.Game;
import org.group20.sunstruck.behavior.Behavior;
import org.group20.sunstruck.behavior.Behavior.BEHAVIOR;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Contact;

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
		super(null, 0);
		if(asteroidTexture.isEmpty()){
			asteroidTexture.add(Game.textureAtlas.findRegion("asteroidSmall"));
			asteroidTexture.add(Game.textureAtlas.findRegion("asteroidMedium"));
			asteroidTexture.add(Game.textureAtlas.findRegion("asteroidLarge"));
			asteroidTexture.add(Game.textureAtlas.findRegion("asteroidHuge"));
		}
			
		this.size = size;
		this.impactDamage = 5 + 5 * size;
		textureRegion = asteroidTexture.get(size);
		width = (float) Math.pow(2, size);
		height = width;
		type = TYPES.ASTEROID;
	}

	@Override
	public void contact(Contact contact, float impactDamage) {
		GameObject goA = (GameObject) contact.getFixtureA().getBody()
				.getUserData();
		GameObject goB = (GameObject) contact.getFixtureB().getBody()
				.getUserData();

		if (!(goA instanceof Asteroid) || !(goB instanceof Asteroid))
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
				// Game.getInstance().getGoFactory().generateWeaponShot(
				// weaponType, this);
				hasSplit = true;
		}

		Behavior.applyBehavior(this);
	}

}
