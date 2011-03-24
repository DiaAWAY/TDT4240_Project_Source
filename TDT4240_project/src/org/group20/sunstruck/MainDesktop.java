package org.group20.sunstruck;

import com.badlogic.gdx.backends.jogl.JoglApplication;

public class MainDesktop {
	
	public static void main(String[] argv) {
		new JoglApplication(new SimpleTest(), "AWESOME PHYSICS", 480, 320, false);
	}
}
