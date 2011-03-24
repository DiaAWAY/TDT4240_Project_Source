package org.group20.sunstruck.gameobject;

import org.group20.sunstruck.Game;
import org.group20.sunstruck.gameobject.GameObject.TYPES;

public class GameObjectFactory {

	public GameObjectFactory() {

	}
	
	public GameObject getProductA() {// TODO remove test method
		if(Game.DEBUG) System.out.println("PRODUCING PRODUCT A");
		GameObject o = new ProductA();
		o.setType(TYPES.ENTITY1);
		o.setArmour(10);
		return o;
	}
	
	public GameObject getProductA(String str) { // TODO remove test method
		if(Game.DEBUG) System.out.println("PRODUCING PRODUCT A");
		return new ProductA(str);
	}
	
	public GameObject getProductB() { // TODO remove test method
		if(Game.DEBUG) System.out.println("PRODUCING PRODUCT B");
		GameObject o = new ProductB();
		o.setType(TYPES.ENTITY2);
		return o;
	}
}
