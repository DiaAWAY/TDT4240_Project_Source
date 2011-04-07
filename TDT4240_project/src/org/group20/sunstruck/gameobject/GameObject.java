package org.group20.sunstruck.gameobject;

import org.group20.sunstruck.Game;
import org.group20.sunstruck.behavior.Behavior;
import org.group20.sunstruck.behavior.Behavior.BEHAVIOR;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;

public abstract class GameObject {

	protected GameObject(TextureRegion textureRegion, float width) {
		this.textureRegion = textureRegion;
		this.width = width;
		if (textureRegion != null)
			this.height = width * textureRegion.getRegionHeight()
					/ textureRegion.getRegionWidth();
	}

	public static enum TYPES {
		PLAYER, ENEMY, BULLET, UNKNOWN, ASTEROID
	}

	TYPES type = TYPES.ENEMY;

	BEHAVIOR behavior = BEHAVIOR.LINE;

	boolean isProjectile = false;
	boolean isDisposed = false;
	boolean isEnemy = true;
	GameObject weaponType = null;
	float speed = 5;
	float hull = 10;
	float shield = 10;
	float impactDamage = 10;
	int score = 10;

	long reloadTime = 200;
	long start = System.currentTimeMillis();
	int shotCount = 0;

	int BURST_COUNT = 3;
	int PAUSE_COUNT = 9;

	float density = 10;

	// height and width of the body-rectangle.
	float width = 0;
	float height = 0;

	Body body = null;

	float currentShield = shield;
	float currentHull = hull;

	long shieldRegenTime = 500;
	long lastShieldRegen = System.currentTimeMillis();

	public void update() {
		Behavior.applyBehavior(this);
		shieldRegeneration();
		if (weaponType != null)
			if (!isProjectile) {
				long time = System.currentTimeMillis() - start;
				if (time > reloadTime) {
					shoot();
					start = System.currentTimeMillis();
				}
			}
	}

	void shieldRegeneration() {
		if (currentShield < shield) {
			long time = System.currentTimeMillis() - lastShieldRegen;
			if (time > shieldRegenTime) {
				currentShield++;
				lastShieldRegen = System.currentTimeMillis();
			}
		}
	}

	private void shoot() {
		if (BURST_COUNT == 0)
			return;
		shotCount++;
		if (shotCount <= BURST_COUNT || PAUSE_COUNT == 0) {
			Game.getInstance().getGoFactory().generateWeaponShot(weaponType,
					GameObjectFactory.getProjectilePosition(weaponType, this),
					this.body.getAngle());
		} else if (shotCount < BURST_COUNT + PAUSE_COUNT) {
			return;
		} else
			shotCount = 0;

	}

	public void contact(Contact contact, float impactDamage) {
		currentShield -= impactDamage;
		if (currentShield < 0) {
			currentHull += currentShield;
			currentShield = 0;
			if (currentHull <= 0)
				dispose();
		}
	}

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
