package org.group20.sunstruck;

import org.group20.sunstruck.gameobject.GameObject;

public class Upgrade {

	public static enum TYPE {
		WEAPON, SHIELD, ARMOUR, SPEED
	}

	public static boolean upgrade(GameObject go, TYPE t, int amount) {
		switch (t) {
		case ARMOUR:
			go.setHull((float) (go.getHull()+amount));
			break;
		case SHIELD:
			go.setShield((float) (go.getShield()+amount));
			break;
		case SPEED:
			go.setSpeed(go.getSpeed()+amount);
			break;
		case WEAPON:
			go.setSpeed(go.getSpeed()+amount);
			break;
		default:
			break;
		}
		return false;
	}
}
