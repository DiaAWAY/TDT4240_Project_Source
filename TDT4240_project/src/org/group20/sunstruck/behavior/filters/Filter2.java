package org.group20.sunstruck.behavior.filters;

import org.group20.sunstruck.Game;
import org.group20.sunstruck.gameobject.GameObject;

/**
 * Example filter for the Behavior class
 * @author DiaAWAY
 *
 */
public class Filter2 implements Filter {

	@Override
	public void applyFilter(GameObject go) {
		// TODO Auto-generated method stub
		if(Game.DEBUG) System.out.println("FILTER2 HAS BEEN APPLIED");
	}

}
