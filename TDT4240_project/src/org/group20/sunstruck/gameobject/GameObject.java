package org.group20.sunstruck.gameobject;

import org.group20.sunstruck.Game;
import org.group20.sunstruck.behavior.Behavior;
import org.group20.sunstruck.behavior.Behavior.BEHAVIOR;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.WorldManifold;

public abstract class GameObject {

	public static enum TYPES {
		PLAYER, ENEMY, METEORITE, BULLET, UNKNOWN
	}

	TYPES type = TYPES.UNKNOWN;
	BEHAVIOR behavior = BEHAVIOR.LINE;
	
	boolean isDisposed = false;
	GameObject weaponType = null;
	float speed = 0;
	float hull = 0;
	float weapon = 0;
	float shield = 0;
	float impactDamage = 0;

	// height and width of the body-rectangle.
	float width = 0;
	float height = 0;

	Body body = null;

	public void update(){
		Behavior.applyBehavior(this);
	}

	public abstract void contact(WorldManifold worldManifold, float impactDamage);

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

	public BEHAVIOR getBehavior() {
		return behavior;
	}

	public void setBehavior(BEHAVIOR behavior) {
		this.behavior = behavior;
	}
	

}
