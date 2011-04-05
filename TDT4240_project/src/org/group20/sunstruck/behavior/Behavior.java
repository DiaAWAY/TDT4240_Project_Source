package org.group20.sunstruck.behavior;

import java.util.HashMap;

import org.group20.sunstruck.behavior.filters.Filter;
import org.group20.sunstruck.behavior.filters.Force;
import org.group20.sunstruck.behavior.filters.Rotation;
import org.group20.sunstruck.behavior.filters.Velocity;
import org.group20.sunstruck.gameobject.GameObject;

/**
 * Behavior handler for GameObjects. This class will apply behaviors to
 * GameObjects, various filters (Filter) will be used to determine what kinds of
 * behaviors to use.
 * 
 * @author Knut Esten
 * 
 */
public class Behavior {
	public static enum BEHAVIOR {
		LINEAR_MOVEMENT, KAMIKAZE_FOR, KAMIKAZE_VEL, SIN_VEL, SIN_FOR, LINE, SPRAY
	}

	public static enum FILTERS {
		FORCE, VELOCITY, ROTATION
	}

	public static HashMap<FILTERS, Filter> filters = new HashMap<FILTERS, Filter>();

	public static void initFilters() {
		filters.put(FILTERS.FORCE, new Force());
		filters.put(FILTERS.VELOCITY, new Velocity());
		filters.put(FILTERS.ROTATION, new Rotation());
	}

	public static void applyBehavior(GameObject go) {
		if (go.getBehavior() == null)
			go.setBehavior(BEHAVIOR.LINE);

		filters.get(FILTERS.VELOCITY).applyFilter(go);

	}
}
