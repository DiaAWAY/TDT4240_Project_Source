package org.group20.sunstruck.gameobject;

import org.group20.sunstruck.Game;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Contact;

public class LaserTiny2 extends GameObject {
	public static TextureRegion laserTexture = Game.textureAtlas
			.findRegion("laserTiny2");

	public LaserTiny2() {
		super(laserTexture, 0.2f);
		isProjectile = true;
		speed = 6;
		density = 0.01f;
		impactDamage = 2;
		type = TYPES.BULLET;
		score = 0;

	}

	@Override
	public void contact(Contact contact, float impactDamage) {
		dispose();

	}

}