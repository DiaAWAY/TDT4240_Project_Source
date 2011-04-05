package org.group20.sunstruck.gameobject;

import org.group20.sunstruck.Game;
import org.group20.sunstruck.interfaces.GameInterface.DIFFICULTIES;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.PolygonShape;

public class GameObjectFactory {

	private DIFFICULTIES difficulty;

	public GameObjectFactory(DIFFICULTIES difficulty) {
		this.difficulty = difficulty;
	}

	public GameObject createPlayer() {
		GameObject player = new Player();
		generateBoxBody(player, new Vector2(0, 0), 0);

		player.body.setFixedRotation(true);

		return player;
	}

	public GameObject createLaser(GameObject shooter) {
		GameObject laser = new Laser();

		float angle = shooter.getBody().getAngle();
		Vector2 direction = getDirection(shooter);
		Vector2 position = getProjectilePosition(laser, shooter, direction);
		generateBoxBody(laser, position, angle);

		setBulletSpeed(laser, direction);

		laser.body.setFixedRotation(true);

		return laser;
	}

	public GameObject createEnemy(Vector2 position, GameObject enemyType) {
		GameObject enemy = enemyType;
		if (enemy instanceof SmallKamikazeEnemy)
			generateBoxBody(enemy, position, 0);

		return enemy;
	}
	
	public GameObject createAsteroid(Vector2 position, int size){
		GameObject asteroid = new Asteroid(size);
		
		generateCircleBody(asteroid, position);
		
		return asteroid;
	}

	// public GameObject createBoss(Vector2 position) {
	// GameObject boss = new Boss();
	//
	// boss.type = TYPES.ENEMY;
	// boss.textureRegion = BOSS_TEXTURE_REGION;
	// boss.width = BOSS_SIZE;
	// boss.height = BOSS_SIZE;
	// boss.speed = BOSS_START_SPEED;
	// boss.hull = BOSS_START_HULL;
	// boss.weapon = BOSS_START_WEAPON;
	// boss.shield = BOSS_START_SHIELD;
	// boss.impactDamage = BOSS_START_IMPACT_DAMAGE;
	// boss.score = BOSS_SCORE;
	//
	// // Defines the body and creates it
	// BodyDef bodyDef = new BodyDef();
	// bodyDef.type = BodyType.DynamicBody;
	// bodyDef.position.x = position.x;
	// bodyDef.position.y = position.y;
	// boss.body = Game.getInstance().getWorld().createBody(bodyDef);
	// boss.body.setUserData(boss);
	//
	// // Creates the box used for collision, and attaches it to the body.
	// // Disposes of the shape to free memory.
	// PolygonShape bodyPoly = new PolygonShape();
	// bodyPoly.setAsBox(BOSS_SIZE / 2, BOSS_SIZE / 2);
	// boss.body.createFixture(bodyPoly, BOSS_DENSITY);
	// boss.body.setLinearVelocity(new Vector2(-1, 0).mul(boss.speed));
	// bodyPoly.dispose();
	//
	// return boss;
	// }
	//
	// public GameObject createMeteorite(Vector2 position, float size) {
	// GameObject meteorite = new Asteroid();
	//
	// meteorite.hull = METEORITE_START_HULL;
	// meteorite.width = size;
	// meteorite.height = size;
	// meteorite.score = METEORITE_SCORE;
	// meteorite.impactDamage = METEORITE_START_IMPACT_DAMAGE;
	// meteorite.speed = METEORITE_START_SPEED;
	// meteorite.textureRegion = METEORITE_TEXTURE_REGION;
	//
	// BodyDef bodyDef = new BodyDef();
	// bodyDef.type = BodyType.DynamicBody;
	// bodyDef.position.x = position.x;
	// bodyDef.position.y = position.y;
	// meteorite.body = Game.getInstance().getWorld().createBody(bodyDef);
	// meteorite.body.setUserData(meteorite);
	//
	// PolygonShape bodyPoly = new PolygonShape();
	// bodyPoly.setAsBox(size / 2, size / 2);
	// meteorite.body.createFixture(bodyPoly, METEORITE_DENSITY);
	// meteorite.body.setLinearVelocity(new Vector2(-1, 0)
	// .mul(meteorite.speed));
	// bodyPoly.dispose();
	//
	// return meteorite;
	// }

	public void generateWeaponShot(GameObject weaponType, GameObject shooter) {
		GameObject shot = null;
		if (weaponType instanceof Laser)
			shot = createLaser(shooter);
		if (shot != null) {
			if (shooter.isEnemy())
				shot.isEnemy = true;
			else
				shot.isEnemy = false;
		}
	}
	private void generateCircleBody(GameObject go, Vector2 position){
		// Defines the body and creates it
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyType.DynamicBody;
		bodyDef.position.set(position);
		go.body = Game.getInstance().getWorld().createBody(bodyDef);
		
		CircleShape circleShape = new CircleShape();
		circleShape.setRadius(go.width);
		go.body.createFixture(circleShape, go.density);
		circleShape.dispose();
		
		go.body.setUserData(go);
		
	}

	private void generateBoxBody(GameObject go, Vector2 position, float angle) {
		// Defines the body and creates it
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyType.DynamicBody;
		bodyDef.position.set(position);
		bodyDef.angle = angle;
		go.body = Game.getInstance().getWorld().createBody(bodyDef);

		PolygonShape bodyPoly = new PolygonShape();
		bodyPoly.setAsBox(go.width / 2, go.height / 2);
		go.body.createFixture(bodyPoly, go.density);
		bodyPoly.dispose();

		go.body.setUserData(go);

	}

	private void setBulletSpeed(GameObject go, Vector2 direction) {
		if (go instanceof Player)
			go.body.setLinearVelocity(new Vector2(1, 0));
		else
			go.body.setLinearVelocity(direction);
		go.body.setLinearVelocity(go.body.getLinearVelocity().tmp()
				.mul(go.speed));

	}

	private Vector2 getDirection(GameObject go) {
		Vector2 direction = new Vector2(0, 0);
		float angle = go.body.getAngle();
		float x = (float) Math.cos(angle);
		float y = (float) Math.sin(angle);
		direction.set(x, y).mul(-1);

		return direction;
	}

	private Vector2 getProjectilePosition(GameObject projectile,
			GameObject shooter, Vector2 direction) {
		Vector2 position = new Vector2(0, 0);
		float len = shooter.width / 2 + projectile.width / 2 + 0.1f;
		position.set(shooter.getBody().getWorldCenter());
		position.add(direction.tmp().mul(len));

		// if(shooter instanceof Player)
		// position.mul(-1);

		return position;

	}
}
