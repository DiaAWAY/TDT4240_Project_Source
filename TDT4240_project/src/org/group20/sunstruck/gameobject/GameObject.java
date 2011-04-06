package org.group20.sunstruck.gameobject;

import org.group20.sunstruck.Game;
import org.group20.sunstruck.behavior.Behavior;
import org.group20.sunstruck.behavior.Behavior.BEHAVIOR;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;

public abstract class GameObject {

	protected GameObject(TextureRegion textureRegion, TYPES type,
			BEHAVIOR behavior, GameObject weaponType, float speed,
			float impactDamage, float hull, float weapon, float shield,
			float width, float reloadTime, int BURST_COUNT, int PAUSE_COUNT,
			float density, int score) {
		this.textureRegion = textureRegion;
		this.type = type;
		this.behavior = behavior;
		this.weaponType = weaponType;
		this.speed = speed;
		this.hull = hull;
		this.weapon = weapon;
		this.shield = shield;
		this.impactDamage = impactDamage;
		this.width = width;
		if (textureRegion != null)
			this.height = width * textureRegion.getRegionHeight()
					/ textureRegion.getRegionWidth();
		this.density = density;
		this.BURST_COUNT = BURST_COUNT;
		this.PAUSE_COUNT = PAUSE_COUNT;
		this.score = score;
	}

	public static enum TYPES {
		PLAYER, ENEMY, BULLET, UNKNOWN, ASTEROID
	}

	TYPES type = TYPES.UNKNOWN;

	BEHAVIOR behavior = BEHAVIOR.LINE;

	boolean isProjectile = false;
	boolean isDisposed = false;
	boolean isEnemy = true;
	GameObject weaponType = null;
	float speed = 0;
	float hull = 0;
	float weapon = 0;
	float shield = 0;
	float impactDamage = 0;
	int score = 0;

	long reloadTime = 200;
	long start = System.currentTimeMillis();
	int shotCount = 0;

	int BURST_COUNT = 3;
	int PAUSE_COUNT = 9;

	float density = 0;

	// height and width of the body-rectangle.
	float width = 0;
	float height = 0;

	Body body = null;

	public void update() {
		Behavior.applyBehavior(this);
		if (!isProjectile) {
			long time = System.currentTimeMillis() - start;
			// if (behavior != BEHAVIOR.KAMIKAZE_FOR)
			if (time > reloadTime) {
				shoot();
				start = System.currentTimeMillis();
			}
		}
	}

	private void shoot() {
		if (BURST_COUNT == 0)
			return;
		shotCount++;
		if (shotCount <= BURST_COUNT || PAUSE_COUNT == 0) {
			Game.getInstance().getGoFactory()
					.generateWeaponShot(weaponType, GameObjectFactory.getProjectilePosition(weaponType, this), this.body.getAngle());
		} else if (shotCount < BURST_COUNT + PAUSE_COUNT) {
			return;
		} else
			shotCount = 0;

	}

	public abstract void contact(Contact contact, float impactDamage);

	public void dispose() {
		if (!isDisposed) {
			Game.getInstance().getDestroyedBodiesList().add(body);
			isDisposed = true;
		}
	}

	TextureRegion textureRegion = null;

	public TYPES getType() {
		return type;
	}

	public GameObject getWeaponType() {
		return weaponType;
	}

	public float getSpeed() {
		return speed;
	}

	public float getHull() {
		return hull;
	}

	public float getWeapon() {
		return weapon;
	}

	public float getShield() {
		return shield;
	}

	public float getImpactDamage() {
		return impactDamage;
	}

	public float getWidth() {
		return width;
	}

	public float getHeight() {
		return height;
	}

	public TextureRegion getTexture() {
		return textureRegion;
	}

	public Body getBody() {
		return body;
	}

	public void setWeaponType(GameObject weaponType) {
		this.weaponType = weaponType;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}

	public void setHull(float hull) {
		this.hull = hull;
	}

	public void setWeapon(float weapon) {
		this.weapon = weapon;
	}

	public void setShield(float shield) {
		this.shield = shield;
	}

	public void setImpactDamage(float impactDamage) {
		this.impactDamage = impactDamage;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getScore() {
		return score;
	}

	public boolean isDisposed() {
		return isDisposed;
	}

	public boolean isProjectile() {
		return isProjectile;
	}

	public boolean isEnemy() {
		return isEnemy;
	}

	public BEHAVIOR getBehavior() {
		return behavior;
	}

	public void setBehavior(BEHAVIOR behavior) {
		this.behavior = behavior;
	}

}
