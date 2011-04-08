package org.group20.sunstruck.gui;

import java.util.ArrayList;

import org.group20.sunstruck.Game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.BitmapFontCache;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class GUI {
	public static boolean isHelpActive = false;

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
	TextureRegion textureReset;
	TextureRegion textureResetPressed;

	TextureRegion texturePlay;

	TextureRegion texturePlayPressed;
	TextureRegion textureHelp;
	TextureRegion textureHelpPressed;
	TextureRegion textureQuit;
	TextureRegion textureQuitPressed;
	TextureRegion textureMenuScreen;
	TextureRegion textureHelpScreen;

	Sprite weaponUpgButton;
	Sprite hullUpgButton;
	Sprite shieldUpgButton;
	Sprite speedUpgButton;
	Sprite resetButton;
	Sprite confirmButton;

	Sprite playSprite;

	Sprite helpSprite;
	Sprite quitSprite;
	Sprite menuBackground;

	BitmapFont f;

	BitmapFontCache weaponCost;
	BitmapFontCache hullCost;
	BitmapFontCache shieldCost;
	BitmapFontCache speedCost;

	BitmapFontCache scoreFont;
	BitmapFontCache shieldFont;
	BitmapFontCache hullFont;
	BitmapFontCache scoreShopFont;

	Sprite controlCircle;
	Sprite fireButton;

	String weaponString;
	String hullString;
	String shieldString;
	String speedString;

	ArrayList<BitmapFontCache> shopFontList = new ArrayList<BitmapFontCache>();
	ArrayList<BitmapFontCache> statsFontList = new ArrayList<BitmapFontCache>();
	ArrayList<Sprite> menuSpriteList = new ArrayList<Sprite>();
	ArrayList<Sprite> controlSpriteList = new ArrayList<Sprite>();
	ArrayList<Sprite> shopSpriteList = new ArrayList<Sprite>();

	public GUI() {
		TextureAtlas ta = Game.TextureAtlas;

		f = new BitmapFont();

		textureUpgWeapon = ta.findRegion("guiWeapons");
		textureUpgWeaponPressed = ta.findRegion("guiWeaponsPressed");
		textureUpgHull = ta.findRegion("guiHull");
		textureUpgHullPressed = ta.findRegion("guiHullPressed");
		textureUpgShield = ta.findRegion("guiShield");
		textureUpgShieldPressed = ta.findRegion("guiShieldPressed");
		textureUpgSpeed = ta.findRegion("guiSpeed");
		textureUpgSpeedPressed = ta.findRegion("guiSpeedPressed");
		textureConfirm = ta.findRegion("guiConfirm");
		textureConfirmPressed = ta.findRegion("guiConfirmPressed");
		textureReset = ta.findRegion("guiReset");
		textureResetPressed = ta.findRegion("guiResetPressed");

		texturePlay = ta.findRegion("guiPlay");
		texturePlayPressed = ta.findRegion("guiPlayPressed");
		textureHelp = ta.findRegion("guiHelp");
		textureHelpPressed = ta.findRegion("guiHelpPressed");
		textureQuit = ta.findRegion("guiQuit");
		textureQuitPressed = ta.findRegion("guiQuitPressed");
		textureMenuScreen = ta.findRegion("screenMain");
		textureHelpScreen = ta.findRegion("screenHelp");

		TextureRegion textureControlCircle = ta.findRegion("controlCircle");
		TextureRegion textureFireButton = ta.findRegion("fireButton");

		// Makes the sprites with corresponding textures.
		weaponUpgButton = new Sprite(textureUpgWeapon);
		hullUpgButton = new Sprite(textureUpgHull);
		shieldUpgButton = new Sprite(textureUpgShield);
		speedUpgButton = new Sprite(textureUpgSpeed);
		confirmButton = new Sprite(textureConfirm);
		resetButton = new Sprite(textureReset);

		playSprite = new Sprite(texturePlay);
		helpSprite = new Sprite(textureHelp);
		quitSprite = new Sprite(textureQuit);

		playSprite.setPosition(
				Gdx.graphics.getWidth() / 2 - playSprite.getWidth() / 2,
				Gdx.graphics.getHeight() * 5 / 8);
		helpSprite.setPosition(
				Gdx.graphics.getWidth() / 2 - playSprite.getWidth() / 2,
				Gdx.graphics.getHeight() * 4 / 8);
		quitSprite.setPosition(
				Gdx.graphics.getWidth() / 2 - playSprite.getWidth() / 2,
				Gdx.graphics.getHeight() * 3 / 8);

		menuSpriteList.add(playSprite);
		menuSpriteList.add(helpSprite);
		menuSpriteList.add(quitSprite);

		controlCircle = new Sprite(textureControlCircle);
		fireButton = new Sprite(textureFireButton);

		// Sets the positions of the control sprites.
		controlCircle.setPosition(0,
				Gdx.graphics.getHeight() - controlCircle.getHeight());
		fireButton.setPosition(0, 0);

		controlSpriteList.add(controlCircle);
		controlSpriteList.add(fireButton);

		weaponUpgButton.setPosition(Gdx.graphics.getWidth() * 19 / 20
				- weaponUpgButton.getWidth(),
				(float) (Gdx.graphics.getHeight() * 6 / 7));
		hullUpgButton.setPosition(Gdx.graphics.getWidth() * 19 / 20
				- hullUpgButton.getWidth(),
				(float) (Gdx.graphics.getHeight() * 5 / 7));
		shieldUpgButton.setPosition(Gdx.graphics.getWidth() * 19 / 20
				- shieldUpgButton.getWidth(),
				(float) (Gdx.graphics.getHeight() * 4 / 7));
		speedUpgButton.setPosition(Gdx.graphics.getWidth() * 19 / 20
				- speedUpgButton.getWidth(),
				(float) (Gdx.graphics.getHeight() * 3 / 7));
		confirmButton.setPosition(Gdx.graphics.getWidth() * 19 / 20
				- confirmButton.getWidth(),
				(float) (Gdx.graphics.getHeight() * 2 / 7));
		resetButton.setPosition(
				Gdx.graphics.getWidth() * 19 / 20 - resetButton.getWidth(),
				(float) (Gdx.graphics.getHeight() * 1 / 7));

		shopSpriteList.add(weaponUpgButton);
		shopSpriteList.add(hullUpgButton);
		shopSpriteList.add(shieldUpgButton);
		shopSpriteList.add(speedUpgButton);
		shopSpriteList.add(confirmButton);
		shopSpriteList.add(resetButton);

		weaponString = "Current weapon level: 0\nUpgrade cost: 5000";
		hullString = "Current hull level: 0\nUpgrade cost: 5000";
		shieldString = "Current shield level: 0\nUpgrade cost: 5000";
		speedString = "Current speed level: 0\nUpgrade cost: 5000";

		weaponCost = new BitmapFontCache(f);
		hullCost = new BitmapFontCache(f);
		shieldCost = new BitmapFontCache(f);
		speedCost = new BitmapFontCache(f);

		weaponCost.setMultiLineText(weaponString, 0, 0);
		hullCost.setMultiLineText(hullString, 0, 0);
		shieldCost.setMultiLineText(shieldString, 0, 0);
		speedCost.setMultiLineText(speedString, 0, 0);

		weaponCost.setColor(Color.BLACK);
		hullCost.setColor(Color.BLACK);
		shieldCost.setColor(Color.BLACK);
		speedCost.setColor(Color.BLACK);

		weaponCost.setPosition(weaponUpgButton.getX() - 165,
				weaponUpgButton.getY() + weaponUpgButton.getHeight() * 1.25f);
		hullCost.setPosition(hullUpgButton.getX() - 165, hullUpgButton.getY()
				+ hullUpgButton.getHeight() * 1.25f);
		shieldCost.setPosition(shieldUpgButton.getX() - 165,
				shieldUpgButton.getY() + shieldUpgButton.getHeight() * 1.25f);
		speedCost.setPosition(speedUpgButton.getX() - 165,
				speedUpgButton.getY() + speedUpgButton.getHeight() * 1.25f);

		shopFontList.add(weaponCost);
		shopFontList.add(hullCost);
		shopFontList.add(shieldCost);
		shopFontList.add(speedCost);

		scoreFont = new BitmapFontCache(f);
		hullFont = new BitmapFontCache(f);
		shieldFont = new BitmapFontCache(f);
		scoreShopFont = new BitmapFontCache(f);

		scoreFont.setColor(Color.BLACK);
		hullFont.setColor(Color.BLACK);
		shieldFont.setColor(Color.BLACK);
		scoreShopFont.setColor(Color.BLACK);

		statsFontList.add(scoreFont);
		statsFontList.add(hullFont);
		statsFontList.add(shieldFont);

	}

	public void updateStats() {
		scoreFont.setText(
				"SCORE: "
						+ Integer.toString((int) Game.getInstance().getPlayer()
								.getScore()), 0, 0);
		hullFont.setText(
				"Hull: "
						+ Integer.toString((int) Game.getInstance().getPlayer()
								.getCurrentHull()), 0, 0);
		shieldFont.setText(
				"Shield: "
						+ Integer.toString((int) Game.getInstance().getPlayer()
								.getCurrentShield()), 0, 0);
		scoreShopFont.setText(
				"SCORE: "
						+ Integer.toString((int) Game.getInstance().getPlayer()
								.getScore()), 0, 0);

		// scoreFont.setPosition(Gdx.graphics.getWidth() - 100,
		// Gdx.graphics.getHeight());
		// hullFont.setPosition(Gdx.graphics.getWidth() - 190,
		// Gdx.graphics.getHeight());
		// shieldFont.setPosition(Gdx.graphics.getWidth() - 190,
		// Gdx.graphics.getHeight() - 15);

		scoreFont.setPosition(-Gdx.graphics.getHeight(),
				Gdx.graphics.getWidth());
		hullFont.setPosition(-Gdx.graphics.getHeight(),
				Gdx.graphics.getWidth() - 15);
		shieldFont.setPosition(-Gdx.graphics.getHeight(),
				Gdx.graphics.getWidth() - 30);
		scoreShopFont.setPosition(
				Gdx.graphics.getWidth() / 2 - scoreShopFont.getBounds().width
						/ 2, Gdx.graphics.getHeight());
	}

	public String getWeaponString() {
		return weaponString;
	}

	public void setWeaponString(String weaponString) {
		float x = weaponCost.getX();
		float y = weaponCost.getY();
		this.weaponString = weaponString;
		weaponCost.setMultiLineText(this.weaponString, 0, 0);
		weaponCost.setPosition(x, y);
	}

	public String getHullString() {
		return hullString;
	}

	public void setHullString(String hullString) {
		float x = hullCost.getX();
		float y = hullCost.getY();
		this.hullString = hullString;
		hullCost.setMultiLineText(this.hullString, 0, 0);
		hullCost.setPosition(x, y);
	}

	public String getShieldString() {
		return shieldString;
	}

	public void setShieldString(String shieldString) {
		float x = shieldCost.getX();
		float y = shieldCost.getY();
		this.shieldString = shieldString;
		shieldCost.setMultiLineText(this.shieldString, 0, 0);
		shieldCost.setPosition(x, y);
	}

	public String getSpeedString() {
		return speedString;
	}

	public void setSpeedString(String speedString) {
		float x = speedCost.getX();
		float y = speedCost.getY();
		this.speedString = speedString;
		speedCost.setMultiLineText(this.speedString, 0, 0);
		speedCost.setPosition(x, y);
	}

	public BitmapFontCache getWeaponCost() {
		return weaponCost;
	}

	public BitmapFontCache getHullCost() {
		return hullCost;
	}

	public BitmapFontCache getShieldCost() {
		return shieldCost;
	}

	public BitmapFontCache getSpeedCost() {
		return speedCost;
	}

	public void setWeaponCost(BitmapFontCache weaponCost) {
		this.weaponCost = weaponCost;
	}

	public void setHullCost(BitmapFontCache hullCost) {
		this.hullCost = hullCost;
	}

	public void setShieldCost(BitmapFontCache shieldCost) {
		this.shieldCost = shieldCost;
	}

	public void setSpeedCost(BitmapFontCache speedCost) {
		this.speedCost = speedCost;
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

	public Sprite getResetButton() {
		return resetButton;
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

	public ArrayList<BitmapFontCache> getShopFontList() {
		return shopFontList;
	}

	public ArrayList<BitmapFontCache> getStatsFontList() {
		return statsFontList;
	}

	public ArrayList<Sprite> getShopSpriteList() {
		return shopSpriteList;
	}

	public void setShopSpriteList(ArrayList<Sprite> shopSpriteList) {
		this.shopSpriteList = shopSpriteList;
	}

	public ArrayList<Sprite> getMenuSpriteList() {
		return menuSpriteList;
	}

	public TextureRegion getTextureReset() {
		return textureReset;
	}

	public TextureRegion getTextureResetPressed() {
		return textureResetPressed;
	}

	public Sprite getControlCircle() {
		return controlCircle;
	}

	public Sprite getFireButton() {
		return fireButton;
	}

	public void setWeaponTexture(TextureRegion tr) {
		weaponUpgButton.setRegion(tr);
	}

	public void setHullTexture(TextureRegion tr) {
		hullUpgButton.setRegion(tr);
	}

	public void setShieldTexture(TextureRegion tr) {
		shieldUpgButton.setRegion(tr);
	}

	public void setSpeedTexture(TextureRegion tr) {
		speedUpgButton.setRegion(tr);
	}

	public void setResetTexture(TextureRegion tr) {
		resetButton.setRegion(tr);
	}

	public BitmapFontCache getScoreShopFont() {
		return scoreShopFont;
	}

	public TextureRegion getTexturePlay() {
		return texturePlay;
	}

	public void setTexturePlay(TextureRegion texturePlay) {
		this.texturePlay = texturePlay;
	}

	public TextureRegion getTexturePlayPressed() {
		return texturePlayPressed;
	}

	public void setTexturePlayPressed(TextureRegion texturePlayPressed) {
		this.texturePlayPressed = texturePlayPressed;
	}

	public TextureRegion getTextureHelp() {
		return textureHelp;
	}

	public void setTextureHelp(TextureRegion textureHelp) {
		this.textureHelp = textureHelp;
	}

	public TextureRegion getTextureHelpPressed() {
		return textureHelpPressed;
	}

	public void setTextureHelpPressed(TextureRegion textureHelpPressed) {
		this.textureHelpPressed = textureHelpPressed;
	}

	public TextureRegion getTextureQuit() {
		return textureQuit;
	}

	public void setTextureQuit(TextureRegion textureQuit) {
		this.textureQuit = textureQuit;
	}

	public TextureRegion getTextureQuitPressed() {
		return textureQuitPressed;
	}

	public void setTextureQuitPressed(TextureRegion textureQuitPressed) {
		this.textureQuitPressed = textureQuitPressed;
	}

	public TextureRegion getTextureMenuScreen() {
		return textureMenuScreen;
	}

	public void setTextureMenuScreen(TextureRegion textureMenuScreen) {
		this.textureMenuScreen = textureMenuScreen;
	}

	public TextureRegion getTextureHelpScreen() {
		return textureHelpScreen;
	}

	public void setTextureHelpScreen(TextureRegion textureHelpScreen) {
		this.textureHelpScreen = textureHelpScreen;
	}

	public Sprite getPlaySprite() {
		return playSprite;
	}

	public void setPlayTexture(TextureRegion tr) {
		playSprite.setRegion(tr);
	}

	public Sprite getHelpSprite() {
		return helpSprite;
	}

	public void setHelpTexture(TextureRegion tr) {
		helpSprite.setRegion(tr);
	}

	public Sprite getQuitSprite() {
		return quitSprite;
	}

	public void setQuitTexture(TextureRegion tr) {
		quitSprite.setRegion(tr);
	}
}
