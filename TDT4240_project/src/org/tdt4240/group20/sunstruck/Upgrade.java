package org.tdt4240.group20.sunstruck;

import org.tdt4240.group20.sunstruck.gameobject.GameObject;

public class Upgrade {

	public static enum TYPE {
		WEAPON, SHIELD, ARMOUR, SPEED
	}

	public static boolean upgrade(GameObject go, TYPE t, int amount) {
		switch (t) {
		case ARMOUR:
			go.setArmour(go.getArmour()+amount);
			break;
		case SHIELD:
			go.setShield(go.getShield()+amount);
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
