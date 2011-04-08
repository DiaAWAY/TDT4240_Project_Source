package org.group20.sunstruck;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.BitmapFontCache;

public class Shop {
	public static boolean isActive = false;

	int weaponUpgrade;
	int hullUpgrade;
	int shieldUpgrade;
	int speedUpgrade;

	BitmapFont font;

	BitmapFontCache weaponCost;
	BitmapFontCache hullCost;
	BitmapFontCache shieldCost;
	BitmapFontCache speedCost;

	String weaponString;
	String weaponStringNew;
	String hullString;
	String hullStringNew;
	String shieldString;
	String shieldStringNew;
	String speedString;
	String speedStringNew;

	int weaponUpgradeCost;
	int hullUpgradeCost;
	int shieldUpgradeCost;
	int speedUpgradeCost;

	int weaponUpgradeCostOrig = 5000;
	int hullUpgradeCostOrig = 5000;
	int shieldUpgradeCostOrig = 5000;
	int speedUpgradeCostOrig = 5000;

	int scoreOriginal;

	public int getScoreOriginal() {
		return scoreOriginal;
	}

	public void setScoreOriginal(int scoreOriginal) {
		this.scoreOriginal = scoreOriginal;
	}

	public Shop() {
		weaponUpgradeCost = 5000;
		hullUpgradeCost = 5000;
		shieldUpgradeCost = 5000;
		speedUpgradeCost = 5000;

		resetUpgrades();
		font = new BitmapFont();
	}

	public void update() {
		font = new BitmapFont();
		weaponCost = Game.getInstance().getGui().getWeaponCost();
		hullCost = Game.getInstance().getGui().getHullCost();
		shieldCost = Game.getInstance().getGui().getShieldCost();
		speedCost = Game.getInstance().getGui().getSpeedCost();

		weaponString = Game.getInstance().getGui().getWeaponString();
		if (weaponStringNew == null)
			weaponStringNew = Game.getInstance().getGui().getWeaponString();

		hullString = Game.getInstance().getGui().getHullString();
		if (hullStringNew == null)
			hullStringNew = Game.getInstance().getGui().getHullString();

		shieldString = Game.getInstance().getGui().getShieldString();
		if (shieldStringNew == null)
			shieldStringNew = Game.getInstance().getGui().getShieldString();

		speedString = Game.getInstance().getGui().getSpeedString();
		if (speedStringNew == null)
			speedStringNew = Game.getInstance().getGui().getSpeedString();

		if (Game.getInstance().getInput().isWeaponUpgraded()
				&& Game.getInstance().getPlayer().getScore() >= weaponUpgradeCost) {
			System.out.println("test5");
			weaponUpgrade++;

			if (weaponUpgrade == 1)
				weaponUpgradeCostOrig = weaponUpgradeCost;
			Game.getInstance()
					.getPlayer()
					.setScore(
							Game.getInstance().getPlayer().getScore()
									- weaponUpgradeCost);
			weaponUpgradeCost = 5000 + 5000 * (Game.getInstance().getPlayer()
					.getWeaponLevel() + weaponUpgrade);

			float x = weaponCost.getX();
			float y = weaponCost.getY();

			weaponStringNew = "Current weapon level: "
					+ (Game.getInstance().getPlayer().getWeaponLevel() + weaponUpgrade)
					+ "\n" + "Upgrade cost: " + weaponUpgradeCost;

			weaponCost.setMultiLineText(weaponStringNew, 0, 0);
			weaponCost.setPosition(x, y);
		}

		if (Game.getInstance().getInput().isHullUpgraded()
				&& Game.getInstance().getPlayer().getScore() >= hullUpgradeCost) {
			hullUpgrade++;

			if (hullUpgrade == 1)
				hullUpgradeCostOrig = hullUpgradeCost;
			Game.getInstance()
					.getPlayer()
					.setScore(
							Game.getInstance().getPlayer().getScore()
									- hullUpgradeCost);
			hullUpgradeCost = 5000 + 5000 * (Game.getInstance().getPlayer()
					.getHullLevel() + hullUpgrade);

			float x = hullCost.getX();
			float y = hullCost.getY();

			hullUpgradeCost = 5000 + 5000 * (Game.getInstance().getPlayer()
					.getHullLevel() + hullUpgrade);

			hullStringNew = "Current hull level: "
					+ (Game.getInstance().getPlayer().getHullLevel() + hullUpgrade)
					+ "\n" + "Upgrade cost: " + hullUpgradeCost;

			hullCost.setMultiLineText(hullStringNew, 0, 0);
			hullCost.setPosition(x, y);
		}

		if (Game.getInstance().getInput().isShieldUpgraded()
				&& Game.getInstance().getPlayer().getScore() >= shieldUpgradeCost) {
			shieldUpgrade++;

			if (shieldUpgrade == 1)
				shieldUpgradeCostOrig = shieldUpgradeCost;
			Game.getInstance()
					.getPlayer()
					.setScore(
							Game.getInstance().getPlayer().getScore()
									- shieldUpgradeCost);
			shieldUpgradeCost = 5000 + 5000 * (Game.getInstance().getPlayer()
					.getShieldLevel() + shieldUpgrade);

			float x = shieldCost.getX();
			float y = shieldCost.getY();

			shieldUpgradeCost = 5000 + 5000 * (Game.getInstance().getPlayer()
					.getShieldLevel() + shieldUpgrade);

			shieldStringNew = "Current shield level: "
					+ (Game.getInstance().getPlayer().getShieldLevel() + shieldUpgrade)
					+ "\n" + "Upgrade cost: " + shieldUpgradeCost;

			shieldCost.setMultiLineText(shieldStringNew, 0, 0);
			shieldCost.setPosition(x, y);
		}

		if (Game.getInstance().getInput().isSpeedUpgraded()
				&& Game.getInstance().getPlayer().getScore() >= speedUpgradeCost) {
			speedUpgrade++;

			if (speedUpgrade == 1)
				speedUpgradeCostOrig = speedUpgradeCost;
			Game.getInstance()
					.getPlayer()
					.setScore(
							Game.getInstance().getPlayer().getScore()
									- speedUpgradeCost);
			speedUpgradeCost = 5000 + 5000 * (Game.getInstance().getPlayer()
					.getSpeedLevel() + speedUpgrade);

			float x = speedCost.getX();
			float y = speedCost.getY();

			speedUpgradeCost = 5000 + 5000 * (Game.getInstance().getPlayer()
					.getSpeedLevel() + speedUpgrade);

			speedStringNew = "Current speed level: "
					+ (Game.getInstance().getPlayer().getSpeedLevel() + speedUpgrade)
					+ "\n" + "Upgrade cost: " + speedUpgradeCost;

			speedCost.setMultiLineText(speedStringNew, 0, 0);
			speedCost.setPosition(x, y);
		}

		if (Game.getInstance().getInput().isConfirmUpgrades()) {
			upgradeEquipment();
			resetUpgrades();
			isActive = false;
		}

		if (Game.getInstance().getInput().isCancelUpgrades()) {
			resetEquipment();
			resetUpgrades();
		}

		// Pressed effect
		// Maybe smarter way to do this so it does not setRegion every update
		if (Game.getInstance().getInput().isWeaponPressed()) {
			Game.getInstance()
					.getGui()
					.setWeaponTexture(
							Game.getInstance().getGui()
									.getTextureUpgWeaponPressed());
		} else {
			Game.getInstance()
					.getGui()
					.setWeaponTexture(
							Game.getInstance().getGui().getTextureUpgWeapon());
		}
		if (Game.getInstance().getInput().isHullPressed()) {
			Game.getInstance()
					.getGui()
					.setHullTexture(
							Game.getInstance().getGui()
									.getTextureUpgHullPressed());
		} else {
			Game.getInstance()
					.getGui()
					.setHullTexture(
							Game.getInstance().getGui().getTextureUpgHull());
		}
		if (Game.getInstance().getInput().isShieldPressed()) {
			Game.getInstance()
					.getGui()
					.setShieldTexture(
							Game.getInstance().getGui()
									.getTextureUpgShieldPressed());
		} else {
			Game.getInstance()
					.getGui()
					.setShieldTexture(
							Game.getInstance().getGui().getTextureUpgShield());
		}
		if (Game.getInstance().getInput().isSpeedPressed()) {
			Game.getInstance()
					.getGui()
					.setSpeedTexture(
							Game.getInstance().getGui()
									.getTextureUpgSpeedPressed());
		} else {
			Game.getInstance()
					.getGui()
					.setSpeedTexture(
							Game.getInstance().getGui().getTextureUpgSpeed());
		}
		if (Game.getInstance().getInput().isResetPressed()) {
			Game.getInstance()
					.getGui()
					.setResetTexture(
							Game.getInstance().getGui()
									.getTextureResetPressed());
		} else {
			Game.getInstance()
					.getGui()
					.setResetTexture(
							Game.getInstance().getGui().getTextureReset());
		}

		System.out.println(Game.getInstance().getPlayer().getScore());

	}

	private void resetUpgrades() {
		weaponUpgrade = 0;
		hullUpgrade = 0;
		shieldUpgrade = 0;
		speedUpgrade = 0;

		weaponStringNew = null;
		hullStringNew = null;
		shieldStringNew = null;
		speedStringNew = null;
	}

	private void upgradeEquipment() {
		weaponString = weaponStringNew;
		Game.getInstance().getGui().setWeaponString(weaponString);

		hullString = hullStringNew;
		Game.getInstance().getGui().setHullString(hullString);

		shieldString = shieldStringNew;
		Game.getInstance().getGui().setShieldString(shieldString);

		speedString = speedStringNew;
		Game.getInstance().getGui().setSpeedString(speedString);

		Game.getInstance().getPlayer().addWeaponLevel(weaponUpgrade);
		Game.getInstance().getPlayer().addHullLevel(hullUpgrade);
		Game.getInstance().getPlayer().addShieldLevel(shieldUpgrade);
		Game.getInstance().getPlayer().addSpeedLevel(speedUpgrade);
	}

	private void resetEquipment() {
		Game.getInstance().getGui().setWeaponString(weaponString);
		Game.getInstance().getGui().setHullString(hullString);
		Game.getInstance().getGui().setShieldString(shieldString);
		Game.getInstance().getGui().setSpeedString(speedString);

		Game.getInstance().getPlayer().setScore(scoreOriginal);

		weaponUpgradeCost = weaponUpgradeCostOrig;
		hullUpgradeCost = hullUpgradeCostOrig;
		shieldUpgradeCost = shieldUpgradeCostOrig;
		speedUpgradeCost = speedUpgradeCostOrig;
	}

	public void setWeaponUpgradeCost(int upgCost) {
		weaponUpgradeCost = upgCost;
	}

	public int getWeaponUpgradeCost() {
		return weaponUpgradeCost;
	}

	public void setHullUpgradeCost(int upgCost) {
		hullUpgradeCost = upgCost;
	}

	public int getHullUpgradeCost() {
		return hullUpgradeCost;
	}

	public void setShieldUpgradeCost(int upgCost) {
		shieldUpgradeCost = upgCost;
	}

	public int getShieldUpgradeCost() {
		return shieldUpgradeCost;
	}

	public void setSpeedUpgradeCost(int upgCost) {
		speedUpgradeCost = upgCost;
	}

	public int getSpeedUpgradeCost() {
		return speedUpgradeCost;
	}

}
