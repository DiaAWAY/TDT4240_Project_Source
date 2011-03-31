package org.group20.sunstruck.gameobject;

import org.group20.sunstruck.Game;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.WorldManifold;

/**
 * Example class for products of the GameObjectFactory
 * 
 * @author DiaAWAY
 * 
 */
public class ProductA extends GameObject {

	private String testStr = "";

	public ProductA(Vector2 position, float width, float height,
			TextureRegion textureRegion, float density, float speed,
			float hull, float weapon, float shield) {
		this(position, width, height, textureRegion, density, speed, hull,
				weapon, shield, "default");
	}

	public ProductA(Vector2 position, float width, float height,
			TextureRegion textureRegion, float density, float speed,
			float hull, float weapon, float shield, String string) {
		super(position, width, height, textureRegion, density, speed, hull,
				weapon, shield, shield);
		testStr = string;
		setType(TYPES.ENTITY3);
	}

	public String getTestStr() {
		return testStr;
	}

	@Override
	public void update() {
		if (Game.DEBUG)
			System.out.println("PRODUCT A UPDATING! type:" + getType()
					+ " special product a property:'" + getTestStr() + "'");
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	@Override
	public void contact(WorldManifold worldManifold, float impactDamage) {
		// TODO Auto-generated method stub
		
	}
}
