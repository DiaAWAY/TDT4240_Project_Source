package org.tdt4240.group20.sunstruck;

import com.badlogic.gdx.backends.jogl.JoglApplication;

public class MainDesktop {
	
	public static void main(String[] argv) {
		new JoglApplication(new Main(), "Hello World", 480, 320, false);
	}
}
