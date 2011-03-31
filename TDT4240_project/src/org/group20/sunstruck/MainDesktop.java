package org.group20.sunstruck;

import com.badlogic.gdx.backends.jogl.JoglApplication;

public class MainDesktop {
	
	public static void main(String[] argv) {
		new JoglApplication(new Main(), "Hello World", 320*2, 533*2, false);
	}
}
