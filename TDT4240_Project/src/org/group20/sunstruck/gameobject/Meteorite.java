package org.group20.sunstruck.gameobject;

import org.group20.sunstruck.Game;

import com.badlogic.gdx.physics.box2d.WorldManifold;

/**
 * Example class for products of the GameObjectFactory
 * @author DiaAWAY
 *
 */
public class Meteorite extends GameObject { // TODO remove test class


	@Override
	public void contact(WorldManifold worldManifold, float impactDamage) {
		hull -= impactDamage;
		if(hull < 0){
//			Game.getInstance().getWorld().
//			gof.createMeteorite(body.getWorldCenter(), height/2);
//			gof.createMeteorite(body.getWorldCenter(), height/2);
//			gof.createMeteorite(body.getWorldCenter(), height/2);
//			gof.createMeteorite(body.getWorldCenter(), height/2);
			dispose();
		}
		
	}
}
