package org.tdt4240.group20.sunstruck.gameobject;

import com.badlogic.gdx.scenes.scene2d.actors.Image;

public abstract class GameObject {

	public static enum TYPES {
		UNKNOWN, ENTITY1, ENTITY2, ENTITY3
	}

	TYPES type = TYPES.UNKNOWN;
	int armour = 0;
	int speed = 0;
	int weapon = 0;
	int shield = 0;
	GameObject weaponType = null;
	Image sprite = null;
	int X = 0;
	int Y = 0;

	public abstract void update();

	public abstract void dispose();
	
	public int getX() {
		return X;
	}

	public void setX(int x) {
		X = x;
	}

	public int getY() {
		return Y;
	}

	public void setY(int y) {
		Y = y;
	}

	public TYPES getType() {
		return type;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public int getWeapon() {
		return weapon;
	}

	public void setWeapon(int weapon) {
		this.weapon = weapon;
	}

	public int getShield() {
		return shield;
	}

	public void setShield(int shield) {
		this.shield = shield;
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

	public int getArmour() {
		return this.armour;
	}

	public void setArmour(int a) {
		this.armour = a;
	}

	public void setSprite(Image sprite) {
		this.sprite = sprite;
	}

	public Image getSprite() {
		return sprite;
	}

}
