package org.group20.sunstruck.gameobject;

import org.group20.sunstruck.Game;
import org.group20.sunstruck.behavior.Behavior.BEHAVIOR;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Contact;

public class Boss extends GameObject {
	GameObject weaponType2 = new SmallKamikazeShip();
	public static TextureRegion shipTexture = Game.textureAtlas.findRegion("shipColossal");

	public Boss() {
		super(shipTexture, 12);
		shield = 100;
		currentShield = shield;
		hull = 500;
		currentHull = hull;
		type = TYPES.UNKNOWN;
		weaponType = new LaserTiny1();
		density = 100000;
		impactDamage = 100;
		behavior = BEHAVIOR.BOSS_GET_IN_POSITION;
		speed = 1f;
	}
}
