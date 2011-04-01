package org.group20.sunstruck.behavior.filters;

import org.group20.sunstruck.Main;
import org.group20.sunstruck.behavior.Behavior;
import org.group20.sunstruck.behavior.Behavior.BEHAVIOR;
import org.group20.sunstruck.behavior.Behavior.FILTERS;
import org.group20.sunstruck.gameobject.GameObject;
import org.group20.sunstruck.gameobject.GameObject.TYPES;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

/**
 * Example filter for the Behavior class
 * @author DiaAWAY
 *
 */
public class Velocity implements Filter {

	
	@Override
	public void applyFilter(GameObject go) {
		BEHAVIOR behavior = go.getBehavior();
		if(behavior == BEHAVIOR.LINEAR_MOVEMENT)
			return;
		
		Vector2 velocity = new Vector2();
		float randomize = (float) Math.random();
		
		TYPES type = go.getType();
		Body body = go.getBody();

		if(behavior == BEHAVIOR.LINE){
			go.setBehavior(BEHAVIOR.LINEAR_MOVEMENT);
		}
		
		if(behavior == BEHAVIOR.SPRAY){
			float y = Main.CAMERA_WIDTH*Gdx.graphics.getHeight()/Gdx.graphics.getWidth();
			//Get the velocity
			y = randomize*y - y/2;
			velocity = new Vector2(-Main.CAMERA_WIDTH/2, y).sub(body.getPosition().x, body.getPosition().y);
			velocity.nor().mul(go.getSpeed());
			body.setLinearVelocity(velocity);
		}
		
		if(behavior == BEHAVIOR.SIN_VEL){
			float x = body.getPosition().x;
			float y = (float)Math.sin(x);
			y = y*6;
			
			velocity.set(1, y);
			velocity.mul(go.getSpeed());
			velocity.mul(-1);
			
			
			body.setLinearVelocity(velocity);
			
		}
		
		
		//Behavior.filters.get(FILTERS.FORCE).applyFilter(go);
	
	}


}
