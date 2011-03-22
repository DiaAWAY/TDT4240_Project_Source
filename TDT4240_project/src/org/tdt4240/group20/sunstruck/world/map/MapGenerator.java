package org.tdt4240.group20.sunstruck.world.map;

import java.util.ArrayList;

import org.tdt4240.group20.sunstruck.world.map.segments.*;

public class MapGenerator {

	private MapSegment lastSegment = null;
	private ArrayList<MapSegment> segments = new ArrayList<MapSegment>();
	
	public MapGenerator() {
		initMapSegments();
	}
	
	private void initMapSegments() {
		segments.add(new MapSegment1());
	}
	
	/**
	 * returns the next segment 
	 * @return
	 */
	public MapSegment getNext() {
		int index = (int)(Math.random()*(segments.size()-1));
		lastSegment = segments.get(index);
		return segments.get(index); // placeholder
	}
}
