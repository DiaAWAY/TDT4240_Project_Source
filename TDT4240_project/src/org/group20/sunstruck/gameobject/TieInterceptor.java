package org.group20.sunstruck.gameobject;

import org.group20.sunstruck.Game;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.WorldManifold;

public class TieInterceptor extends GameObject {

	public TieInterceptor(Vector2 position, float width, float height,
			TextureRegion textureRegion, float density, float speed,
			float hull, float weapon, float shield, float impactDamage) {
		super(position, width, height, textureRegion, density, speed, hull,
				weapon, shield, impactDamage);
	}

	@Override
	public void update() {
		//if (Math.random() > 0.95) shoot();
	}

	@Override
	public void dispose() {
		Game.getInstance().getGameObjectsToBeDestroyed().add(this);
	}

	@Override
	public void contact(WorldManifold worldManifold, float impactDamage) {
		if (shield > 0) shield -= impactDamage;
		if (shield < 0) {
			hull -= impactDamage-shield;
			shield = 0;
		}
		if (shield == 0) hull -= impactDamage;
		if (hull < 0) dispose();
	}

	private void shoot() {

		Vector2 pos = body.getWorldCenter().add((float) (width / 2 + 0.6), 0);

		// TODO use gameobjectfactory!
		Projectile laser = new Projectile(pos, 1f, 1f,
				Game.textureAtlas.findRegion("yellowLaser"), 10, 10, 0, 0, 0,
				10);
		Vector2 vel = new Vector2(-1, 0);
		vel.mul(laser.getSpeed());
		laser.getBody().setLinearVelocity(vel);
		// laser.getBody().setAngularVelocity((float) (Math.random()*100-5));
		Game.getInstance().getGameObjectList().add(laser);
	}

}
