package org.group20.sunstruck.gameobject;

import org.group20.sunstruck.Game;
import org.group20.sunstruck.behavior.Behavior.BEHAVIOR;

import com.badlogic.gdx.physics.box2d.Contact;

public class Boss extends GameObject {
	GameObject weaponType2 = new SmallKamikazeShip();

	public Boss() {
		super(Game.textureAtlas.findRegion("shipColossal"), 6);
		shield = 100;
		currentShield = shield;
		hull = 500;
		currentHull = hull;
		type = TYPES.UNKNOWN;
		weaponType = new LaserTiny1();
		density = 100000;
		impactDamage = 100;
		behavior = BEHAVIOR.LINE;
		speed = 0.1f;
	}
}
