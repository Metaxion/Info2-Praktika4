package roboter;

import java.awt.Color;
import java.awt.event.KeyEvent;

import robocode.AdvancedRobot;
import robocode.Event;
import robocode.HitByBulletEvent;
import robocode.HitWallEvent;
import robocode.ScannedRobotEvent;

/**
 * Ein Bot fuer das Strategy Interface der RoboCode Anwendung <br>
 * Vorgehensweise des Roboters: <br>
 * Der Roboter dreht sich links rum bis er zur rechten Wand schaut, bewegt sich darauf an
 * den Rand. Ab da fährt der Bot am Rand entlang im Kreis, mit der Kanone in
 * Richtung Spielfeld gerichtet. Bei Sichtkontakt schiesst der Bot. Wird er
 * getroffen, dreht er sich und faehrt in die andere Richtung im Kreis. <br>
 * Nutzbar durch die Implementierung der Strategie {@link Strategy}.
 * 
 * @author Marcel Thomas Krups
 */
public class UmrandungsBotStrategy implements Strategy {

	int turnDirection; // 1 = rechts rum, 2 = links rum
	int turnRight;
	int turnLeft;
	AdvancedRobot bossBot;

	/**
	 * Hauptmethode (Funktionalität siehe Klassenbeschreibung)
	 */
	@Override
	public void move() {
		if (turnRight == 1) {
			bossBot.turnRight(90);
			turnRight = 0;
		} else if (turnLeft == 1) {
			bossBot.turnLeft(90);
			turnLeft = 0;
		}
		bossBot.setAhead(10);
		bossBot.execute();
	}

	@Override
	public void reactEvent(Event e) {
		if (e instanceof HitWallEvent) {
			onHitWall((HitWallEvent) e);
		} else if (e instanceof HitByBulletEvent) {
			onHitByBullet((HitByBulletEvent) e);
		}
	}

	/**
	 * Bei Wandkontakt dreht er sich um 90 Grad.
	 * 
	 * @param e HitWallEvent
	 */
	public void onHitWall(HitWallEvent e) {
		if (turnDirection == 1) {
			turnRight = 1;
		} else {
			turnLeft = 1;
		}
	}

	/**
	 * Wenn er von einer Kugel getroffen wird, dreht er sich um 180 Grad und faehrt
	 * in die andere Richtung
	 * 
	 * @param e HitByBulletEvent
	 */
	public void onHitByBullet(HitByBulletEvent e) {
		if (turnDirection == 1) {
			turnDirection = 2;
			bossBot.turnRight(180);
			bossBot.turnGunRight(180);
		} else {
			turnDirection = 1;
			bossBot.turnLeft(180);
			bossBot.turnGunLeft(180);
		}
	}

	/**
	 * Wenn ein Bot sich in Sichtweite befindet, schiesst der Roboter
	 * 
	 * @param e ScannedRobotEvent
	 */
	@Override
	public void fire(ScannedRobotEvent e) {
		bossBot.fire(1);
	}

	@Override
	public void identify(AdvancedRobot r) {
		bossBot = r;
	}

	@Override
	public void reactKey(KeyEvent e) {
	}

	@Override
	public void reactKeyReleased(KeyEvent e) {
	}

	@Override
	public void reset() {
		bossBot.setAhead(0);
		bossBot.setTurnRight(0);

		bossBot.setAllColors(Color.green);
		turnDirection = 1;
		turnRight = 0;
		turnLeft = 0;
		double change = bossBot.getHeading() - 90;
		bossBot.turnLeft(change);
		bossBot.turnGunRight(90);
		bossBot.execute();
	}

}
