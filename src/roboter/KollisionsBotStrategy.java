package roboter;

import java.awt.Color;
import java.awt.event.KeyEvent;

import robocode.AdvancedRobot;
import robocode.Event;
import robocode.ScannedRobotEvent;

/**
 * Ein Bot fuer das Strategy Interface der RoboCode Anwendung <br>
 * Vorgehensweise des Roboters: <br>
 * Der Bot dreht sich links rum bis er einen Roboter scannt. Ist ein Roboter in
 * Sichtweite bewegt er sich auf ihn zu und schiesst. Er dreht sich dann in die
 * Richtung, in die auch der erkannte Roboter sich dreht und behaelt ihn somit
 * im Blick. <br>
 * Nutzbar durch die Implementierung der Strategie {@link Strategy}.
 * 
 * @author Marcel Thomas Krups
 */
public class KollisionsBotStrategy implements Strategy {

	boolean turn;
	int richtung;
	AdvancedRobot bossBot;

	/**
	 * Hauptmethode (Funktionalität siehe Klassenbeschreibung)
	 */
	@Override
	public void move() {
		bossBot.setAllColors(Color.red);
		if (richtung > 0) {
			bossBot.setTurnLeft(8);
		} else {
			bossBot.setTurnRight(8);
		}
		bossBot.execute();
	}

	@Override
	public void identify(AdvancedRobot r) {
		bossBot = r;
	}

	@Override
	public void reactEvent(Event e) {
	}

	@Override
	public void reactKey(KeyEvent e) {
	}

	/**
	 * Wenn ein Bot sich in Sichtweite befindet, schiesst der Roboter und faehrt
	 * gerade aus. Der Bot dreht sich in die Richtung, in die der erkannte Bot sich
	 * bewegt.
	 * 
	 * @param e ScannedRobotEvent
	 */
	@Override
	public void fire(ScannedRobotEvent e) {
		if (e.getBearing() < 0) {
			richtung = 1;
		} else {
			richtung = -1;
		}
		bossBot.ahead(7);
		bossBot.fire(2);
	}

	@Override
	public void reactKeyReleased(KeyEvent e) {

	}

	@Override
	public void reset() {
		bossBot.setBodyColor(Color.red);
		bossBot.setAhead(0);
		bossBot.setTurnGunRight(bossBot.getHeading() - bossBot.getGunHeading());
		richtung = 1;
		bossBot.execute();
	}
}
