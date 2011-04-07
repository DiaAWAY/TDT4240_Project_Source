package org.group20.sunstruck.gameobject;

import org.group20.sunstruck.Game;
import org.group20.sunstruck.Main;
import org.group20.sunstruck.Shop;
import org.group20.sunstruck.behavior.Behavior.BEHAVIOR;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Contact;

public class Player extends GameObject {

	private long startGun = System.currentTimeMillis();
	private long reloadTimeGun = 200;
	private long startBomb = System.currentTimeMillis();
	private long reloadTimeBomb = 1000;
	
	private int weaponLevel = 1;
	private int hullLevel = 0;
	private int shieldLevel = 0;
	private int speedLevel = 0;

	public static TextureRegion shipTexture = Game.textureAtlas
			.findRegion("shipPlayer");

	public Player() {
		super(shipTexture, 1.5f);
		isEnemy = false;
		type = TYPES.PLAYER;
		weaponType = new LaserTiny1();
		hull = 100;
		currentHull = hull;
		shield = 100;
		currentShield = shield;
		speed = 7;
	}

	@Override
	public void update() {
		// System.out
		// .println("Shield: " + currentShield + " Hull: " + currentHull);

		super.shieldRegeneration();

		boolean setXSpeedToZero = false;
		boolean setYSpeedToZero = false;

		body.setLinearVelocity(Game.getInstance().getInput().getNewVelocity());

		if (body.getWorldCenter().x < -Main.CAMERA_WIDTH / 2
				&& body.getLinearVelocity().x < 0)
			setXSpeedToZero = true;
		if (body.getWorldCenter().x > Main.CAMERA_WIDTH / 2
				&& body.getLinearVelocity().x > 0)
			setXSpeedToZero = true;
		if (body.getWorldCenter().y < -Main.CAMERA_WIDTH
				* Gdx.graphics.getHeight() / (2 * Gdx.graphics.getWidth())
				&& body.getLinearVelocity().y < 0)
			setYSpeedToZero = true;
		if (body.getWorldCenter().y > Main.CAMERA_WIDTH
				* Gdx.graphics.getHeight() / (2 * Gdx.graphics.getWidth())
				&& body.getLinearVelocity().y > 0)
			setYSpeedToZero = true;

		if (setXSpeedToZero)
			body.setLinearVelocity(new Vector2(0, body.getLinearVelocity().y));
		if (setYSpeedToZero)
			body.setLinearVelocity(new Vector2(body.getLinearVelocity().x, 0));

		long time = System.currentTimeMillis() - startGun;
		if (time > reloadTimeGun)
			if (Game.getInstance().getInput().getHasFired()) {
				shoot(weaponLevel);
				startGun = System.currentTimeMillis();
			}

		time = System.currentTimeMillis() - startBomb;
		if (time > reloadTimeBomb)
			if (Game.getInstance().getInput().getHasFiredBomb()) {
				fireBomb();
				startBomb = System.currentTimeMillis();
			}

	}

	private void fireBomb() {
		// laser.getBody().setAngularVelocity((float) (Math.random()*100-5));
		// Game.getInstance().getGameObjectList().add(laser);
	}

	@Override
	public void dispose() {
//		System.out.println(score);
		// Game.getInstance().getGameObjectsToBeDestroyed().add((GameObject)this);

	}

	@Override
	public String toString() {
		return "Player";
	}

	private void shoot(int weaponLevel) {
		
		Vector2 centerPosition = GameObjectFactory.getProjectilePosition(
				weaponType, this);
		centerPosition.sub(0, weaponType.height/4);

		if(weaponLevel == 1){
			Game.getInstance().getGoFactory().generateWeaponShot(weaponType, centerPosition, body.getAngle()).isEnemy = false;
			return;
		}
			
		float distance = (height-weaponType.height)/(weaponLevel);
		float angleChange = (float)Math.PI/4;
		if(weaponLevel % 2 == 0)
			angleChange = 2*angleChange/(weaponLevel-2);
		else
			angleChange = 2*angleChange/(weaponLevel-1);
		
		
		if(weaponLevel % 2 == 0){
			Game.getInstance().getGoFactory().generateWeaponShot(weaponType, centerPosition.tmp().add(0,distance), body.getAngle()).isEnemy = false;
			Game.getInstance().getGoFactory().generateWeaponShot(weaponType, centerPosition.tmp().sub(0,distance), body.getAngle()).isEnemy = false;
			for(int j = 1; j < weaponLevel/2; j++)
				Game.getInstance().getGoFactory().generateWeaponShot(weaponType, centerPosition.tmp().add(0,distance*(j+1)), body.getAngle()+angleChange*j).isEnemy = false;
			for(int j = 1; j < weaponLevel/2; j++)
				Game.getInstance().getGoFactory().generateWeaponShot(weaponType, centerPosition.tmp().sub(0,distance*(j+1)), body.getAngle()-angleChange*j).isEnemy = false;
		}else{
			Game.getInstance().getGoFactory().generateWeaponShot(weaponType, centerPosition.tmp(),body.getAngle()).isEnemy = false;
			for(int j = 1; j <= weaponLevel/2; j++)				
				Game.getInstance().getGoFactory().generateWeaponShot(weaponType, centerPosition.tmp().add(0,distance*j), body.getAngle()+angleChange*j).isEnemy = false;
			for(int j = 1; j <= weaponLevel/2; j++)
				Game.getInstance().getGoFactory().generateWeaponShot(weaponType, centerPosition.tmp().sub(0,distance*j), body.getAngle()-angleChange*j).isEnemy = false;
		}
	}
	
	public void addWeaponLevel(int weaponUpgrade) {
		weaponLevel += weaponUpgrade;
		// Do whatever you like.
	}

	public void addHullLevel(int hullUpgrade) {
		hullLevel += hullUpgrade;
		hull = 100 + hullLevel * 50;
	}

	public void addShieldLevel(int shieldUpgrade) {
		shieldLevel += shieldUpgrade;
		shield = 100 + shieldLevel * 50;
	}

	public void addSpeedLevel(int speedUpgrade) {
		speedLevel += speedUpgrade;
		speed = 15 + speedLevel * 5;
	}

	public int getWeaponLevel() {
		return weaponLevel;
	}

	public int getHullLevel() {
		return hullLevel;
	}

	public int getShieldLevel() {
		return shieldLevel;
	}

	public int getSpeedLevel() {
		return speedLevel;
	}
		

}
