package org.group20.sunstruck.gameobject;

import java.util.ArrayList;

import org.group20.sunstruck.Game;
import org.group20.sunstruck.behavior.Behavior;
import org.group20.sunstruck.behavior.Behavior.BEHAVIOR;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;

public abstract class GameObject {
	public static ArrayList<TextureRegion> explosionTextures = new ArrayList<TextureRegion>();

	protected GameObject(TextureRegion textureRegion, float width) {
		this.textureRegion = textureRegion;
		this.width = width;
		if (textureRegion != null)
			this.height = width * textureRegion.getRegionHeight()
					/ textureRegion.getRegionWidth();
		
		if (explosionTextures.isEmpty()) {
			explosionTextures.add(Game.textureAtlas
					.findRegion("explosionRed1"));
			explosionTextures.add(Game.textureAtlas
					.findRegion("explosionRed2"));
			explosionTextures.add(Game.textureAtlas
					.findRegion("explosionRed3"));
			explosionTextures.add(Game.textureAtlas
					.findRegion("explosionRed4"));
			explosionTextures.add(Game.textureAtlas
					.findRegion("explosionRed5"));
			explosionTextures.add(Game.textureAtlas
					.findRegion("explosionRed6"));
			explosionTextures.add(Game.textureAtlas
					.findRegion("explosionRed7"));
			explosionTextures.add(Game.textureAtlas
					.findRegion("explosionRed8"));
			explosionTextures.add(Game.textureAtlas
					.findRegion("explosionRed9"));
			explosionTextures.add(Game.textureAtlas
					.findRegion("explosionRed10"));
			explosionTextures.add(Game.textureAtlas
					.findRegion("explosionRed11"));
			explosionTextures.add(Game.textureAtlas
					.findRegion("explosionRed12"));
			explosionTextures.add(Game.textureAtlas
					.findRegion("explosionRed13"));
			explosionTextures.add(Game.textureAtlas
					.findRegion("explosionRed14"));
			explosionTextures.add(Game.textureAtlas
					.findRegion("explosionRed15"));
		}
	}

	public static enum TYPES {
		PLAYER, ENEMY, BULLET, UNKNOWN, ASTEROID
	}

	TYPES type = TYPES.ENEMY;

	BEHAVIOR behavior = BEHAVIOR.LINE;

	boolean isExploding = false;
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
	
	int explosionAnimationCount = 0;
	
	long explosionTime = 200;
	long startExplosionTime = System.currentTimeMillis();
	

	public void update() {
		Behavior.applyBehavior(this);
		shieldRegeneration();
		long time = 0;
			
		
		if (isExploding)
			time = System.currentTimeMillis() - startExplosionTime;
			if(time > explosionTime)
				explode();
		else if (weaponType != null)
			if (!isProjectile) {
				time = System.currentTimeMillis() - start;
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
			if (currentHull <= 0){
				dispose();
			}
		}
	}

	public void dispose() {
		if (!isDisposed) {
			isExploding = true;
			width*=3;
			height = width;
			Game.getInstance().getDestroyedBodiesList().add(body);
		}
	}

	void explode() {
		textureRegion = explosionTextures.get(explosionAnimationCount);
		explosionAnimationCount++;
		if(explosionTextures.size() == explosionAnimationCount)
			isExploding = false;
		
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
	public boolean isExploding(){
		return isExploding;
	}

}
