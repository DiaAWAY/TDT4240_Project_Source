package org.group20.sunstruck.gameobject;

import org.group20.sunstruck.Game;
import org.group20.sunstruck.gameobject.GameObject.TYPES;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Contact;

public class LaserGreen1 extends GameObject {
	public static TextureRegion laserTexture = Game.textureAtlas
			.findRegion("laserGreen1");

	public LaserGreen1() {
		super(laserTexture, 0.8f);
		isProjectile = true;
		speed = 15;
		impactDamage = 15;
		type = TYPES.BULLET;
		density = 5;
		score = 0;

	}

	@Override
	public void contact(Contact contact, float impactDamage) {
		dispose();

	}

}
