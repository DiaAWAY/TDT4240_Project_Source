package org.group20.sunstruck.behavior.filters;

import org.group20.sunstruck.Game;
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
			go.setBehavior(BEHAVIOR.LINEAR_MOVEMENT);
		}
		
		if(behavior == BEHAVIOR.SIN_VEL){
			float len = 5/Main.CAMERA_WIDTH;
			
			float x = body.getPosition().x;
			float vy = (float)(Math.cos(x*len));
			
			float a = Main.CAMERA_WIDTH*Gdx.graphics.getHeight()/Gdx.graphics.getWidth();
			a = a/5f;
			vy = vy*a;
			
			velocity.set(1, vy);
			velocity.nor();
			velocity.mul(go.getSpeed());
			velocity.mul(-1);
			
			
			body.setLinearVelocity(velocity);
			return;
			
		}
		
		if(behavior == BEHAVIOR.KAMIKAZE_VEL){
			velocity = Game.getInstance().getPlayer().getBody().getWorldCenter().tmp().sub(body.getPosition());
			velocity.nor();
			velocity.mul(go.getSpeed());
			body.setLinearVelocity(velocity);
			go.setBehavior(BEHAVIOR.LINEAR_MOVEMENT);
		}
			
		
		
		Behavior.filters.get(FILTERS.FORCE).applyFilter(go);
	
	}


}
