package org.group20.sunstruck.gameobject;


import org.group20.sunstruck.Game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
public abstract class GameObject {
	
	public static enum TYPES {
		UNKNOWN, ENTITY1, ENTITY2, ENTITY3
	}

	TYPES type = TYPES.UNKNOWN;

	GameObject weaponType = null;
	
	Body body = null;
	
	float speed = 0;
	float hull = 0;
	float weapon = 0;
	float shield = 0;
	
	//height and width of the body-rectangle.
	float width = 0;
	float height = 0;
	
	public abstract void update();


	public abstract void dispose();	
	
	TextureRegion texture = null;

	public GameObject(Vector2 position, float width, float height, TextureRegion textureRegion, float density, float speed, float hull, float weapon, float shield){
		
		texture = textureRegion;

		//Defines the body and creates it
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyType.DynamicBody;
		bodyDef.position.x = position.x;
		bodyDef.position.y = position.y;
		body = Game.getInstance().getWorld().createBody(bodyDef);
		
		//Creates the box used for collision, and attaches it to the body. Disposes of the shape to free memory.
		PolygonShape bodyPoly = new PolygonShape();
		bodyPoly.setAsBox(width, height);
		body.createFixture(bodyPoly, density);
		bodyPoly.dispose();
		
		this.width 	= width;
		this.height = height;
		this.speed 	= speed;
		this.hull 	= hull;
		this.weapon = weapon;
		this.shield = shield;
	}
	
	public float getWidth(){
		return width;
	}
	public float getHeight(){
		return height;
	}
	public TextureRegion getTexture(){
		return texture;
	}
	
	public Body getBody(){
		return body;
	}
	
	public void setBody(Body body){
		this.body = body;
	}

	public void setType(TYPES type) {
		this.type = type;
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
	

	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}

	public double getHull() {
		return hull;
	}

	public void setHull(float hull) {
		this.hull = hull;
	}

	public double getWeapon() {
		return weapon;
	}

	public void setWeapon(float weapon) {
		this.weapon = weapon;
	}

	public double getShield() {
		return shield;
	}

	public void setShield(float shield) {
		this.shield = shield;
	}
}
