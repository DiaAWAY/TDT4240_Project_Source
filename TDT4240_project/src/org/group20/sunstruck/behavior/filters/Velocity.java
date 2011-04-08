package org.group20.sunstruck.behavior.filters;

import org.group20.sunstruck.Game;
import org.group20.sunstruck.Main;
import org.group20.sunstruck.behavior.Behavior;
import org.group20.sunstruck.behavior.Behavior.BEHAVIOR;
import org.group20.sunstruck.behavior.Behavior.FILTERS;
import org.group20.sunstruck.gameobject.Boss;
import org.group20.sunstruck.gameobject.GameObject;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

/**
 * Example filter for the Behavior class
 * 
 * @author DiaAWAY
 * 
 */
public class Velocity implements Filter {

	@Override
	public void applyFilter(GameObject go) {
		BEHAVIOR behavior = go.getBehavior();
		if (behavior == BEHAVIOR.LINEAR_MOVEMENT)
			return;

		Vector2 velocity = new Vector2();
		float randomize = (float) Math.random();

		Body body = go.getBody();
		if (behavior == BEHAVIOR.LAUNCHED) {
			float x = (float) Math.cos(body.getAngle());
			float y = (float) Math.sin(body.getAngle());
			velocity.set(x, y);
			velocity.mul(go.getSpeed() * 2);
			body.setLinearVelocity(velocity);

			if (body.getWorldPoint(new Vector2(0, 0)).x <= 0) {
				go.setBURST_COUNT(3);
				go.setBehavior(BEHAVIOR.PLAYER_GRAVITY);
			}

		}

		if (behavior == BEHAVIOR.BOSS_GET_IN_POSITION) {
			Vector2 position = body.getWorldCenter();

			if (position.x < Main.CAMERA_WIDTH / 2 + go.getWidth() / 3.5 - 0.2f)
				velocity.set(go.getSpeed() * 3, 0);
			else if (position.x > Main.CAMERA_WIDTH / 2 + go.getWidth() / 3.5
					+ 0.2f)
				velocity.set(-go.getSpeed(), 0);
			else {
				velocity.set(0, 0);
				go.setBehavior(BEHAVIOR.BOSS_ATTACK);
			}
			body.setLinearVelocity(velocity);
		}
		if (behavior == BEHAVIOR.BOSS_ATTACK) {
			((Boss) go).setCanShoot(true);
			Vector2 position = body.getWorldCenter();

			float scale = (float) Gdx.graphics.getHeight()
					/ Gdx.graphics.getWidth();

			velocity.set(body.getLinearVelocity());
			if (velocity.len() < go.getSpeed()) {
				if (randomize < 0.5)
					velocity.set(0, go.getSpeed() * 2);
				else
					velocity.set(0, -go.getSpeed() * 2);
			} else {
				if (position.y >= Main.CAMERA_WIDTH * scale / 2
						|| position.y <= -Main.CAMERA_WIDTH * scale / 2)
					velocity.mul(-1);
			}
			body.setLinearVelocity(velocity);

			if (randomize < 0.001) {
				// ((Boss) go).setCanShoot(false);
				// go.setBehavior(BEHAVIOR.BOSS_CHARGE);
			}

		}

		if (behavior == BEHAVIOR.BOSS_CHARGE) {
			Vector2 position = body.getWorldCenter();
			if (position.x <= -Main.CAMERA_WIDTH / 2 + go.getWidth() / 2)
				go.setBehavior(BEHAVIOR.BOSS_GET_IN_POSITION);
			velocity.set(-1, 0);
			velocity.mul(go.getSpeed() * 5);

			body.setLinearVelocity(velocity);

		}
		if (behavior == BEHAVIOR.LINE) {
			float angle = body.getAngle();
			float x = (float) Math.cos(angle);
			float y = (float) Math.sin(angle);

			velocity.set(x, y);
			velocity.nor();
			velocity.mul(go.getSpeed());
			body.setLinearVelocity(velocity);
			go.setBehavior(BEHAVIOR.LINEAR_MOVEMENT);
		}

		if (behavior == BEHAVIOR.SPRAY) {
			float y = Main.CAMERA_WIDTH * Gdx.graphics.getHeight()
					/ Gdx.graphics.getWidth();
			// Get the velocity
			randomize = (float) Math.random();
			y = randomize * y - y / 2;
			velocity = new Vector2(-Main.CAMERA_WIDTH / 2, y).sub(
					body.getPosition().x, body.getPosition().y);
			velocity.nor().mul(go.getSpeed());
			body.setLinearVelocity(velocity);
			go.setBehavior(BEHAVIOR.LINEAR_MOVEMENT);
		}

		if (behavior == BEHAVIOR.SIN_VEL) {
			float len = 5 / Main.CAMERA_WIDTH;

			float x = body.getPosition().x;
			float vy = (float) (Math.cos(x * len));

			float a = Main.CAMERA_WIDTH * Gdx.graphics.getHeight()
					/ Gdx.graphics.getWidth();
			a = a / 5f;
			vy = vy * a;

			velocity.set(1, vy);
			velocity.nor();
			velocity.mul(go.getSpeed());
			velocity.mul(-1);

			body.setLinearVelocity(velocity);
			return;

		}

		if (behavior == BEHAVIOR.KAMIKAZE_VEL) {
			velocity = Game.getInstance().getPlayer().getBody()
					.getWorldCenter().tmp().sub(body.getPosition());
			velocity.nor();
			velocity.mul(go.getSpeed());
			body.setLinearVelocity(velocity);
			go.setBehavior(BEHAVIOR.LINEAR_MOVEMENT);
		}

		Behavior.filters.get(FILTERS.FORCE).applyFilter(go);

	}

}
