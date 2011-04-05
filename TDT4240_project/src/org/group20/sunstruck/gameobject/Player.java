package org.group20.sunstruck.gameobject;

import org.group20.sunstruck.Game;
import org.group20.sunstruck.Main;
import org.group20.sunstruck.behavior.Behavior.BEHAVIOR;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.WorldManifold;

public class Player extends GameObject {

	private long startGun = System.currentTimeMillis();
	private long reloadTimeGun = 200;
	private long startBomb = System.currentTimeMillis();
	private long reloadTimeBomb = 1000;
	
	
	
	public Player() {
		super(Game.textureAtlas.findRegion("shipLarge2"), TYPES.ENEMY,
				BEHAVIOR.LINEAR_MOVEMENT, false, false, new Laser(), 15, 20, 5,
				0, 10, 3, 200, 3, 9, 10, 0);

	}

	@Override
	public void update() {

		boolean setXSpeedToZero = false;
		boolean setYSpeedToZero = false;

		body.setLinearVelocity(Game.getInstance().getInput().getNewVelocity());

		if (body.getWorldCenter().x < -Main.CAMERA_WIDTH / 2
				&& body.getLinearVelocity().x < 0)
			setXSpeedToZero = true;
		if (body.getWorldCenter().x > Main.CAMERA_WIDTH / 2
				&& body.getLinearVelocity().x > 0)
			setXSpeedToZero = true;
		if (body.getWorldCenter().y < -Main.CAMERA_WIDTH
				* Gdx.graphics.getHeight() / (2 * Gdx.graphics.getWidth())
				&& body.getLinearVelocity().y < 0)
			setYSpeedToZero = true;
		if (body.getWorldCenter().y > Main.CAMERA_WIDTH
				* Gdx.graphics.getHeight() / (2 * Gdx.graphics.getWidth())
				&& body.getLinearVelocity().y > 0)
			setYSpeedToZero = true;

		if (setXSpeedToZero)
			body.setLinearVelocity(new Vector2(0, body.getLinearVelocity().y));
		if (setYSpeedToZero)
			body.setLinearVelocity(new Vector2(body.getLinearVelocity().x, 0));

		long time = System.currentTimeMillis() - startGun;
		if (time > reloadTimeGun)
			if (Game.getInstance().getInput().getHasFired()) {
				shoot();
				startGun = System.currentTimeMillis();
			}

		time = System.currentTimeMillis() - startBomb;
		if (time > reloadTimeBomb)
			if (Game.getInstance().getInput().getHasFiredBomb()) {
				fireBomb();
				startBomb = System.currentTimeMillis();
			}

	}

	private void fireBomb() {
		// laser.getBody().setAngularVelocity((float) (Math.random()*100-5));
		// Game.getInstance().getGameObjectList().add(laser);
	}

	@Override
	public void dispose() {
		// Game.getInstance().getGameObjectsToBeDestroyed().add((GameObject)this);

	}

	@Override
	public String toString() {
		return "Player";
	}

	private void shoot() {
		Game.getInstance().getGoFactory().generateWeaponShot(weaponType, this);
	}

	@Override
	public void contact(WorldManifold worldManifold, float impactDamage) {
		shield -= impactDamage;
		if (shield < 0) {
			hull += shield;
			shield = 0;
			if (hull < 0)
				dispose();
		}
		System.out.println("Shield: " + shield + " Hull: " + hull);
	}

}
