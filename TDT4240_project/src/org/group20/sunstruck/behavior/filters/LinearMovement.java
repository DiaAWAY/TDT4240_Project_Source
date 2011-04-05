package org.group20.sunstruck.behavior.filters;

import org.group20.sunstruck.Main;
import org.group20.sunstruck.behavior.Behavior.BEHAVIOR;
import org.group20.sunstruck.gameobject.GameObject;
import org.group20.sunstruck.gameobject.GameObject.TYPES;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

/**
 * Example filter for the Behavior class
 * 
 * @author DiaAWAY
 * @author Knut Esten
 */
public class LinearMovement implements Filter {

	@Override
	public void applyFilter(GameObject go) {
		Vector2 velocity = new Vector2();
		float randomize = (float) Math.random();

		TYPES type = go.getType();
		BEHAVIOR behavior = go.getBehavior();
		Body body = go.getBody();

		if (behavior == BEHAVIOR.LINEAR_MOVEMENT)
			return;

		if (behavior == null) {
			if (type == TYPES.BULLET)
				body.setLinearVelocity(body.getLinearVelocity().tmp()
						.mul(go.getSpeed()));
			if (type == TYPES.ENEMY) {
				float y = Main.CAMERA_WIDTH * Gdx.graphics.getHeight()
						/ Gdx.graphics.getWidth();
				y = randomize * y - y / 2;
				velocity = new Vector2(0, y).sub(body.getPosition().x,
						body.getPosition().y);
				velocity.nor().mul(go.getSpeed());
				body.setLinearVelocity(velocity);
			}

			go.setBehavior(BEHAVIOR.LINEAR_MOVEMENT);
		}

	}

}
