package org.group20.sunstruck;

import org.group20.sunstruck.gui.GUI;

public class Menu {

	public static boolean isActive = true;

	public Menu() {
	}

	public void update() {
		if (Game.getInstance().getInput().isPlay()) {
			// TODO: this is just fucked up!
			System.out.println("pressed");
			isActive = false;
			Game.getInstance().getInput().setPlay(false);
			if (Game.getInstance().getPlayer().isDisposed()) {
				Game.getInstance().reset();
				Game.getInstance().initializePlayer();
			}
		}
		if (Game.getInstance().getInput().isHelp()) {
			GUI.isHelpActive = true;
		}
		if (Game.getInstance().getInput().isQuit()) {
			// QUIT THE #!#$!## GAME!
			System.exit(0);
		}

	}

}
