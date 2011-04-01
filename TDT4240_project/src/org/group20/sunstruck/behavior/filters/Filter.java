package org.group20.sunstruck.behavior.filters;

import com.badlogic.gdx.physics.box2d.Body;

/**
 * Interface for the behavior filters used in Behavior
 * @author DiaAWAY
 *
 */
public interface Filter {

	public void applyFilter(Body b);
}
