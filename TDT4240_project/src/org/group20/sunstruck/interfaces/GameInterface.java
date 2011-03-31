package org.group20.sunstruck.interfaces;

import org.group20.sunstruck.Shop;
import org.group20.sunstruck.gameobject.GameObjectFactory;
import org.group20.sunstruck.gameobject.Player;
import org.group20.sunstruck.gui.GUI;
import org.group20.sunstruck.input.Input;
import org.group20.sunstruck.world.map.MapGenerator;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.physics.box2d.World;

public interface GameInterface {
	
	
	public static enum DIFFICULTIES {EASY, MEDIUM, HARD}

	void start();

	void update();

	void setGoFactory(GameObjectFactory goFactory);

	GameObjectFactory getGoFactory();

	void setPlayer(Player player);

	Player getPlayer();

	void setShop(Shop shop);

	Shop getShop();

	void setMap(MapGenerator map);

	MapGenerator getMap();

	void setWorld(World world);

	World getWorld();

	void setInput(Input input);

	Input getInput();

	void setGui(GUI gui);

	GUI getGui();

	void setDifficulty(DIFFICULTIES d);

	DIFFICULTIES getDifficulty();

	void setUpdateRate(float updaterate);

	float getUpdateRate();
}
