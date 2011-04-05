package org.group20.sunstruck.gameobject;

import org.group20.sunstruck.Game;
import org.group20.sunstruck.gameobject.GameObject.TYPES;
import org.group20.sunstruck.interfaces.GameInterface.DIFFICULTIES;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.PolygonShape;

public class GameObjectFactory {
	private final float PLAYER_SIZE = 1;
	private final float PLAYER_DENSITY = 10;
	private final GameObject PLAYER_START_WEAPON_TYPE = new Laser();
	private final float PLAYER_START_SPEED = 10;
	private final float PLAYER_START_HULL = 100;
	private final float PLAYER_START_WEAPON = 10;
	private final float PLAYER_START_SHIELD = 50;
	private final float PLAYER_START_IMPACT_DAMAGE = 10;
	private final TextureRegion PLAYER_START_TEXTURE_REGION = Game.textureAtlas
			.findRegion("TIEBomber");

	private final float LASER_DENSITY = 1;
	private final float LASER_START_WIDTH = 1;
	private final float LASER_START_HEIGHT = 0.1f;
	private final float LASER_START_SPEED = 10;
	private final TextureRegion LASER_TEXTURE_REGION = Game.textureAtlas
			.findRegion("redLaser");

	private final float ENEMY1_DENSITY = 10;
	private final float ENEMY1_SIZE = 2;
	private final GameObject ENEMY1_START_WEAPON_TYPE = new Laser();
	private final float ENEMY1_START_SPEED = 1;
	private final float ENEMY1_START_WEAPON = 10;
	private final float ENEMY1_START_HULL = 30;
	private final float ENEMY1_START_SHIELD = 20;
	private final float ENEMY1_START_IMPACT_DAMAGE = 10;
	private final int ENEMY1_SCORE = 10;
	private final TextureRegion ENEMY1_TEXTURE_REGION = Game.textureAtlas
			.findRegion("enemy1");

	private final float BOSS_DENSITY = 100;
	private final float BOSS_SIZE = 2;
	private final float BOSS_START_SPEED = 1;
	private final float BOSS_START_WEAPON = 10;
	private final float BOSS_START_HULL = 30;
	private final float BOSS_START_SHIELD = 20;
	private final float BOSS_START_IMPACT_DAMAGE = 10;
	private final int BOSS_SCORE = 10;
	private final TextureRegion BOSS_TEXTURE_REGION = Game.textureAtlas
			.findRegion("enemy2");
	
	private final float METEORITE_DENSITY = 100;
	private final float METEORITE_START_SPEED = 0;
	private final float METEORITE_START_HULL = 10;
	private final float METEORITE_START_IMPACT_DAMAGE = 30;
	private final int METEORITE_SCORE = 5;
	private final TextureRegion METEORITE_TEXTURE_REGION = Game.textureAtlas.findRegion("enemy1");

	private DIFFICULTIES difficulty;

	public GameObjectFactory(DIFFICULTIES difficulty) {
		this.difficulty = difficulty;
	}

	public GameObject createPlayer() {
		GameObject player = new Player();

		// Defines the body and creates it
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyType.DynamicBody;
		bodyDef.position.x = 0;
		bodyDef.position.y = 0;
		player.body = Game.getInstance().getWorld().createBody(bodyDef);
		player.body.setUserData(player);

		// Creates the box used for collision, and attaches it to the body.
		// Disposes of the shape to free memory.
		PolygonShape bodyPoly = new PolygonShape();
		bodyPoly.setAsBox(PLAYER_SIZE / 2, PLAYER_SIZE / 2);
		player.body.createFixture(bodyPoly, PLAYER_DENSITY);
		bodyPoly.dispose();

		player.body.setFixedRotation(true);

		player.weaponType = PLAYER_START_WEAPON_TYPE;
		player.type = TYPES.PLAYER;
		player.textureRegion = PLAYER_START_TEXTURE_REGION;
		player.height = PLAYER_SIZE;
		player.width = PLAYER_SIZE;
		player.speed = PLAYER_START_SPEED;
		player.hull = PLAYER_START_HULL;
		player.weapon = PLAYER_START_WEAPON;
		player.shield = PLAYER_START_SHIELD;
		player.impactDamage = PLAYER_START_IMPACT_DAMAGE;

		return player;
	}

	public GameObject createLaser(GameObject shooter) {
		GameObject laser = new Laser();

		laser.type = TYPES.BULLET;
		laser.textureRegion = LASER_TEXTURE_REGION;
		laser.width = LASER_START_WIDTH;
		laser.height = LASER_START_HEIGHT;
		laser.isProjectile = true;
		laser.speed = LASER_START_SPEED;


		float angle = shooter.getBody().getAngle();
		Vector2 direction = new Vector2(0,0);
		
		// Defines the body and creates it
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyType.DynamicBody;
		if(shooter instanceof Player){
			bodyDef.position.x =shooter.body.getWorldCenter().x + shooter.width/ 2 + laser.width / 2 + 0.1f;
			bodyDef.position.y = shooter.body.getWorldCenter().y;
		}else{
			float len = shooter.width/ 2 + laser.width / 2 + 0.1f;
			float x = (float)Math.cos(angle);
			float y = (float)Math.sin(angle);
			direction.set(x, y).mul(-1);
			
			bodyDef.position.set(shooter.getBody().getWorldCenter());
			bodyDef.position.add(direction.tmp().mul(len));
			
			bodyDef.angle = (float) (angle);
		}
		laser.body = Game.getInstance().getWorld().createBody(bodyDef);
		laser.body.setUserData(laser);

		// Creates the box used for collision, and attaches it to the body.
		// Disposes of the shape to free memory.
		PolygonShape bodyPoly = new PolygonShape();
		bodyPoly.setAsBox(LASER_START_WIDTH / 2, LASER_START_HEIGHT / 2);
		laser.body.createFixture(bodyPoly, LASER_DENSITY);
		bodyPoly.dispose();

		if (shooter instanceof Player)
			laser.body.setLinearVelocity(new Vector2(1, 0));
		else
			laser.body.setLinearVelocity(direction);
			
		
		laser.body.setLinearVelocity(laser.body.getLinearVelocity().tmp().mul(
				laser.speed));
		laser.impactDamage = shooter.weapon;
		laser.body.setFixedRotation(true);
		return laser;
	}

	public GameObject createEnemy1(Vector2 position) {
		GameObject enemy1 = new Enemy1();

		enemy1.type = TYPES.ENEMY;
		enemy1.textureRegion = ENEMY1_TEXTURE_REGION;
		enemy1.width = ENEMY1_SIZE;
		enemy1.height = ENEMY1_SIZE;
		enemy1.speed = ENEMY1_START_SPEED;
		enemy1.hull = ENEMY1_START_HULL;
		enemy1.weapon = ENEMY1_START_WEAPON;
		enemy1.shield = ENEMY1_START_SHIELD;
		enemy1.impactDamage = ENEMY1_START_IMPACT_DAMAGE;
		enemy1.score = ENEMY1_SCORE;
		enemy1.weaponType = ENEMY1_START_WEAPON_TYPE;

		// Defines the body and creates it
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyType.DynamicBody;
		bodyDef.position.x = position.x;
		bodyDef.position.y = position.y;
		enemy1.body = Game.getInstance().getWorld().createBody(bodyDef);
		enemy1.body.setUserData(enemy1);

		// Creates the box used for collision, and attaches it to the body.
		// Disposes of the shape to free memory.
		PolygonShape bodyPoly = new PolygonShape();
		bodyPoly.setAsBox(ENEMY1_SIZE / 2, ENEMY1_SIZE / 2);
		enemy1.body.createFixture(bodyPoly, ENEMY1_DENSITY);
		enemy1.body.setLinearVelocity(new Vector2(-1, 0).mul(enemy1.speed));
		bodyPoly.dispose(); 

		return enemy1;
	}

	public GameObject createBoss(Vector2 position) {
		GameObject boss = new Boss();

		boss.type = TYPES.ENEMY;
		boss.textureRegion = BOSS_TEXTURE_REGION;
		boss.width = BOSS_SIZE;
		boss.height = BOSS_SIZE;
		boss.speed = BOSS_START_SPEED;
		boss.hull = BOSS_START_HULL;
		boss.weapon = BOSS_START_WEAPON;
		boss.shield = BOSS_START_SHIELD;
		boss.impactDamage = BOSS_START_IMPACT_DAMAGE;
		boss.score = BOSS_SCORE;

		// Defines the body and creates it
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyType.DynamicBody;
		bodyDef.position.x = position.x;
		bodyDef.position.y = position.y;
		boss.body = Game.getInstance().getWorld().createBody(bodyDef);
		boss.body.setUserData(boss);

		// Creates the box used for collision, and attaches it to the body.
		// Disposes of the shape to free memory.
		PolygonShape bodyPoly = new PolygonShape();
		bodyPoly.setAsBox(BOSS_SIZE / 2, BOSS_SIZE / 2);
		boss.body.createFixture(bodyPoly, BOSS_DENSITY);
		boss.body.setLinearVelocity(new Vector2(-1, 0).mul(boss.speed));
		bodyPoly.dispose();

		return boss;
	}
	
	public GameObject createMeteorite(Vector2 position, float size){
		GameObject meteorite = new Meteorite();
		
		meteorite.hull = METEORITE_START_HULL;
		meteorite.width = size;
		meteorite.height = size;
		meteorite.score = METEORITE_SCORE;
		meteorite.impactDamage = METEORITE_START_IMPACT_DAMAGE;
		meteorite.speed = METEORITE_START_SPEED;
		meteorite.textureRegion = METEORITE_TEXTURE_REGION;
		
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyType.DynamicBody;
		bodyDef.position.x = position.x;
		bodyDef.position.y = position.y;
		meteorite.body = Game.getInstance().getWorld().createBody(bodyDef);
		meteorite.body.setUserData(meteorite);
		
		PolygonShape bodyPoly = new PolygonShape();
		bodyPoly.setAsBox(size/2, size/2);
		meteorite.body.createFixture(bodyPoly, METEORITE_DENSITY);
		meteorite.body.setLinearVelocity(new Vector2(-1, 0).mul(meteorite.speed));
		bodyPoly.dispose();
		
		return meteorite;
	}

	public void generateWeaponShot(GameObject weaponType, GameObject shooter) {
		if (weaponType instanceof Laser)
			createLaser(shooter);
	}
}
