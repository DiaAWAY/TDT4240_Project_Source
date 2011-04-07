package org.group20.sunstruck.gui;

import java.util.ArrayList;

import org.group20.sunstruck.Game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class GUI {
	TextureRegion textureUpgWeapon;
	TextureRegion textureUpgWeaponPressed;
	TextureRegion textureUpgHull;
	TextureRegion textureUpgHullPressed;
	TextureRegion textureUpgShield;
	TextureRegion textureUpgShieldPressed;
	TextureRegion textureUpgSpeed;
	TextureRegion textureUpgSpeedPressed;
	TextureRegion textureConfirm;
	TextureRegion textureConfirmPressed;
	TextureRegion textureCancel;
	TextureRegion textureCancelPressed;

	Sprite weaponUpgButton;
	Sprite hullUpgButton;
	Sprite shieldUpgButton;
	Sprite speedUpgButton;
	Sprite cancelButton;
	Sprite confirmButton;

	Sprite controlCircle;
	Sprite fireButton;

	ArrayList<Sprite> controlSpriteList = new ArrayList<Sprite>();
	ArrayList<Sprite> shopSpriteList = new ArrayList<Sprite>();

	public GUI() {
		Game.getInstance();
		TextureAtlas ta = Game.textureAtlas;

		textureUpgWeapon = ta.findRegion("buttonWeapon");
		textureUpgWeaponPressed = ta.findRegion("buttonWeaponPressed");

		textureUpgHull = ta.findRegion("buttonHull");
		textureUpgHullPressed = ta.findRegion("buttonPressed");

		textureUpgShield = ta.findRegion("buttonShield");
		textureUpgShieldPressed = ta.findRegion("buttonShieldPressed");

		textureUpgSpeed = ta.findRegion("buttonSpeed");
		textureUpgSpeedPressed = ta.findRegion("buttonSpeedPressed");

		// textureConfirm = ta.findRegion("guiConfirm");
		// textureConfirmPressed = ta.findRegion("guiConfirmPressed");

		// textureCancel = ta.findRegion("guiCancel");
		// textureCancelPressed = ta.findRegion("guiCancelPressed");

		TextureRegion textureControlCircle = ta.findRegion("controlCircle");
		TextureRegion textureFireButton = ta.findRegion("fireButton");

		// Makes the sprites with corresponding textures.
		weaponUpgButton = new Sprite(textureUpgWeapon);
		hullUpgButton = new Sprite(textureUpgHull);
		shieldUpgButton = new Sprite(textureUpgShield);
		speedUpgButton = new Sprite(textureUpgSpeed);
		// confirmButton = new Sprite(textureConfirm);
		// cancelButton = new Sprite(textureCancel);

		controlCircle = new Sprite(textureControlCircle);
		fireButton = new Sprite(textureFireButton);

		// Sets the positions of the control sprites.
		controlCircle.setPosition(0,
				Gdx.graphics.getHeight() - controlCircle.getHeight());
		fireButton.setPosition(0, 0);
		weaponUpgButton
				.setPosition(
						Gdx.graphics.getWidth() / 2
								- weaponUpgButton.getWidth() / 2,
						Gdx.graphics.getHeight() - 50);
		hullUpgButton.setPosition(
				Gdx.graphics.getWidth() / 2 - hullUpgButton.getWidth() / 2,
				Gdx.graphics.getHeight() - 100);
		shieldUpgButton.setPosition(Gdx.graphics.getWidth() / 2
				- shieldUpgButton.getWidth() / 2,
				Gdx.graphics.getHeight() - 150);
		speedUpgButton.setPosition(
				Gdx.graphics.getWidth() / 2 - speedUpgButton.getWidth() / 2,
				Gdx.graphics.getHeight() - 200);
		// confirmButton.setPosition(Gdx.graphics.getWidth() / 2 -
		// cancelButton.getWidth() / 2, Gdx.graphics.getHeight()-250);
		// cancelButton.setPosition(Gdx.graphics.getWidth() / 2 -
		// cancelButton.getWidth() / 2, Gdx.graphics.getHeight()-300);

		shopSpriteList.add(weaponUpgButton);
		shopSpriteList.add(hullUpgButton);
		shopSpriteList.add(shieldUpgButton);
		shopSpriteList.add(speedUpgButton);
		// shopSpriteList.add(confirmButton);
		// shopSpriteList.add(cancelButton);

		controlSpriteList.add(controlCircle);
		controlSpriteList.add(fireButton);
	}

	// Getters for Shop buttons
	public Sprite getWeaponUpgButton() {
		return weaponUpgButton;
	}

	public Sprite getHullUpgButton() {
		return hullUpgButton;
	}

	public Sprite getShieldUpgButton() {
		return shieldUpgButton;
	}

	public Sprite getSpeedUpgButton() {
		return speedUpgButton;
	}

	public Sprite getCancelButton() {
		return cancelButton;
	}

	public Sprite getConfirmButton() {
		return confirmButton;
	}

	// End of getters for Shop buttons

	public TextureRegion getTextureUpgWeapon() {
		return textureUpgWeapon;
	}

	public TextureRegion getTextureUpgWeaponPressed() {
		return textureUpgWeaponPressed;
	}

	public TextureRegion getTextureUpgHull() {
		return textureUpgHull;
	}

	public TextureRegion getTextureUpgHullPressed() {
		return textureUpgHullPressed;
	}

	public TextureRegion getTextureUpgShield() {
		return textureUpgShield;
	}

	public TextureRegion getTextureUpgShieldPressed() {
		return textureUpgShieldPressed;
	}

	public TextureRegion getTextureUpgSpeed() {
		return textureUpgSpeed;
	}

	public TextureRegion getTextureUpgSpeedPressed() {
		return textureUpgSpeedPressed;
	}

	public TextureRegion getTextureConfirm() {
		return textureConfirm;
	}

	public TextureRegion getTextureConfirmPressed() {
		return textureConfirmPressed;
	}

	public ArrayList<Sprite> getControlSpriteList() {
		return controlSpriteList;
	}

	public void setControlSpriteList(ArrayList<Sprite> controlSpriteList) {
		this.controlSpriteList = controlSpriteList;
	}

	public ArrayList<Sprite> getShopSpriteList() {
		return shopSpriteList;
	}

	public void setShopSpriteList(ArrayList<Sprite> shopSpriteList) {
		this.shopSpriteList = shopSpriteList;
	}

	public TextureRegion getTextureCancel() {
		return textureCancel;
	}

	public TextureRegion getTextureCancelPressed() {
		return textureCancelPressed;
	}

	public Sprite getControlCircle() {
		return controlCircle;
	}

	public Sprite getFireButton() {
		return fireButton;
	}
}
