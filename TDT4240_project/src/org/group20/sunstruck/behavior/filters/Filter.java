package org.group20.sunstruck.behavior.filters;

import org.group20.sunstruck.gameobject.GameObject;

import com.badlogic.gdx.physics.box2d.Body;

/**
 * Interface for the behavior filters used in Behavior
 * @author DiaAWAY
 *
 */
public interface Filter {


	public void applyFilter(GameObject go);
}
