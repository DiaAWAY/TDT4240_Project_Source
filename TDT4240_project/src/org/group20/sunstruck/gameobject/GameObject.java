package org.group20.sunstruck.gameobject;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.actors.Image;

public abstract class GameObject {

	public static enum TYPES {
		UNKNOWN, ENTITY1, ENTITY2, ENTITY3
	}

	TYPES type = TYPES.UNKNOWN;

	GameObject weaponType = null;
	Image sprite = null;
	Vector2 position = new Vector2();
	Vector2 direction = new Vector2();
	float speed = 0;
	double armour = 0;
	double weapon = 0;
	double shield = 0;

	public abstract void update();

	public abstract void dispose();

	public TYPES getType() {
		return type;
	}

	public GameObject getWeaponType() {
		return weaponType;
	}

	public void setWeaponType(GameObject weaponType) {
		this.weaponType = weaponType;
	}

	public void setType(TYPES t) {
		this.type = t;
	}

	public Vector2 getPosition() {
		return position;
	}

	public void setPosition(Vector2 position) {
		this.position = position;
	}

	public Vector2 getDirection() {
		return direction;
	}

	public void setDirection(Vector2 direction) {
		this.direction = direction;
	}

	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}

	public double getArmour() {
		return armour;
	}

	public void setArmour(double armour) {
		this.armour = armour;
	}

	public double getWeapon() {
		return weapon;
	}

	public void setWeapon(double weapon) {
		this.weapon = weapon;
	}

	public double getShield() {
		return shield;
	}

	public void setShield(double shield) {
		this.shield = shield;
	}

	public void setSprite(Image sprite) {
		this.sprite = sprite;
	}

	public Image getSprite() {
		return sprite;
	}

}
