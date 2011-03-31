package org.group20.sunstruck.behavior.filters;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

/**
 * Example filter for the Behavior class
 * @author DiaAWAY
 *
 */
public class Filter2 implements Filter {

	private double d = Math.PI/2;
	private double up = Math.PI/2;
	private double down = (3*Math.PI)/2;
	
	@Override
	public void applyFilter(Body body) {
		Vector2 direction = new Vector2(0, (float) Math.sin(d));
		body.setLinearVelocity(new Vector2(-1, 0));
		body.applyLinearImpulse(direction, body.getPosition());
		
		if (d == up) {
			d = down;
		} else {
			d = up;
		}
	}

}
