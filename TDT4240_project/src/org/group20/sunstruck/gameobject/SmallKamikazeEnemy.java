package org.group20.sunstruck.gameobject;

import org.group20.sunstruck.Game;
import org.group20.sunstruck.behavior.Behavior.BEHAVIOR;

import com.badlogic.gdx.physics.box2d.Contact;

public class SmallKamikazeEnemy extends GameObject {

	public SmallKamikazeEnemy() {
		super(Game.textureAtlas.findRegion("shipSmall3"), TYPES.ENEMY,
				BEHAVIOR.KAMIKAZE_VEL, new Laser(), 3, 20, 5, 0,
				10, 1, 250, 2, 4, 1, 10);
	}

	@Override
	public void contact(Contact contact, float impactDamage) {
		dispose();
	}

}
