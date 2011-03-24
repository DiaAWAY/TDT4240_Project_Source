package org.group20.sunstruck.gameobject;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;

public abstract class GameObject {

	public static enum TYPES {
		UNKNOWN, ENTITY1, ENTITY2, ENTITY3
	}

	TYPES type = TYPES.UNKNOWN;

	GameObject weaponType = null;
	
	Sprite sprite = null;
	
	//These are not needed as long as we use physics.
	/*
	Vector2 position = new Vector2();
	Vector2 direction = new Vector2();
	*/
	Body body = null;
	
	float speed = 0;
	double armour = 0;
	double weapon = 0;
	double shield = 0;

	public abstract void update();

	public abstract void dispose();

	
	
	public Sprite getSprite(){
		return sprite;
	}
	public void setSprite(Sprite sprite){
		this.sprite = sprite;
	}
	public Body getBody(){
		return body;
	}
	
	public void setBody(Body body){
		this.body = body;
	}
	
	
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


}
