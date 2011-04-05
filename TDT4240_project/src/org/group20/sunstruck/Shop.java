package org.group20.sunstruck;

public class Shop {
	public static boolean isActive = false;

	float weaponUpgrade;
	float hullUpgrade;
	float shieldUpgrade;
	float speedUpgrade;
	Game game;

	public Shop(Game game) {
		this.game = Game.getInstance();
		weaponUpgrade = 0;
		hullUpgrade = 0;
		shieldUpgrade = 0;
		speedUpgrade = 0;
	}

	/*
	 * //TODO: Oppdatere disse til korrekte referanser ettersom game.gui er
	 * fortsatt fra prototypen public void update() { if
	 * (Gdx.input.justTouched()) { if
	 * (game.getInput().getHitButton(game.getGui().getWeaponUpgButton())) {
	 * System.out.println("WEAPONS UPGRADED!"); weaponUpgrade++; } if
	 * (game.getInput().getHitButton(game.getGui().getHullUpgButton())) {
	 * System.out.println("HULL REINFORCED!"); hullUpgrade++; } if
	 * (game.getInput().getHitButton(game.getGui().getShieldUpgButton())) {
	 * System.out.println("SHIELD UPGRADED!"); shieldUpgrade++; } if
	 * (game.getInput().getHitButton(game.getGui().getSpeedUpgButton())) {
	 * System.out.println("SPEED UPGRADED!"); speedUpgrade++; } if
	 * (game.getInput().getHitButton(game.getGui().getConfirmButton())) {
	 * updateEquipment(); //change state back to the game game here
	 * game.setState("game"); } if
	 * (game.getInput().getHitButton(game.getGui().getCancelButton())) { //
	 * change state back to game } }
	 * 
	 * if (Gdx.input.isTouched()) { if
	 * (game.getInput().getHitButton(game.getGui().getWeaponUpgButton())) {
	 * game.getGui().getWeaponUpgButton().setTexture(game.getGui().
	 * getTextureUpgWeaponPressed()); } else if
	 * (game.getInput().getHitButton(game.getGui().getHullUpgButton())) {
	 * game.getGui
	 * ().getHullUpgButton().setTexture(game.getGui().getTextureUpgHullPressed
	 * ()); } else if
	 * (game.getInput().getHitButton(game.getGui().getShieldUpgButton())) {
	 * game.getGui().getShieldUpgButton().setTexture(game.getGui().
	 * getTextureUpgShieldPressed()); } else if
	 * (game.getInput().getHitButton(game.getGui().getSpeedUpgButton())) {
	 * game.getGui
	 * ().getSpeedUpgButton().setTexture(game.getGui().getTextureUpgSpeedPressed
	 * ()); } else if
	 * (game.getInput().getHitButton(game.getGui().getConfirmButton())) {
	 * //Hvordan legge til feedback her før den skifter skjerm? Slik det er nå
	 * så skifter den skjerm instantly uten at det blir // trykket ned. } else
	 * if (game.getInput().getHitButton(game.getGui().getCancelButton())) {
	 * //Hvordan legge til feedback her før den skifter skjerm? Slik det er nå
	 * så skifter den skjerm instantly uten at det blir // trykket ned. } else
	 * unpressButtons(); } else unpressButtons(); }
	 * 
	 * private void unpressButtons() {
	 * game.getGui().getWeaponUpgButton().setTexture
	 * (game.getGui().getTextureUpgWeapon());
	 * game.getGui().getHullUpgButton().setTexture
	 * (game.getGui().getTextureUpgHull());
	 * game.getGui().getShieldUpgButton().setTexture
	 * (game.getGui().getTextureUpgShield());
	 * game.getGui().getSpeedUpgButton().setTexture
	 * (game.getGui().getTextureUpgSpeed()); }
	 * 
	 * private void updateEquipment() {
	 * game.getPlayer().addWeaponLevel(weaponUpgrade);
	 * game.getPlayer().addHullLevel(hullUpgrade);
	 * game.getPlayer().addShieldLevel(shieldUpgrade);
	 * game.getPlayer().addSpeedLevel(speedUpgrade); }
	 */

}
