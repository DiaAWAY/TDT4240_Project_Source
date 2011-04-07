package org.group20.sunstruck.gameobject;

import org.group20.sunstruck.Game;
import org.group20.sunstruck.behavior.Behavior;
import org.group20.sunstruck.behavior.Behavior.BEHAVIOR;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class Boss extends GameObject {
	GameObject weaponType2 = new SmallKamikazeShip();
	Vector2 kamikazeSpawnPoint;
	boolean isSpawningKamikaze;
	public static TextureRegion shipTexture = Game.textureAtlas
			.findRegion("shipColossal");

	public Boss() {
		super(shipTexture, 12);
		shield = 100;
		currentShield = shield;
		hull = 500;
		currentHull = hull;
		type = TYPES.UNKNOWN;
		weaponType = new LaserTiny1();
		density = 100000;
		impactDamage = 100;
		behavior = BEHAVIOR.BOSS_GET_IN_POSITION;
		speed = 1f;
	}

	public void update() {
		Behavior.applyBehavior(this);
		shieldRegeneration();
		if (weaponType != null)
			if (!isProjectile) {
				long time = System.currentTimeMillis() - start;
				if (time > reloadTime) {
					shoot();
					launchKamikazeShip();
					start = System.currentTimeMillis();
				}
			}
	}

	private void shoot() {
		if (BURST_COUNT == 0)
			return;
		shotCount++;
		if (shotCount <= BURST_COUNT || PAUSE_COUNT == 0) {
			Game.getInstance()
					.getGoFactory()
					.generateWeaponShot(
							weaponType,
							GameObjectFactory.getProjectilePosition(weaponType,
									this), this.body.getAngle());
			Game.getInstance()
					.getGoFactory()
					.generateWeaponShot(
							weaponType,
							GameObjectFactory.getProjectilePosition(weaponType,
									this), this.body.getAngle());
			Game.getInstance()
					.getGoFactory()
					.generateWeaponShot(
							weaponType,
							GameObjectFactory.getProjectilePosition(weaponType,
									this), this.body.getAngle());
		} else if (shotCount < BURST_COUNT + PAUSE_COUNT) {
			return;
		} else
			shotCount = 0;

	}

	private void launchKamikazeShip() {
		if (isSpawningKamikaze) {
			Game.getInstance()
					.getGoFactory()
					.generateWeaponShot(new SmallKamikazeShip(),
							body.getWorldPoint(kamikazeSpawnPoint),
							this.body.getAngle());
		} else
			return;
	}

	public Vector2 getKamikazeSpawnPoint() {
		return kamikazeSpawnPoint;
	}

	public void setKamikazeSpawnPoint(Vector2 point) {
		this.kamikazeSpawnPoint = point;
	}

	public void launchKamikazeSquad() {

	}
}
