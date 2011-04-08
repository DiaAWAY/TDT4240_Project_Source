package org.group20.sunstruck.gameobject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

import org.group20.sunstruck.Game;
import org.group20.sunstruck.Main;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

@SuppressWarnings("serial")
public class Player extends GameObject implements Serializable {
	public static ArrayList<TextureRegion> bombExplosionTextures = new ArrayList<TextureRegion>();

	private long startGun = System.currentTimeMillis();
	private long reloadTimeGun = 200;
	private long startBomb = System.currentTimeMillis();
	private long reloadTimeBomb = 60 * 1000;

	private int weaponLevel = 1;
	private int hullLevel = 0;
	private int shieldLevel = 0;
	private int speedLevel = 0;

	private boolean isBombExplosionSize = false;

	private float bombExplosionSizeFactor = 10;

	private int bombExplosionAnimationCount = 0;

	private boolean isBombExploding = false;

	private long startBombExplosionTime = System.currentTimeMillis();

	private long bombExplosionTime = 67;

	private float widthTemp;

	private float heightTemp;

	public static TextureRegion shipTexture = Game.textureAtlas
			.findRegion("shipPlayer");

	public Player() {
		super(shipTexture, 2f);
		isEnemy = false;
		type = TYPES.PLAYER;
		weaponType = new LaserTiny1();
		hull = 100;
		currentHull = hull;
		shield = 100;
		currentShield = shield;
		speed = 7;
		score = 0;
		if (bombExplosionTextures.isEmpty()) {
			bombExplosionTextures.add(Game.textureAtlas
					.findRegion("explosionBlue1"));
			bombExplosionTextures.add(Game.textureAtlas
					.findRegion("explosionBlue2"));
			bombExplosionTextures.add(Game.textureAtlas
					.findRegion("explosionBlue3"));
			bombExplosionTextures.add(Game.textureAtlas
					.findRegion("explosionBlue4"));
			bombExplosionTextures.add(Game.textureAtlas
					.findRegion("explosionBlue5"));
			bombExplosionTextures.add(Game.textureAtlas
					.findRegion("explosionBlue6"));
			bombExplosionTextures.add(Game.textureAtlas
					.findRegion("explosionBlue7"));
			bombExplosionTextures.add(Game.textureAtlas
					.findRegion("explosionBlue8"));
			bombExplosionTextures.add(Game.textureAtlas
					.findRegion("explosionBlue9"));
			bombExplosionTextures.add(Game.textureAtlas
					.findRegion("explosionBlue10"));
			bombExplosionTextures.add(Game.textureAtlas
					.findRegion("explosionBlue11"));
			bombExplosionTextures.add(Game.textureAtlas
					.findRegion("explosionBlue12"));
			bombExplosionTextures.add(Game.textureAtlas
					.findRegion("explosionBlue13"));
			bombExplosionTextures.add(Game.textureAtlas
					.findRegion("explosionBlue14"));
			bombExplosionTextures.add(Game.textureAtlas
					.findRegion("explosionBlue15"));
		}
	}

	@Override
	public void update() {
		// System.out
		// .println("Shield: " + currentShield + " Hull: " + currentHull);
		long time = 0;
		if (isExploding) {
			time = System.currentTimeMillis() - startExplosionTime;
			if (time > explosionTime) {
				explode();
				startExplosionTime = System.currentTimeMillis();
			}
		} else if (isBombExploding) {
			time = System.currentTimeMillis() - startBombExplosionTime;
			if (time > bombExplosionTime) {
				generateBombExplosion();
				startBombExplosionTime = System.currentTimeMillis();
			}
		} else {
			super.shieldRegeneration();

			boolean setXSpeedToZero = false;
			boolean setYSpeedToZero = false;

			body.setLinearVelocity(Game.getInstance().getInput()
					.getNewVelocity());

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
				body.setLinearVelocity(new Vector2(0,
						body.getLinearVelocity().y));
			if (setYSpeedToZero)
				body.setLinearVelocity(new Vector2(body.getLinearVelocity().x,
						0));

			time = System.currentTimeMillis() - startGun;
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

	}

	private void fireBomb() {
		generateBombExplosion();
		Iterator<Body> it = Game.getInstance().getWorld().getBodies();
		Body body = null;
		while (it.hasNext()) {
			body = it.next();
			if (body.getUserData() instanceof Boss
					|| body.getUserData() instanceof Player)
				continue;
			if (body.getUserData() != null)
				((GameObject) body.getUserData()).dispose();
		}
	}

	private void generateBombExplosion() {
		if (!isBombExplosionSize) {
			widthTemp = width;
			heightTemp = height;
			width *= bombExplosionSizeFactor;
			height = width;
			isBombExplosionSize = true;
			isBombExploding = true;
		}
		textureRegion = bombExplosionTextures.get(bombExplosionAnimationCount);
		bombExplosionAnimationCount++;
		if (bombExplosionTextures.size() == bombExplosionAnimationCount) {
			isBombExploding = false;
			isBombExplosionSize = false;
			bombExplosionAnimationCount = 0;
			textureRegion = shipTexture;
			width = widthTemp;
			height = heightTemp;
		}

	}

	@Override
	public void dispose() {
		// System.out.println(score);
		Game.getInstance().getDestroyedBodiesList().add(body);
	}

	@Override
	public String toString() {
		return "Player";
	}

	private void shoot(int weaponLevel) {

		Vector2 centerPosition = GameObjectFactory.getProjectilePosition(
				weaponType, this);
		centerPosition.sub(0, weaponType.height / 4);

		if (weaponLevel == 1) {
			Game.getInstance()
					.getGoFactory()
					.generateWeaponShot(weaponType, centerPosition,
							body.getAngle()).isEnemy = false;
			return;
		}

		float distance = (height - weaponType.height) / (weaponLevel);
		float angleChange = (float) Math.PI / 4;
		if (weaponLevel % 2 == 0)
			angleChange = 2 * angleChange / (weaponLevel - 2);
		else
			angleChange = 2 * angleChange / (weaponLevel - 1);

		if (weaponLevel % 2 == 0) {
			Game.getInstance()
					.getGoFactory()
					.generateWeaponShot(weaponType,
							centerPosition.tmp().add(0, distance),
							body.getAngle()).isEnemy = false;
			Game.getInstance()
					.getGoFactory()
					.generateWeaponShot(weaponType,
							centerPosition.tmp().sub(0, distance),
							body.getAngle()).isEnemy = false;
			for (int j = 1; j < weaponLevel / 2; j++)
				Game.getInstance()
						.getGoFactory()
						.generateWeaponShot(
								weaponType,
								centerPosition.tmp().add(0, distance * (j + 1)),
								body.getAngle() + angleChange * j).isEnemy = false;
			for (int j = 1; j < weaponLevel / 2; j++)
				Game.getInstance()
						.getGoFactory()
						.generateWeaponShot(
								weaponType,
								centerPosition.tmp().sub(0, distance * (j + 1)),
								body.getAngle() - angleChange * j).isEnemy = false;
		} else {
			Game.getInstance()
					.getGoFactory()
					.generateWeaponShot(weaponType, centerPosition.tmp(),
							body.getAngle()).isEnemy = false;
			for (int j = 1; j <= weaponLevel / 2; j++)
				Game.getInstance()
						.getGoFactory()
						.generateWeaponShot(weaponType,
								centerPosition.tmp().add(0, distance * j),
								body.getAngle() + angleChange * j).isEnemy = false;
			for (int j = 1; j <= weaponLevel / 2; j++)
				Game.getInstance()
						.getGoFactory()
						.generateWeaponShot(weaponType,
								centerPosition.tmp().sub(0, distance * j),
								body.getAngle() - angleChange * j).isEnemy = false;
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

	public float getCurrentHull() {
		return currentHull;
	}

	public float getCurrentShield() {
		return currentShield;
	}

}
