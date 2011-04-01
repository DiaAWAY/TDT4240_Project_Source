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
	private final float LASER_START_SPEED = 100;
	private final TextureRegion LASER_TEXTURE_REGION = Game.textureAtlas
			.findRegion("redLaser");

	private final float ENEMY1_DENSITY = 100;
	private final float ENEMY1_SIZE = 2;
	private final float ENEMY1_START_SPEED = 10;
	private final float ENEMY1_START_WEAPON = 10;
	private final float ENEMY1_START_HULL = 30;
	private final float ENEMY1_START_SHIELD = 20;
	private final float ENEMY1_START_IMPACT_DAMAGE = 10;
	private final TextureRegion ENEMY1_TEXTURE_REGION = Game.textureAtlas
			.findRegion("enemy1");

	private DIFFICULTIES difficulty;

	public GameObjectFactory(DIFFICULTIES difficulty) {
		this.difficulty = difficulty;
	}

	public GameObject getPlayer() {
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

	public GameObject getLaser(GameObject shooter) {
		GameObject laser = new Laser();

		laser.type = TYPES.BULLET;
		laser.textureRegion = LASER_TEXTURE_REGION;
		laser.width = LASER_START_WIDTH;
		laser.height = LASER_START_HEIGHT;

		// Defines the body and creates it
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyType.DynamicBody;
		bodyDef.position.x = shooter.body.getWorldCenter().x + shooter.width
				/ 2 + laser.width / 2 + 0.1f;
		bodyDef.position.y = shooter.body.getWorldCenter().y;
		laser.body = Game.getInstance().getWorld().createBody(bodyDef);
		laser.body.setUserData(laser);

		// Creates the box used for collision, and attaches it to the body.
		// Disposes of the shape to free memory.
		PolygonShape bodyPoly = new PolygonShape();
		bodyPoly.setAsBox(LASER_START_WIDTH / 2, LASER_START_HEIGHT / 2);
		laser.body.createFixture(bodyPoly, LASER_DENSITY);
		bodyPoly.dispose();

		if(shooter instanceof Player)	
			laser.body.setLinearVelocity(new Vector2(1, 0));
		else
			laser.body.setLinearVelocity(new Vector2(-1,0));
		laser.body.setLinearVelocity(laser.body.getLinearVelocity().tmp().mul(laser.speed));
		laser.impactDamage = shooter.weapon;

		return laser;
	}

	public GameObject getEnemy1(Vector2 position) {
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

	public void generateWeaponShot(GameObject weaponType, GameObject shooter) {
		if (weaponType instanceof Laser)
			getLaser(shooter);
	}
}
