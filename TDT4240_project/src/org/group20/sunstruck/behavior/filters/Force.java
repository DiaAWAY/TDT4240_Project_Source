package org.group20.sunstruck.behavior.filters;

import org.group20.sunstruck.Game;
import org.group20.sunstruck.behavior.Behavior;
import org.group20.sunstruck.behavior.Behavior.BEHAVIOR;
import org.group20.sunstruck.behavior.Behavior.FILTERS;
import org.group20.sunstruck.gameobject.GameObject;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

public class Force implements Filter {
	public float check = 0;

	@Override
	public void applyFilter(GameObject go) {
		Body body = go.getBody();
		BEHAVIOR behavior = go.getBehavior();
		Vector2 force = new Vector2(0, 0);

		if (behavior == BEHAVIOR.SIN_FOR) {
			force.set(0, 1 - body.getPosition().y);
			force.nor();
			System.out.println(force);
			force.mul(10 * body.getMass());

			// body.applyLinearImpulse(force, body.getWorldCenter());

		}

		if (behavior == BEHAVIOR.PLAYER_GRAVITY) {
			force = Game.getInstance().getPlayer().getBody().getPosition()
					.tmp().sub(body.getPosition());
			force.nor();
			force.mul(7 * body.getMass());
			body.applyForce(force, body.getWorldCenter());
		}
		
		if(behavior == BEHAVIOR.KAMIKAZE_FOR){
			float x = (float) Math.cos(body.getAngle());
			float y = (float) Math.cos(body.getAngle());
			force.set(x, y);
			force.mul(3*body.getMass());
			body.applyForce(force, body.getWorldCenter());
			
		}
			

		Behavior.filters.get(FILTERS.ROTATION).applyFilter(go);

		// Vector2 gravityCenter = body.getWorldCenter();
		// Vector2 force = null;
		// float scalarGravity = 10000;
		// Iterator<Body> bodyIt = Game.getInstance().getWorld().getBodies();
		// Body body = null;
		// while(bodyIt.hasNext()){
		// body = bodyIt.next();
		// if(body.equals(this.body))
		// continue;
		// force = gravityCenter.tmp().sub(body.getWorldCenter());
		// force.mul(1/(Math.abs(force.x)+Math.abs(force.y)));
		// force.mul(scalarGravity);
		// force.mul(body.getMass());
		// body.applyForce(force, body.getWorldCenter());
		// }

	}
}
