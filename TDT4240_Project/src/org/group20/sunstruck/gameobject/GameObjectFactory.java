package org.group20.sunstruck.gameobject;

import org.group20.sunstruck.Game;
import org.group20.sunstruck.behavior.Behavior.BEHAVIOR;
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

	public Boss createBoss(Vector2 position, float angle) {
		Boss bo = new Boss();
		generateBossBody(bo, position, angle);
		bo.setKamikazeSpawnPoint(new Vector2(bo.width * 0.3193f,
				-bo.height * 0.3456f).add(0, -0.7f));

		bo.body.setFixedRotation(true);
		return bo;
	}

	public Player createPlayer(Vector2 position, float angle) {
		Player player = new Player();
		generateBoxBody(player, position, angle);
		player.body.setFixedRotation(true);
		return player;
	}

	public SmallKamikazeShip createSmallKamikazeShip(Vector2 position,
			float angle) {
		SmallKamikazeShip go = new SmallKamikazeShip();
		generateBoxBody(go, position, angle);
		return go;
	}

	public MediumKamikazeShip createMediumKamikazeShip(Vector2 position,
			float angle) {
		MediumKamikazeShip go = new MediumKamikazeShip();
		generateBoxBody(go, position, angle);
		return go;
	}

	public SmallLaserShip createSmallLaserShip(Vector2 position, float angle) {
		SmallLaserShip go = new SmallLaserShip();
		generateBoxBody(go, position, angle);
		return go;
	}

	public Asteroid createAsteroid(Vector2 position, float angle, int size) {
		Asteroid go = new Asteroid(size);
		generateCircleBody(go, position, angle);
		go.body.setAngularVelocity((float) Math.random());
		return go;
	}

	public LaserTiny1 createLaserTiny1(Vector2 position, float angle) {
		LaserTiny1 go = new LaserTiny1();
		generateBoxBody(go, position, angle);
		go.body.setFixedRotation(true);
		return go;

	}

	public LaserGreen1 createLaserGreen1(Vector2 position, float angle) {
		LaserGreen1 go = new LaserGreen1();
		generateBoxBody(go, position, angle);
		go.body.setFixedRotation(true);
		return go;
	}

	public LaserTiny2 createLaserTiny2(Vector2 position, float angle) {
		LaserTiny2 go = new LaserTiny2();
		generateBoxBody(go, position, angle);
		go.body.setFixedRotation(true);
		return go;
	}

	public GameObject generateWeaponShot(GameObject weaponType,
			Vector2 position, float angle) {
		GameObject go = null;
		if (weaponType instanceof LaserTiny1)
			go = createLaserTiny1(position, angle);
		if (weaponType instanceof LaserTiny2)
			go = createLaserTiny2(position, angle);
		if (weaponType instanceof SmallKamikazeShip) {
			go = createSmallKamikazeShip(position, angle);
			go.score = 0;
		}
		if (weaponType instanceof MediumKamikazeShip)
			go = createMediumKamikazeShip(position, angle);
		if (weaponType instanceof LaserGreen1)
			go = createLaserGreen1(position, angle);

		go.isProjectile = true;

		return go;
	}

	public void createSmallerAsteroids(GameObject shooter) {
		Asteroid asteroid1 = new Asteroid(((Asteroid) shooter).size - 1);
		Asteroid asteroid2 = new Asteroid(((Asteroid) shooter).size - 1);
		Asteroid asteroid3 = new Asteroid(((Asteroid) shooter).size - 1);
		Asteroid asteroid4 = new Asteroid(((Asteroid) shooter).size - 1);

		Vector2 position1 = new Vector2(0, 0);
		Vector2 position2 = new Vector2(0, 0);
		Vector2 position3 = new Vector2(0, 0);
		Vector2 position4 = new Vector2(0, 0);

		position1.set(shooter.body.getWorldCenter().tmp()).add(
				shooter.width / 8, shooter.width / 8);
		position2.set(shooter.body.getWorldCenter().tmp()).add(
				-shooter.width / 8, shooter.width / 8);
		position3.set(shooter.body.getWorldCenter().tmp()).add(
				shooter.width / 8, -shooter.width / 8);
		position4.set(shooter.body.getWorldCenter().tmp()).add(
				-shooter.width / 8, -shooter.width / 8);

		generateCircleBody(asteroid1, position1, 0);
		generateCircleBody(asteroid2, position2, 0);
		generateCircleBody(asteroid3, position3, 0);
		generateCircleBody(asteroid4, position4, 0);

		Vector2 velocity1 = new Vector2(1, 1).nor().mul(shooter.speed);
		Vector2 velocity2 = new Vector2(-1, 1).nor().mul(shooter.speed);
		Vector2 velocity3 = new Vector2(1, -1).nor().mul(shooter.speed);
		Vector2 velocity4 = new Vector2(-1, -1).nor().mul(shooter.speed);

		asteroid1.body.setLinearVelocity(velocity1);
		asteroid2.body.setLinearVelocity(velocity2);
		asteroid3.body.setLinearVelocity(velocity3);
		asteroid4.body.setLinearVelocity(velocity4);

		asteroid1.behavior = BEHAVIOR.LINEAR_MOVEMENT;
		asteroid2.behavior = BEHAVIOR.LINEAR_MOVEMENT;
		asteroid3.behavior = BEHAVIOR.LINEAR_MOVEMENT;
		asteroid4.behavior = BEHAVIOR.LINEAR_MOVEMENT;

	}

	private void generateCircleBody(GameObject go, Vector2 position, float angle) {
		// Defines the body and creates it
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyType.DynamicBody;
		bodyDef.position.set(position);
		bodyDef.angle = angle;
		go.body = Game.getInstance().getWorld().createBody(bodyDef);

		CircleShape circleShape = new CircleShape();
		circleShape.setRadius(go.width / 2);
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

	private void generateBossBody(Boss bo, Vector2 position, float angle) {
		// Defines the body and creates it
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyType.DynamicBody;
		bodyDef.position.set(position);
		bodyDef.angle = angle;
		bo.body = Game.getInstance().getWorld().createBody(bodyDef);

		CircleShape circleShape = new CircleShape();

		circleShape.setRadius(bo.width * 0.1107f);
		circleShape.setPosition(new Vector2(bo.width * 0.3875f, 0));
		bo.body.createFixture(circleShape, bo.density);
		circleShape.dispose();

		PolygonShape bodyPoly = new PolygonShape();

		bodyPoly.setAsBox((bo.width / 2) * 0.6298f, (bo.height / 2) * 0.8203f,
				new Vector2(-bo.width * 0.1868f, bo.height * 0.0875f), 0);
		bo.body.createFixture(bodyPoly, bo.density);

		bodyPoly.setAsBox((bo.width / 2) * 0.1592f, (bo.height / 2) * 0.7051f,
				new Vector2(bo.width * 0.2076f, -bo.height * 0.0253f), 0);
		bo.body.createFixture(bodyPoly, bo.density);
		bodyPoly.dispose();

		bo.body.setUserData(bo);
	}

	public static Vector2 getProjectilePosition(GameObject projectile,
			GameObject shooter) {
		Vector2 direction = new Vector2(0, 0);
		float angle = shooter.body.getAngle();
		float x = (float) Math.cos(angle);
		float y = (float) Math.sin(angle);
		direction.set(x, y);

		Vector2 position = new Vector2(0, 0);
		float len = shooter.width / 2 + projectile.width / 2 + 0.1f;
		position.set(shooter.getBody().getWorldPoint(new Vector2(0, 0)));
		position.add(direction.tmp().mul(len));

		position.add(0.05f, 0.05f);

		return position;
	}
}
