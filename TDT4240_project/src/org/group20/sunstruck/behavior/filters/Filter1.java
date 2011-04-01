package org.group20.sunstruck.behavior.filters;

import org.group20.sunstruck.Game;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

/**
 * Example filter for the Behavior class
 * @author DiaAWAY
 *
 */
public class Filter1 implements Filter {

	@Override
	public void applyFilter(Body body) {
		Vector2 player = Game.getInstance().getPlayer().getBody().getWorldCenter();
		Vector2 direction = player.sub(body.getWorldCenter());
		direction.nor();
		//direction.mul(2);
		body.applyLinearImpulse(direction, body.getWorldCenter());
	}

}
