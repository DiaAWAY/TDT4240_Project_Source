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
	boolean canShoot = false;
	long spawnTime = 350;
	long start2 = System.currentTimeMillis();
	int SPAWNBURST_COUNT = 3;
	int SPAWNPAUSE_COUNT = 24;
	int spawnCount = 0;

	public static TextureRegion shipTexture = Game.textureAtlas
			.findRegion("shipColossal");

	public Boss() {
		super(shipTexture, 10);
		shield = 100;
		currentShield = shield;
		hull = 500;
		currentHull = hull;
		type = TYPES.UNKNOWN;
		weaponType = new LaserGreen1();
		density = 100000;
		impactDamage = 100;
		behavior = BEHAVIOR.BOSS_GET_IN_POSITION;
		speed = 1f;
		explosionSizeFactor = 1.2f;
	}

	public void update() {
		Behavior.applyBehavior(this);
		long time = 0;
		if (isExploding)
			time = System.currentTimeMillis() - startExplosionTime;
		if (time > explosionTime) {
			explode();
			startExplosionTime = System.currentTimeMillis();
		} else {
			shieldRegeneration();
			time = System.currentTimeMillis() - start;
			if (time > reloadTime) {
				shoot();
				start = System.currentTimeMillis();
			}
			time = System.currentTimeMillis() - start2;
			if (time > spawnTime) {
				launchKamikazeShip();
				start2 = System.currentTimeMillis();
			}
		}
	}

	@Override
	void shoot() {
		if (canShoot) {
			if (BURST_COUNT == 0)
				return;
			shotCount++;
			if (shotCount <= BURST_COUNT || PAUSE_COUNT == 0) {
				Vector2 shotPosition = GameObjectFactory.getProjectilePosition(
						weaponType, this);
				Game.getInstance()
						.getGoFactory()
						.generateWeaponShot(weaponType,
								shotPosition.tmp().add(0, 0.6f),
								this.body.getAngle());
				Game.getInstance()
						.getGoFactory()
						.generateWeaponShot(weaponType, shotPosition.tmp(),
								this.body.getAngle());
				Game.getInstance()
						.getGoFactory()
						.generateWeaponShot(weaponType,
								shotPosition.tmp().sub(0, 0.6f),
								this.body.getAngle());
			} else if (shotCount < BURST_COUNT + PAUSE_COUNT) {
				return;
			} else
				shotCount = 0;
		}

	}

	private void launchKamikazeShip() {
		if (canShoot) {
			if (SPAWNBURST_COUNT == 0)
				return;
			spawnCount++;
			if (spawnCount <= SPAWNBURST_COUNT || SPAWNPAUSE_COUNT == 0) {
				Game.getInstance()
						.getGoFactory()
						.generateWeaponShot(new SmallKamikazeShip(),
								body.getWorldPoint(kamikazeSpawnPoint),
								(float) (this.body.getAngle() - Math.PI / 4))
						.setBehavior(BEHAVIOR.LAUNCHED);
			} else if (spawnCount < SPAWNBURST_COUNT + SPAWNPAUSE_COUNT) {
				return;
			} else
				spawnCount = 0;
		}
	}

	public Vector2 getKamikazeSpawnPoint() {
		return kamikazeSpawnPoint;
	}

	public void setKamikazeSpawnPoint(Vector2 point) {
		this.kamikazeSpawnPoint = point;
	}

	public boolean getCanShoot() {
		return canShoot;
	}

	public void setCanShoot(boolean bool) {
		this.canShoot = bool;
	}
}
