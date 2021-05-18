package roboter;

import java.awt.event.KeyEvent;

import robocode.AdvancedRobot;
import robocode.HitByBulletEvent;
import robocode.HitRobotEvent;
import robocode.HitWallEvent;
import robocode.ScannedRobotEvent;

/**
 * Ein Bot fuer die RoboCode Anwendung Vorgehensweise des Roboters:
 * 
 * Der Bot enthaelt drei verschiedenen Strategien {@link Strategy} zwischen denen mit der
 * Eingabe der Taste 'c' gewechselt werden kann. <br>
 * Implementierte Strategien: <br>
 * - Kollisionsbot {@link KollisionsBotStrategy} <br>
 * - TastaturBot {@link TastaturBotStrategy} <br>
 * - Umrandungsbot {@link UmrandungsBotStrategy} <br>
 * 
 * @author Marcel Thomas Krups
 * 
 */
public class Wechselrobot extends AdvancedRobot {

	Strategy strategy = null;
	Strategy[] strategies = new Strategy[3];
	int strategyPos = 0;

	/**
	 * Hauptmethode des Roboters, hier werden die Strategien initialisiert und angesteuert.
	 */
	@Override
	public void run() {
		strategies[0] = new KollisionsBotStrategy();
		strategies[0].identify(this);
		strategies[1] = new TastaturBotStrategy();
		strategies[1].identify(this);
		strategies[2] = new UmrandungsBotStrategy();
		strategies[2].identify(this);

		strategy = strategies[0];
		while (true) {
			strategy.move();
		}
	}

	/**
	 * Prueft auf die Eingabe der Taste 'c' zum Strategie wechseln,
	 * ansonsten wir das KeyEvent weitergereicht an die Strategie
	 * @param e KeyEvent
	 */
	@Override
	public void onKeyPressed(KeyEvent e) {
		out.println("taste" + e.getKeyChar());
		if (e.getKeyChar() == 'c') {
			toggleStrategies();
			out.println("change!");
		} else {
			strategy.reactKey(e);
		}
	}
	
	/**
	 * Uebergibt eine KeyEvent(KeyReleased)
	 * @param e KeyEvent
	 */
	@Override
	public void onKeyReleased(KeyEvent e) {
		strategy.reactKeyReleased(e);
	}

	/**
	 * Uebergibt ein HitByBulletEvent
	 * @param hbbe HitByBulletEvent
	 */
	@Override
	public void onHitByBullet(HitByBulletEvent hbbe) {
		strategy.reactEvent(hbbe);
	}

	/**
	 * Uebergibt ein HitRobotEvent
	 * @param hre HitRobotEvent
	 */
	@Override
	public void onHitRobot(HitRobotEvent hre) {
		strategy.reactEvent(hre);
	}

	/**
	 * Uebergibt ein HitWallEvent
	 * @param hwe HitWallEvent
	 */
	@Override
	public void onHitWall(HitWallEvent hwe) {
		strategy.reactEvent(hwe);
	}

	/**
	 * Uebergibt ein ScannedRobotEvent
	 * @param sre ScannedRobotEvent
	 */
	@Override
	public void onScannedRobot(ScannedRobotEvent sre) {
		strategy.fire(sre);
	}

	/**
	 * Hier wird die Strategie gewechselt.
	 * Dafuer wird eine neue Strategie ausgewaehlt und resettet.
	 */
	private synchronized void toggleStrategies() {
		strategyPos += 1;
		if (strategyPos >= strategies.length) {
			strategyPos = 0;
		}
		out.println("Strategiewechsel auf " + strategyPos);
		strategy = strategies[strategyPos];
		strategy.reset();
	}
}
