package org.group20.sunstruck.gameobject;

import java.util.Iterator;

import org.group20.sunstruck.Game;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.WorldManifold;

public class Projectile extends GameObject{
	public boolean isBomb = false;
	
	public Projectile(Vector2 position, float width, float height,
			TextureRegion textureRegion, float density, float speed,
			float hull, float weapon, float shield, float impactDamage) {
		super(position, width, height, textureRegion, density, speed, hull, weapon,
				shield, impactDamage);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void dispose() {
		Game.getInstance().getGameObjectsToBeDestroyed().add((GameObject)this);
	}
	
	public String toString(){
		return "Shot";
	}

	@Override
	public void update() {
		if(isBomb){
			Vector2 gravityCenter = body.getWorldCenter();
			Vector2 force = null;
			float scalarGravity = 10;
			Iterator<Body> bodyIt = Game.getInstance().getWorld().getBodies();
			Body body = null;
			while(bodyIt.hasNext()){
				body = bodyIt.next();
				if(body.equals(this.body))
					continue;
				force = gravityCenter.tmp().sub(body.getWorldCenter());
				force.mul(1/(Math.abs(force.x)+Math.abs(force.y)));
				force.mul(scalarGravity);
				force.mul(body.getMass());
				body.applyForce(force, body.getWorldCenter());
			}
		}
		
	}

	@Override
	public void contact(WorldManifold worldManifold, float impactDamage) {
		dispose();
		
	}

	
}