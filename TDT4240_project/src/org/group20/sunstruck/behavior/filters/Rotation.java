package org.group20.sunstruck.behavior.filters;

import org.group20.sunstruck.Game;
import org.group20.sunstruck.behavior.Behavior.BEHAVIOR;
import org.group20.sunstruck.gameobject.GameObject;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

public class Rotation implements Filter {

	@Override
	public void applyFilter(GameObject go) {
		BEHAVIOR behavior = go.getBehavior();
		Body body = go.getBody();

		if (behavior == BEHAVIOR.KAMIKAZE_FOR)
			return;
		if (behavior == BEHAVIOR.BOSS_GET_IN_POSITION)
			return;
		if (behavior == BEHAVIOR.BOSS_ATTACK)
			return;
		if (behavior == BEHAVIOR.BOSS_CHARGE)
			return;
		if(behavior == BEHAVIOR.LAUNCHED)
			return;

		if (behavior == BEHAVIOR.LINEAR_MOVEMENT) {
			Vector2 direction = body.getLinearVelocity();
			direction.nor();
			float angle = (float) Math.acos(direction.x);
			if (direction.y < 0)
				angle = (float) (Math.PI * 2 - angle);
			body.setTransform(body.getWorldCenter(), angle);

		} else {
			Vector2 direction = Game.getInstance().getPlayer().getBody()
					.getWorldCenter().tmp();
			direction.sub(body.getWorldCenter());
			direction.nor();
			float angle = (float) Math.acos(direction.x);
			if (direction.y < 0)
				angle = (float) (Math.PI * 2 - angle);

			body.setTransform(body.getWorldCenter(), angle);

			if (behavior == BEHAVIOR.KAMIKAZE_FOR_ANGLE)
				go.setBehavior(BEHAVIOR.KAMIKAZE_FOR);
		}

	}

}
