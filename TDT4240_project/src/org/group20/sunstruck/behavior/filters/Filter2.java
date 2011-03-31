package org.group20.sunstruck.behavior.filters;

import org.group20.sunstruck.Game;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

/**
 * Example filter for the Behavior class
 * @author DiaAWAY
 *
 */
public class Filter2 implements Filter {

	@Override
	public void applyFilter(Body body) {
		if(Game.DEBUG) System.out.println("FILTER2 HAS BEEN APPLIED");
		body.setLinearVelocity(new Vector2(-2,0));
	}

}
